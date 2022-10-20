package com.ssafy.nuguri.service.member;

import antlr.Token;
import com.ssafy.nuguri.domain.member.Member;
import com.ssafy.nuguri.dto.member.MemberJoinDto;
import com.ssafy.nuguri.dto.member.MemberJoinResponseDto;
import com.ssafy.nuguri.dto.member.MemberLoginDto;
import com.ssafy.nuguri.dto.token.TokenDto;
import com.ssafy.nuguri.exception.ex.CustomException;
import com.ssafy.nuguri.exception.ex.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.ssafy.nuguri.exception.ex.ErrorCode.ALREADY_SAVED_MEMBER;
import static com.ssafy.nuguri.exception.ex.ErrorCode.MEMBER_EMAIL_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final PasswordEncoder passwordEncoder;
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

        Member member = memberRepository.findByMemberEmail(memberLoginDto.getEmail()).orElseThrow(() ->
                new CustomException(MEMBER_EMAIL_NOT_FOUND));

        // 2. 실제로 검증 (사용자 비밀번호 체크) 이 이루어지는 부분
        //    authenticate 메서드가 실행이 될 때 CustomUserDetailsService 에서 만들었던 loadUserByUsername 메서드가 실행됨
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        // 3. 인증 정보를 기반으로 JWT 토큰 생성
        TokenDto tokenDto = tokenProvider.generateTokenDto(authentication);
        member.updateMemberStatus(MemberStatus.ONLINE);
        // 5. 토큰 발급
        return tokenDto;
    }
}
