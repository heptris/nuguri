package com.ssafy.nuguri.repository.deal;

import com.ssafy.nuguri.domain.deal.DealHistory;

public interface DealHistoryRepositoryCustom {
    int countDealHistoryByDealId(Long dealId);
}
