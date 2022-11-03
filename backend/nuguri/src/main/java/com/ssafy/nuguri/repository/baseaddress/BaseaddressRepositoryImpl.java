package com.ssafy.nuguri.repository.baseaddress;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.nuguri.domain.baseaddress.BaseAddress;
import com.ssafy.nuguri.domain.baseaddress.QBaseAddress;
import com.ssafy.nuguri.dto.baseaddress.BaseAddressResponseDto;
import com.ssafy.nuguri.dto.baseaddress.BaseAddressSearchDto;

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
}
