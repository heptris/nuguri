package com.ssafy.nuguri.service.member;

import com.ssafy.nuguri.domain.deal.Deal;
import com.ssafy.nuguri.domain.member.Member;
import com.ssafy.nuguri.dto.deal.DealListDto;
import com.ssafy.nuguri.dto.hobby.HobbyDto;
import com.ssafy.nuguri.dto.member.MemberProfileDto;
import com.ssafy.nuguri.exception.ex.CustomException;
import com.ssafy.nuguri.repository.deal.DealRepository;
import com.ssafy.nuguri.repository.hobby.HobbyHistoryRepository;
import com.ssafy.nuguri.repository.hobby.HobbyRepository;
import com.ssafy.nuguri.repository.member.MemberRepository;
import com.ssafy.nuguri.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.ssafy.nuguri.exception.ex.ErrorCode.MEMBER_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final HobbyHistoryRepository hobbyHistoryRepository;
    private final DealRepository dealRepository;

    @Transactional
    public MemberProfileDto profile(){
        Long memberId = SecurityUtil.getCurrentMemberId();
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

//    @Transactional
//    public List<HobbyDto> profileHobby(){
//        Long memberId = SecurityUtil.getCurrentMemberId();
//        return hobbyHistoryRepository.findByMemberId(memberId);
//    }
//
//    @Transactional
//    public List<DealListDto> profileDeal(){
//        Long memberId = SecurityUtil.getCurrentMemberId();
//
//        List<Deal> dealList = dealRepository.findByMemberId(memberId);
//        for(Deal d : dealList){
//
//        }
//    }

//    @Transactional
//    public 공동구매


    @Transactional
    public boolean existsByNickname(String nickName){
        return memberRepository.existsByNickname(nickName);
    }

}
