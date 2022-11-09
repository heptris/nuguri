package com.ssafy.nuguri.service.member;

import com.ssafy.nuguri.config.redis.RedisService;
import com.ssafy.nuguri.domain.deal.Deal;
import com.ssafy.nuguri.domain.deal.DealFavorite;
import com.ssafy.nuguri.domain.deal.DealHistory;
import com.ssafy.nuguri.domain.deal.DealStatus;
import com.ssafy.nuguri.domain.hobby.ApproveStatus;
import com.ssafy.nuguri.domain.member.Member;
import com.ssafy.nuguri.dto.deal.DealListDto;
import com.ssafy.nuguri.dto.hobby.HobbyHistoryResponseDto;
import com.ssafy.nuguri.dto.member.MemberModifyDto;
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

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

import static com.ssafy.nuguri.exception.ex.ErrorCode.MEMBER_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final HobbyRepository hobbyRepository;
    private final DealRepository dealRepository;
    private final DealFavoriteRepository dealFavoriteRepository;
    private final DealHistoryRepository dealHistoryRepository;

    private final RedisService redisService;

    /**
     * 회원 프로필 조회
     */
    @Transactional
    public MemberProfileDto profile(MemberProfileRequestDto requestDto){
        MemberProfileDto memberProfileDto;

        // 다른 회원 프로필 조회
        if(requestDto.getNickname() != null){
            Member member = memberRepository.findByNickname(requestDto.getNickname()).orElseThrow(() -> new CustomException(MEMBER_NOT_FOUND));

            memberProfileDto = profileCreate(member);
        }

        // 본인 프로필 조회
        else {
            Long memberId = SecurityUtil.getCurrentMemberId();
            Member member = memberRepository.findById(memberId).orElseThrow(() -> new CustomException(MEMBER_NOT_FOUND));

            memberProfileDto = profileCreate(member);
        }

        return memberProfileDto;
    }

    @Transactional
    public MemberModifyDto profileModify(MemberModifyDto requestDto){
        Long memberId = SecurityUtil.getCurrentMemberId();
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new CustomException(MEMBER_NOT_FOUND));

        member.modify(requestDto.getNickname());
        redisService.setValues(String.valueOf(memberId) + ".", requestDto.getNickname());
        return new MemberModifyDto(requestDto.getNickname());
    }

