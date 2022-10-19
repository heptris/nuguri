package com.ssafy.nuguri.repository.deal;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.nuguri.dto.deal.DealListDto;

import javax.persistence.EntityManager;
import java.util.List;

import static com.ssafy.nuguri.domain.baseaddress.QBaseAddress.baseAddress;
import static com.ssafy.nuguri.domain.category.QCategory.category;
import static com.ssafy.nuguri.domain.deal.QDeal.deal;

public class DealRepositoryImpl implements DealRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    public DealRepositoryImpl(EntityManager em){
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<DealListDto> findLocalCategoryDealList(Long localId, Long categoryId) {
        Tuple tuple = queryFactory
                .select(baseAddress.lat,
                        baseAddress.lng
                )
                .from(baseAddress)
                .where(baseAddress.id.eq(localId))
                .fetchOne();

        double lat = Double.parseDouble(tuple.get(baseAddress.lat));
        System.out.println("lat = " + lat);

        double lng = Double.parseDouble(tuple.get(baseAddress.lng));
        System.out.println("lng = " + lng);

        List<DealListDto> dealListDtoList = queryFactory.select(Projections.constructor(DealListDto.class,
                        deal.id,
                        category.id,
                        baseAddress.id,
                        deal.title,
                        deal.description,
                        deal.price,
                        deal.hit,
                        deal.isDeal,
                        deal.dealImage
                ))
                .from(baseAddress)
                .innerJoin(baseAddress.dealList, deal)
                .innerJoin(deal.category, category)
                .where(baseAddress.lat.between(Double.toString(lat - 0.01), Double.toString(lat + 0.01))
                        .and(baseAddress.lng.between(Double.toString(lng - 0.01), Double.toString(lng + 0.01)))
                        .and(category.id.eq(categoryId)))
                .fetch();

        return dealListDtoList;
    }
}
