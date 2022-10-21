package com.ssafy.nuguri.repository.deal;

import com.ssafy.nuguri.domain.deal.DealHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DealHistoryRepository extends JpaRepository<DealHistory, Long> {
    DealHistory findByMemberIdAndDealId(Long memberId, Long dealId);
}
