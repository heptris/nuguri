package com.ssafy.nuguri.repository.deal;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.nuguri.domain.deal.QDealFavorite;

import javax.persistence.EntityManager;

import static com.ssafy.nuguri.domain.deal.QDealFavorite.dealFavorite;
import static java.lang.Boolean.TRUE;

public class DealFavoriteRepositoryImpl implements DealFavoriteRepositoryCustom{
    private final JPAQueryFactory queryFactory;

    public DealFavoriteRepositoryImpl(EntityManager em){
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public int countDealFavoriteByDealId(Long dealId) {
        int size = queryFactory.select(dealFavorite.id)
                .from(dealFavorite)
                .where(dealFavorite.deal.id.eq(dealId).and(dealFavorite.isFavorite.isTrue()))
                .fetch().size();

        return size;
    }
}
