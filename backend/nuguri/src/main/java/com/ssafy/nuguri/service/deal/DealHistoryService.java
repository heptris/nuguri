package com.ssafy.nuguri.service.deal;

import com.ssafy.nuguri.domain.deal.Deal;
import com.ssafy.nuguri.domain.deal.DealHistory;
import com.ssafy.nuguri.domain.deal.DealStatus;
import com.ssafy.nuguri.domain.member.Member;
import com.ssafy.nuguri.dto.deal.DealChatFavCountDto;
import com.ssafy.nuguri.dto.deal.DealFinishedDto;
import com.ssafy.nuguri.dto.deal.DealHistoryResponseDto;
import com.ssafy.nuguri.dto.deal.DealHistoryUpdateDto;
import com.ssafy.nuguri.exception.ex.CustomException;
import com.ssafy.nuguri.exception.ex.ErrorCode;
import com.ssafy.nuguri.repository.deal.DealFavoriteRepository;
import com.ssafy.nuguri.repository.deal.DealHistoryRepository;
import com.ssafy.nuguri.repository.deal.DealRepository;
import com.ssafy.nuguri.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.ssafy.nuguri.exception.ex.ErrorCode.*;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class DealHistoryService {

    private final DealHistoryRepository dealHistoryRepository;
    private final MemberRepository memberRepository;
    private final DealRepository dealRepository;
    private final DealFavoriteRepository dealFavoriteRepository;

    @Transactional
    public DealHistoryResponseDto createDealHistory(Long memberId, Long dealId){
        // 이미 채팅을 했던 것에 대한 중복 로그 쌓임 방지를 위한 중복 체크
        DealHistory duplicateCheckDealRepository = dealHistoryRepository.findByMemberIdAndDealId(memberId, dealId);
        // 중복일 경우
        if(duplicateCheckDealRepository != null){
            return DealHistoryResponseDto.builder()
                    .dealHistoryId(duplicateCheckDealRepository.getId())
                    .isDuplicated(true)
                    .build();
//            throw new CustomException(ALREADY_USED_DEAL_HISTORY);
        }

        Member member = memberRepository.findById(memberId)
                .orElseThrow(()->new CustomException(ErrorCode.MEMBER_NOT_FOUND));
        Deal deal = dealRepository.findById(dealId)
                        .orElseThrow(()->new CustomException(ErrorCode.DEAL_NOT_FOUND));

        DealHistory dealHistory = DealHistory.builder()
                .member(member)
                .deal(deal)
                .dealStatus(DealStatus.AWAITER)
                .build();
        dealHistoryRepository.save(dealHistory);

        DealHistoryResponseDto dealHistoryResponseDto = DealHistoryResponseDto.builder()
                .dealHistoryId(dealHistory.getId())
                .isDuplicated(false)
                .build();
        return dealHistoryResponseDto;
    }

    @Transactional
    public void updateToReserver(DealHistoryUpdateDto dealHistoryUpdateDto){
        DealHistory dealHistory = dealHistoryRepository.
                findByMemberIdAndDealId(dealHistoryUpdateDto.getBuyerId(), dealHistoryUpdateDto.getDealId());
        if(dealHistory == null) throw new CustomException(DEAL_HISTORY_NOT_FOUND);

        dealHistory.updateDealHistory(DealStatus.RESERVER,
                dealHistoryUpdateDto.getPromiseTime(), dealHistoryUpdateDto.getPromiseLocation());
    }

    @Transactional
    public void dealFinished(DealFinishedDto dealFinishedDto){
        Deal deal = dealRepository.findById(dealFinishedDto.getDealId())
                .orElseThrow(()->new CustomException(DEAL_NOT_FOUND));
        deal.finishDeal();

        DealHistory dealHistory = dealHistoryRepository.
                findByMemberIdAndDealId(dealFinishedDto.getBuyerId(), dealFinishedDto.getDealId());
        if(dealHistory == null) throw new CustomException(DEAL_HISTORY_NOT_FOUND);

        dealHistory.dealFinished();
    }

    public DealChatFavCountDto countDealChatFav(Long dealId){
        int chatCnt = dealHistoryRepository.countDealHistoryByDealId(dealId);
        int favCnt = dealFavoriteRepository.countDealFavoriteByDealId(dealId);

        return DealChatFavCountDto.builder()
                .favCnt(favCnt)
                .chatCnt(chatCnt)
                .build();
    }
}
