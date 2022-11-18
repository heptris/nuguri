package com.ssafy.nuguri.repository.deal;

import com.ssafy.nuguri.domain.deal.DealHistory;
import com.ssafy.nuguri.domain.deal.DealStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DealHistoryRepository extends JpaRepository<DealHistory, Long>, DealHistoryRepositoryCustom {
    DealHistory findByMemberIdAndDealId(Long memberId, Long dealId);
    List<DealHistory> findByMemberIdAndDealStatus(Long memberId, DealStatus dealStatus);
}
