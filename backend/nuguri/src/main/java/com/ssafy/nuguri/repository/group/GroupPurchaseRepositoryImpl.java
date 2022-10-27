package com.ssafy.nuguri.repository.group;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.nuguri.domain.category.QCategory;
import com.ssafy.nuguri.domain.group.QGroupPurchase;
import com.ssafy.nuguri.dto.group.GroupPurchaseListDto;

import javax.persistence.EntityManager;
import java.util.List;

import static com.ssafy.nuguri.domain.baseaddress.QBaseAddress.baseAddress;
import static com.ssafy.nuguri.domain.category.QCategory.category;
import static com.ssafy.nuguri.domain.group.QGroupPurchase.groupPurchase;

public class GroupPurchaseRepositoryImpl implements GroupPurchaseRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    public GroupPurchaseRepositoryImpl(EntityManager em){
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<GroupPurchaseListDto> findGroupByLocalAndCategory(Long localId, Long categoryId) {
        double[] latLng = findLatLng(localId);
        double lat = latLng[0];
        double lng = latLng[1];
        List<GroupPurchaseListDto> groupPurchaseListDtoList = queryFactory.select(Projections.constructor(GroupPurchaseListDto.class,
                        groupPurchase.id,
                        category.id,
                        groupPurchase.title,
                        groupPurchase.description,
                        groupPurchase.productUrl,
                        groupPurchase.endDate,
                        groupPurchase.goalNumber,
                        groupPurchase.reservedNumber,
                        groupPurchase.price,
                        groupPurchase.groupPurchaseImage,
                        baseAddress.dong
                ))
                .from(baseAddress)
                .innerJoin(baseAddress.groupPurchaseList, groupPurchase)
                .innerJoin(groupPurchase.category, category)
                .where(baseAddress.lat.between(Double.toString(lat - 0.01), Double.toString(lat + 0.01))
                        .and(baseAddress.lng.between(Double.toString(lng - 0.01), Double.toString(lng + 0.01)))
                        .and(category.id.eq(categoryId)))
                .fetch();

        return groupPurchaseListDtoList;
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
