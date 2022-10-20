package com.ssafy.nuguri.repository.deal;

import com.ssafy.nuguri.dto.deal.DealDetailDto;
import com.ssafy.nuguri.dto.deal.DealLoginDetailDto;
import com.ssafy.nuguri.dto.deal.DealListDto;

import java.util.List;

public interface DealRepositoryCustom {
    List<DealListDto> findLocalCategoryDealList(Long localId, Long categoryId);

    DealDetailDto dealDetail(Long dealId);

    boolean findIsDealFavorite(Long memberId, Long dealId);
}
