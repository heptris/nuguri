package com.ssafy.nuguri.repository.deal;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.nuguri.domain.deal.QDealFavorite;
import com.ssafy.nuguri.domain.member.QMember;
import com.ssafy.nuguri.dto.deal.DealDetailDto;
import com.ssafy.nuguri.dto.deal.DealLoginDetailDto;
import com.ssafy.nuguri.dto.deal.DealListDto;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

import static com.ssafy.nuguri.domain.baseaddress.QBaseAddress.baseAddress;
import static com.ssafy.nuguri.domain.category.QCategory.category;
import static com.ssafy.nuguri.domain.deal.QDeal.deal;
import static com.ssafy.nuguri.domain.deal.QDealFavorite.dealFavorite;
import static com.ssafy.nuguri.domain.member.QMember.member;

public class DealRepositoryImpl implements DealRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    public DealRepositoryImpl(EntityManager em){
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<DealListDto> findLocalCategoryDealList(Long localId, Long categoryId) {
        double[] latLng = findLatLng(localId);
        double lat = latLng[0];
        double lng = latLng[1];

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

    @Override
    public Optional<DealDetailDto> dealDetail(Long dealId) {
        return Optional.ofNullable(queryFactory
                .select(Projections.constructor(DealDetailDto.class,
                        deal.id,
                        deal.title,
                        deal.description,
                        deal.price,
                        deal.hit,
                        deal.isDeal,
                        deal.dealImage,
                        baseAddress.dong,
                        deal.member.id
                ))
                .from(deal)
                .innerJoin(deal.baseAddress, baseAddress)
                .where(deal.id.eq(dealId))
                .fetchOne());
    }

//    @Override
//    public Optional<DealLoginDetailDto> dealLoginDetail(Long memberId, Long dealId) {
//        return Optional.ofNullable(queryFactory
//                .select(Projections.constructor(DealLoginDetailDto.class,
//                        deal.id,
//                        deal.title,
//                        deal.description,
//                        deal.price,
//                        deal.hit,
//                        deal.isDeal,
//                        deal.dealImage,
//                        baseAddress.dong,
//                        dealFavorite.isFavorite,
//                        deal.member.id
//                ))
//                .from(member)
//                .innerJoin(member.dealFavoriteList, dealFavorite)
//                .innerJoin(dealFavorite.deal, deal)
//                .innerJoin(deal.baseAddress, baseAddress)
//                .where(member.id.eq(memberId).and(deal.id.eq(dealId)))
//                .fetchOne());
//    }

    @Override
    public boolean findIsDealFavorite(Long memberId, Long dealId) {
        Boolean isFavorite = queryFactory
                .select(dealFavorite.isFavorite)
                .from(member)
                .innerJoin(member.dealFavoriteList, dealFavorite)
                .innerJoin(dealFavorite.deal, deal)
                .where(member.id.eq(memberId).and(deal.id.eq(dealId)))
                .fetchOne();
        if(isFavorite == null){
            return false;
        }
        return isFavorite;
    }

    public double[] findLatLng(Long localId){
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
        return new double[] {lat, lng};
    }
}
