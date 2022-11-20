package com.ssafy.nuguri.repository.baseaddress;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.nuguri.domain.baseaddress.BaseAddress;
import com.ssafy.nuguri.domain.baseaddress.QBaseAddress;
import com.ssafy.nuguri.dto.baseaddress.*;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

import static com.ssafy.nuguri.domain.baseaddress.QBaseAddress.baseAddress;

public class BaseaddressRepositoryImpl implements BaseaddressRepositoryCustom{
    private final JPAQueryFactory queryFactory;

    public BaseaddressRepositoryImpl(EntityManager em){
        this.queryFactory = new JPAQueryFactory(em);
    }


    @Override
    public List<BaseAddressResponseDto> findBaseaddressByDong(String keyword) {
        List<BaseAddressSearchDto> baseAddressSearchDtoList = queryFactory.
                select(Projections.constructor(BaseAddressSearchDto.class,
                        baseAddress.id,
                        baseAddress.sido,
                        baseAddress.gugun,
                        baseAddress.dong
                ))
                .from(baseAddress)
//                성능 비교 : 280ms
//                .where(baseAddress.dong.like("%"+keyword+"%"))
//                성능 비교 : 257ms
                .where(baseAddress.dong.contains(keyword))
                .fetch();
        List<BaseAddressResponseDto> list = new ArrayList<>();
        for (BaseAddressSearchDto baseAddressSearchDto : baseAddressSearchDtoList) {
            list.add(baseAddressSearchDto.toBaseAddressResponseDto());
        }

        return list;
    }

    @Override
    public List<BaseAddressSidoDto> findSidoList() {
        List<BaseAddressSidoDto> baseAddressSidoDtoList = queryFactory
                .select(Projections.constructor(BaseAddressSidoDto.class,
                        baseAddress.sido
                ))
                .from(baseAddress)
                .groupBy(baseAddress.sido)
                .fetch();

        return baseAddressSidoDtoList;
    }

    @Override
    public List<BaseAddressGugunDto> findGugunList(String sido) {
        List<BaseAddressGugunDto> baseAddressGugunDtoList = queryFactory
                .select(Projections.constructor(BaseAddressGugunDto.class,
                        baseAddress.gugun
                ))
                .from(baseAddress)
                .where(baseAddress.sido.eq(sido))
                .groupBy(baseAddress.gugun)
                .fetch();

        return baseAddressGugunDtoList;
    }

    @Override
    public List<BaseAddressDongDto> findDongList(String gugun) {
        List<BaseAddressDongDto> baseAddressDongDtoList = queryFactory
                .select(Projections.constructor(BaseAddressDongDto.class,
                        baseAddress.id,
                        baseAddress.dong
                ))
                .from(baseAddress)
                .where(baseAddress.gugun.eq(gugun))
                .fetch();

        return baseAddressDongDtoList;
    }

    @Override
    public List<BaseAddressDto> findAllBaseAddress() {
        List<BaseAddressDto> baseAddressDtoList = queryFactory
                .select(Projections.constructor(BaseAddressDto.class,
                        baseAddress.id,
                        baseAddress.sido,
                        baseAddress.gugun,
                        baseAddress.dong,
                        baseAddress.lat,
                        baseAddress.lng
                ))
                .from(baseAddress)
                .fetch();
        return baseAddressDtoList;
    }
}
