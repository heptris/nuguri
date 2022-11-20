package com.ssafy.nuguri.repository.deal;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.nuguri.domain.deal.QDealHistory;

import javax.persistence.EntityManager;

import static com.ssafy.nuguri.domain.deal.QDealHistory.dealHistory;

public class DealHistoryRepositoryImpl implements DealHistoryRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    public DealHistoryRepositoryImpl(EntityManager em){
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public int countDealHistoryByDealId(Long dealId) {
        int size = queryFactory.select(dealHistory.id)
                .from(dealHistory)
                .where(dealHistory.deal.id.eq(dealId))
                .fetch().size();
        return size;
    }
}
