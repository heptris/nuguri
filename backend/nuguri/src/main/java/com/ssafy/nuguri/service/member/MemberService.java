package com.ssafy.nuguri.service.member;

import com.ssafy.nuguri.domain.member.Member;
import com.ssafy.nuguri.dto.member.MemberProfileDto;
import com.ssafy.nuguri.exception.ex.CustomException;
import com.ssafy.nuguri.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.ssafy.nuguri.exception.ex.ErrorCode.MEMBER_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public MemberProfileDto profile(Long memberId){
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new CustomException(MEMBER_NOT_FOUND));

        MemberProfileDto memberProfileResponseDto = MemberProfileDto.builder()
                .email(member.getEmail())
                .name(member.getName())
                .nickname(member.getNickname())
                .description(member.getDescription())
                .temperature(member.getTemperature())
                .profileImage(member.getProfileImage())
                .baseAddress(member.getBaseAddress())
                .build();

        return memberProfileResponseDto;
    }

//    @Transactional
//    public void profileFeed(){
//        return;
//    }

    @Transactional
    public
}
