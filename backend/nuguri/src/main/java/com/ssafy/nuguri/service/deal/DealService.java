package com.ssafy.nuguri.service.deal;

import com.ssafy.nuguri.dto.deal.DealDetailDto;
import com.ssafy.nuguri.dto.deal.DealListDto;
import com.ssafy.nuguri.dto.deal.DealLoginDetailDto;
import com.ssafy.nuguri.exception.ex.CustomException;
import com.ssafy.nuguri.repository.deal.DealRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class DealService {

    private final DealRepository dealRepository;

    public List<DealListDto> findLocalCategoryDealList(Long localId, Long categoryId){
        return dealRepository.findLocalCategoryDealList(localId, categoryId);
    }

    /*
        비로그인시 중고거래 detail
     */
    public DealDetailDto findDealDetail(Long dealId){
        // Exception 처리 ??
        return dealRepository.dealDetail(dealId);
    }

    /*
        로그인시 중고거래 detail
     */
    public DealLoginDetailDto findLoginDealDetail(Long memberId, Long dealId){

        DealDetailDto dealDetailDto = dealRepository.dealDetail(dealId);
        boolean isDealFavorite = dealRepository.findIsDealFavorite(memberId, dealId);

        return DealLoginDetailDto.builder()
                .dealId(dealDetailDto.getDealId())
                .title(dealDetailDto.getTitle())
                .description(dealDetailDto.getDescription())
                .price(dealDetailDto.getPrice())
                .hit(dealDetailDto.getHit())
                .isDeal(dealDetailDto.isDeal())
                .dealImage(dealDetailDto.getDealImage())
                .dong(dealDetailDto.getDong())
                .isFavorite(isDealFavorite)
                .sellerId(dealDetailDto.getSellerId())
                .build();
    }


}
