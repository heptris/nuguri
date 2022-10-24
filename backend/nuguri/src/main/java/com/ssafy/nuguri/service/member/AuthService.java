package com.ssafy.nuguri.service.member;

import com.ssafy.nuguri.config.jwt.TokenProvider;
import com.ssafy.nuguri.domain.member.Member;
import com.ssafy.nuguri.dto.auth.MemberJoinDto;
import com.ssafy.nuguri.dto.auth.MemberJoinResponseDto;
import com.ssafy.nuguri.dto.auth.MemberLoginDto;
import com.ssafy.nuguri.dto.token.TokenDto;
import com.ssafy.nuguri.dto.token.TokenRequestDto;
import com.ssafy.nuguri.exception.ex.CustomException;
import com.ssafy.nuguri.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.ssafy.nuguri.exception.ex.ErrorCode.*;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;
    private final TokenProvider tokenProvider;
    @Transactional
    public MemberJoinResponseDto signup(MemberJoinDto memberJoinDto) {
        if(memberRepository.existsByEmail(memberJoinDto.getEmail())){
            throw new CustomException(ALREADY_SAVED_MEMBER);
        }

        Member member = memberJoinDto.toMember(passwordEncoder);
        memberRepository.save(member);

        return new MemberJoinResponseDto(member.getEmail());
    }

    @Transactional
    public TokenDto login(MemberLoginDto memberLoginDto){
        // 1. Login ID/PW 를 기반으로 AuthenticationToken 생성
        UsernamePasswordAuthenticationToken authenticationToken = memberLoginDto.toAuthentication();

        Member member = memberRepository.findByEmail(memberLoginDto.getEmail()).orElseThrow(() ->
                new CustomException(MEMBER_EMAIL_NOT_FOUND));

        // 2. 실제로 검증 (사용자 비밀번호 체크) 이 이루어지는 부분
        //    authenticate 메서드가 실행이 될 때 CustomUserDetailsService 에서 만들었던 loadUserByUsername 메서드가 실행됨
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        // 3. 인증 정보를 기반으로 JWT 토큰 생성
        TokenDto tokenDto = tokenProvider.generateTokenDto(authentication);
        // 5. 토큰 발급
        return tokenDto;
    }

    @Transactional
    public TokenDto reissue(TokenRequestDto tokenRequestDto){
        // 1. Refresh Token 검증
        if (!tokenProvider.validateToken(tokenRequestDto.getRefreshToken())) {
            throw new CustomException(INVALID_REFRESH_TOKEN);
        }

        // 2. Access Token 에서 Member ID 가져오기
        Authentication authentication = tokenProvider.getAuthentication(tokenRequestDto.getAccessToken());

        // redis에 있는 refreshToken과 비교
        tokenProvider.checkRefreshToken(authentication.getName(), tokenRequestDto.getRefreshToken());

        // 5. 새로운 토큰 생성
        TokenDto tokenDto = tokenProvider.generateTokenDto(authentication);


        // 토큰 발급
        return tokenDto;
    }
}
