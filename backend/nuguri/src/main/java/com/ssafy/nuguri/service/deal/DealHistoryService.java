package com.ssafy.nuguri.service.deal;

import com.ssafy.nuguri.domain.deal.Deal;
import com.ssafy.nuguri.domain.deal.DealHistory;
import com.ssafy.nuguri.domain.deal.DealStatus;
import com.ssafy.nuguri.domain.member.Member;
import com.ssafy.nuguri.dto.deal.DealHistoryUpdateDto;
import com.ssafy.nuguri.exception.ex.CustomException;
import com.ssafy.nuguri.exception.ex.ErrorCode;
import com.ssafy.nuguri.repository.deal.DealHistoryRepository;
import com.ssafy.nuguri.repository.deal.DealRepository;
import com.ssafy.nuguri.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.ssafy.nuguri.exception.ex.ErrorCode.ALREADY_USED_DEALHISTORY;
import static com.ssafy.nuguri.exception.ex.ErrorCode.DEALHISTORY_NOT_FOUND;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class DealHistoryService {

    private final DealHistoryRepository dealHistoryRepository;
    private final MemberRepository memberRepository;
    private final DealRepository dealRepository;

    @Transactional
    public void createDealHistory(Long memberId, Long dealId){
        Member member = memberRepository.findById(memberId)
                .orElseThrow(()->new CustomException(ErrorCode.MEMBER_NOT_FOUND));
        Deal deal = dealRepository.findById(dealId)
                        .orElseThrow(()->new CustomException(ErrorCode.DEAL_NOT_FOUND));

        DealHistory dealHistory = DealHistory.builder()
                .member(member)
                .deal(deal)
                .dealStatus(DealStatus.AWAITER)
                .build();

        // 이미 채팅을 했던 것에 대한 중복 로그 쌓임 방지를 위한 Exception
        DealHistory duplicateCheckDealRepository = dealHistoryRepository.findByMemberIdAndDealId(memberId, dealId);
        if(duplicateCheckDealRepository != null)throw new CustomException(ALREADY_USED_DEALHISTORY);

        dealHistoryRepository.save(dealHistory);
    }

    @Transactional
    public void updateToReserver(DealHistoryUpdateDto dealHistoryUpdateDto){
        DealHistory dealHistory = dealHistoryRepository.
                findByMemberIdAndDealId(dealHistoryUpdateDto.getBuyerId(), dealHistoryUpdateDto.getDealId());
        if(dealHistory == null) throw new CustomException(DEALHISTORY_NOT_FOUND);

        dealHistory.updateDealHistory(DealStatus.RESERVER,
                dealHistoryUpdateDto.getPromiseTime(), dealHistoryUpdateDto.getPromiseLocation());
    }

}
