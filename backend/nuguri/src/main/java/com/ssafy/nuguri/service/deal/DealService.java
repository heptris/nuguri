package com.ssafy.nuguri.service.deal;

import com.ssafy.nuguri.dto.deal.DealListDto;
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


}