//    @Transactional
//    public void profileFeed(){
//        return;
//    }

    /**
     * 취미 모임방 (대기 중)
     */
    @Transactional
    public List<HobbyHistoryResponseDto> profileHobbyReady(MemberProfileRequestDto requestDto){
        List<HobbyHistoryResponseDto> hobbyHistoryResponseDtoList = new ArrayList<>();
        if (requestDto.getNickname() != null) {
            Member member = memberRepository.findByNickname(requestDto.getNickname()).orElseThrow(() -> new CustomException(MEMBER_NOT_FOUND));
            Long memberId = member.getId();

            hobbyHistoryResponseDtoList = hobbyRepository.findByMemberIdAndStatus(memberId, ApproveStatus.READY);
        } else {
            Long memberId = SecurityUtil.getCurrentMemberId();

            hobbyHistoryResponseDtoList = hobbyRepository.findByMemberIdAndStatus(memberId, ApproveStatus.READY);
        }
        return hobbyHistoryResponseDtoList;
    }

    /**
     * 취미 모임방 (참여 중)
     */
    @Transactional
    public List<HobbyHistoryResponseDto> profileHobbyParticipation(MemberProfileRequestDto requestDto){
        List<HobbyHistoryResponseDto> hobbyHistoryResponseDtoList = new ArrayList<>();
        if (requestDto.getNickname() != null) {
            Member member = memberRepository.findByNickname(requestDto.getNickname()).orElseThrow(() -> new CustomException(MEMBER_NOT_FOUND));
            Long memberId = member.getId();

            hobbyHistoryResponseDtoList = hobbyRepository.findByMemberIdAndStatus(memberId, ApproveStatus.APPROVE);
        } else {
            Long memberId = SecurityUtil.getCurrentMemberId();

            hobbyHistoryResponseDtoList = hobbyRepository.findByMemberIdAndStatus(memberId, ApproveStatus.APPROVE);
        }
        return hobbyHistoryResponseDtoList;
    }

    /**
     * 취미 모임방 (운영 중)
     */
    @Transactional
    public List<HobbyHistoryResponseDto> profileHobbyManage(MemberProfileRequestDto requestDto){
        List<HobbyHistoryResponseDto> hobbyHistoryResponseDtoList = new ArrayList<>();
        if (requestDto.getNickname() != null) {
            Member member = memberRepository.findByNickname(requestDto.getNickname()).orElseThrow(() -> new CustomException(MEMBER_NOT_FOUND));
            Long memberId = member.getId();

            hobbyHistoryResponseDtoList = hobbyRepository.findByMemberIdAndPromoter(memberId, true);
        } else {
            Long memberId = SecurityUtil.getCurrentMemberId();

            hobbyHistoryResponseDtoList = hobbyRepository.findByMemberIdAndPromoter(memberId, true);
        }
        return hobbyHistoryResponseDtoList;
    }

    /**
     * 취미 모임방 (찜)
     */
    @Transactional
    public List<HobbyHistoryResponseDto> profileHobbyFavorite(MemberProfileRequestDto requestDto){
        List<HobbyHistoryResponseDto> hobbyHistoryResponseDtoList = new ArrayList<>();
        if (requestDto.getNickname() != null) {
            Member member = memberRepository.findByNickname(requestDto.getNickname()).orElseThrow(() -> new CustomException(MEMBER_NOT_FOUND));
            Long memberId = member.getId();

            hobbyHistoryResponseDtoList = hobbyRepository.findByMemberIdAndFavorite(memberId, true);
        } else {
            Long memberId = SecurityUtil.getCurrentMemberId();

            hobbyHistoryResponseDtoList = hobbyRepository.findByMemberIdAndFavorite(memberId, true);
        }
        return hobbyHistoryResponseDtoList;
    }


    /**
     * 중고 거래 (판매 중)
     */
    @Transactional
    public List<DealListDto> profileDealOnSale(MemberProfileRequestDto requestDto){
        List<DealListDto> dtoList = new ArrayList<>();

        // 다른 회원 프로필 조회
        if(requestDto.getNickname() != null){
            Member member = memberRepository.findByNickname(requestDto.getNickname()).orElseThrow(() -> new CustomException(MEMBER_NOT_FOUND));

            Long memberId = member.getId();
            List<Deal> dealList = dealRepository.findByMemberIdAndIsDeal(memberId, false);
            for(Deal d : dealList){
                convertToDto(dtoList, d);
            }
        }
        // 본인 프로필 조회
        else {
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
        List<DealListDto> dtoList = new ArrayList<>();

        // 다른 회원 프로필 조회
        if(requestDto.getNickname() != null){
            Member member = memberRepository.findByNickname(requestDto.getNickname()).orElseThrow(() -> new CustomException(MEMBER_NOT_FOUND));
            Long memberId = member.getId();

            List<Deal> dealList = dealRepository.findByMemberIdAndIsDeal(memberId, true);
            for(Deal d : dealList){
                convertToDto(dtoList, d);
            }
        }

        // 본인 프로필 조회
        else {
            Long memberId = SecurityUtil.getCurrentMemberId();

            List<Deal> dealList = dealRepository.findByMemberIdAndIsDeal(memberId, true);
            for(Deal d : dealList){
                convertToDto(dtoList, d);
            }
        }
        return dtoList;
    }
    /**
     * 중고 거래 (구매 완료)
     */
    @Transactional
    public List<DealListDto> profileDealPurchase(MemberProfileRequestDto requestDto){
        List<DealListDto> dtoList = new ArrayList<>();

        // 다른 회원 프로필 조회
        if(requestDto.getNickname() != null){
            Member member = memberRepository.findByNickname(requestDto.getNickname()).orElseThrow(() -> new CustomException(MEMBER_NOT_FOUND));
            Long memberId = member.getId();

            List<DealHistory> dealList = dealHistoryRepository.findByMemberIdAndDealStatus(memberId, DealStatus.BUYER);
            for(DealHistory dh : dealList){
                Deal d = dh.getDeal();
                convertToDto(dtoList, d);
            }
        }

        // 본인 프로필 조회
        else {
            Long memberId = SecurityUtil.getCurrentMemberId();

            List<DealHistory> dealList = dealHistoryRepository.findByMemberIdAndDealStatus(memberId, DealStatus.BUYER);
            for(DealHistory dh : dealList){
                Deal d = dh.getDeal();
                convertToDto(dtoList, d);
            }
        }
        return dtoList;
    }
    /**
     * 중고 거래 (찜)
     */
    @Transactional
    public List<DealListDto> profileDealFavorite(MemberProfileRequestDto requestDto){
        List<DealListDto> dtoList = new ArrayList<>();

        // 다른 회원 프로필 조회
        if(requestDto.getNickname() != null){
            Member member = memberRepository.findByNickname(requestDto.getNickname()).orElseThrow(() -> new CustomException(MEMBER_NOT_FOUND));
            Long memberId = member.getId();

            List<DealFavorite> dealList = dealFavoriteRepository.findByMemberIdAndIsFavorite(memberId, true);
            for(DealFavorite df : dealList){
                Deal d = df.getDeal();
                convertToDto(dtoList, d);
            }
        }

        // 본인 프로필 조회
        else {
            Long memberId = SecurityUtil.getCurrentMemberId();

            List<DealFavorite> dealList = dealFavoriteRepository.findByMemberIdAndIsFavorite(memberId, true);
            for(DealFavorite df : dealList){
                Deal d = df.getDeal();
                convertToDto(dtoList, d);
            }
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

    private MemberProfileDto profileCreate(Member member){
        MemberProfileDto memberProfileDto = MemberProfileDto.builder()
                .memberId(member.getId())
                .email(member.getEmail())
                .name(member.getName())
                .nickname(member.getNickname())
                .description(member.getDescription())
                .temperature(member.getTemperature())
                .profileImage(member.getProfileImage())
                .baseAddress(member.getBaseAddress().getSido() + " " + member.getBaseAddress().getGugun() + " " + member.getBaseAddress().getDong())
                .localId(member.getBaseAddress().getId())
                .build();
        return memberProfileDto;
    }


    @Transactional
    public boolean existsByNickname(String nickName){
        return memberRepository.existsByNickname(nickName);
    }

    @PostConstruct
    public void init() {
      //  System.out.println("실행됨??");
        List<Member> memberList = memberRepository.findAll();
        System.out.println(memberList.size());
        memberList.forEach(member -> {
            System.out.println("@@@@@@@@@@");
            System.out.println(member.getId());
            redisService.setValues(String.valueOf(member.getId()) + ".", member.getNickname());
        });
    }

}
