package com.ssafy.nuguri.repository.deal;

import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;

public class DealHistoryRepositoryImpl implements DealHistoryRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    public DealHistoryRepositoryImpl(EntityManager em){
        this.queryFactory = new JPAQueryFactory(em);
    }
}
