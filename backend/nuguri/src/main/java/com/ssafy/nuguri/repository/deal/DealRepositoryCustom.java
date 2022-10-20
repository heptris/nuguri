package com.ssafy.nuguri.repository.deal;

import com.ssafy.nuguri.dto.deal.DealDetailDto;
import com.ssafy.nuguri.dto.deal.DealLoginDetailDto;
import com.ssafy.nuguri.dto.deal.DealListDto;

import java.util.List;
import java.util.Optional;

public interface DealRepositoryCustom {
    List<DealListDto> findLocalCategoryDealList(Long localId, Long categoryId);

    Optional<DealDetailDto> dealDetail(Long dealId);

//    즐겨찾기를 안 할 경우 deal_favorite이 아예 생기지 않기 때문에 NULL 반환이 됨
//    Optional<DealLoginDetailDto> dealLoginDetail(Long memberId, Long dealId);

    boolean findIsDealFavorite(Long memberId, Long dealId);
}
