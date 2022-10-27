package com.ssafy.nuguri.service.member;

import com.ssafy.nuguri.domain.deal.Deal;
import com.ssafy.nuguri.domain.deal.DealFavorite;
import com.ssafy.nuguri.domain.deal.DealHistory;
import com.ssafy.nuguri.domain.deal.DealStatus;
import com.ssafy.nuguri.domain.member.Member;
import com.ssafy.nuguri.dto.deal.DealListDto;
import com.ssafy.nuguri.dto.hobby.HobbyDto;
import com.ssafy.nuguri.dto.member.MemberProfileDto;
import com.ssafy.nuguri.dto.member.MemberProfileRequestDto;
import com.ssafy.nuguri.exception.ex.CustomException;
import com.ssafy.nuguri.repository.deal.DealFavoriteRepository;
import com.ssafy.nuguri.repository.deal.DealHistoryRepository;
import com.ssafy.nuguri.repository.deal.DealRepository;
import com.ssafy.nuguri.repository.hobby.HobbyHistoryRepository;
import com.ssafy.nuguri.repository.hobby.HobbyRepository;
import com.ssafy.nuguri.repository.member.MemberRepository;
import com.ssafy.nuguri.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static com.ssafy.nuguri.exception.ex.ErrorCode.MEMBER_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final HobbyHistoryRepository hobbyHistoryRepository;
    private final DealRepository dealRepository;
    private final DealFavoriteRepository dealFavoriteRepository;
    private final DealHistoryRepository dealHistoryRepository;

    @Transactional
    public MemberProfileDto profile(MemberProfileRequestDto requestDto){
        MemberProfileDto memberProfileDto = null;
        if(requestDto.getNickname() != null){
            Member member = memberRepository.findByNickname(requestDto.getNickname()).orElseThrow(() -> new CustomException(MEMBER_NOT_FOUND));

            memberProfileDto = profileCreate(memberProfileDto, member);
        } else {
            Long memberId = SecurityUtil.getCurrentMemberId();
            Member member = memberRepository.findById(memberId).orElseThrow(() -> new CustomException(MEMBER_NOT_FOUND));

            memberProfileDto = profileCreate(memberProfileDto, member);
        }

        return memberProfileDto;
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

    /**
     * 중고 거래 (판매 중)
     */
    @Transactional
    public List<DealListDto> profileDealOnSale(MemberProfileRequestDto requestDto){
        List<DealListDto> dtoList = new ArrayList<>();

        if(requestDto.getNickname() != null){
            Member member = memberRepository.findByNickname(requestDto.getNickname()).orElseThrow(() -> new CustomException(MEMBER_NOT_FOUND));

            Long memberId = member.getId();
            List<Deal> dealList = dealRepository.findByMemberIdAndIsDeal(memberId, false);
            for(Deal d : dealList){
                convertToDto(dtoList, d);
            }
        } else {
            Long memberId = SecurityUtil.getCurrentMemberId();

            List<Deal> dealList = dealRepository.findByMemberIdAndIsDeal(memberId, false);
            for(Deal d : dealList){
                convertToDto(dtoList, d);
            }
        }
        return dtoList;
    }

    /**
     * 중고 거래 (판매 완료)
     */
    @Transactional
    public List<DealListDto> profileDealSoldOut(MemberProfileRequestDto requestDto){
        Long memberId = SecurityUtil.getCurrentMemberId();

        List<Deal> dealList = dealRepository.findByMemberIdAndIsDeal(memberId, true);
        List<DealListDto> dtoList = new ArrayList<>();
        for(Deal d : dealList){
            convertToDto(dtoList, d);
        }
        return dtoList;
    }
    /**
     * 중고 거래 (구매 완료)
     */
    @Transactional
    public List<DealListDto> profileDealPurchase(MemberProfileRequestDto requestDto){
        Long memberId = SecurityUtil.getCurrentMemberId();

        List<DealHistory> dealList = dealHistoryRepository.findByMemberIdAndDealStatus(memberId, DealStatus.BUYER);
        List<DealListDto> dtoList = new ArrayList<>();
        for(DealHistory dh : dealList){
            Deal d = dh.getDeal();
            convertToDto(dtoList, d);
        }
        return dtoList;
    }
    /**
     * 중고 거래 (찜)
     */
    @Transactional
    public List<DealListDto> profileDealFavorite(MemberProfileRequestDto requestDto){
        Long memberId = SecurityUtil.getCurrentMemberId();

        List<DealFavorite> dealList = dealFavoriteRepository.findByMemberIdAndIsFavorite(memberId, true);
        List<DealListDto> dtoList = new ArrayList<>();
        for(DealFavorite df : dealList){
            Deal d = df.getDeal();
            convertToDto(dtoList, d);
        }
        return dtoList;
    }

//    @Transactional
//    public 공동구매


    private void convertToDto(List<DealListDto> dtoList, Deal d) {
        dtoList.add(DealListDto.builder()
                .dealId(d.getId())
                .categoryId(d.getCategory().getId())
                .localId(d.getBaseAddress().getId())
                .title(d.getTitle())
                .description(d.getDescription())
                .price(d.getPrice())
                .hit(d.getHit())
                .isDeal(d.isDeal())
                .dealImage(d.getDealImage())
                .build());
    }

    private MemberProfileDto profileCreate(MemberProfileDto memberProfileDto, Member member){
        memberProfileDto = MemberProfileDto.builder()
                .email(member.getEmail())
                .name(member.getName())
                .nickname(member.getNickname())
                .description(member.getDescription())
                .temperature(member.getTemperature())
                .profileImage(member.getProfileImage())
                .baseAddress(member.getBaseAddress())
                .build();
        return memberProfileDto;
    }


    @Transactional
    public boolean existsByNickname(String nickName){
        return memberRepository.existsByNickname(nickName);
    }

}
