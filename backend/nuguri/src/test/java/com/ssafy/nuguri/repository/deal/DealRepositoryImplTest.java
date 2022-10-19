package com.ssafy.nuguri.repository.deal;

import com.ssafy.nuguri.dto.deal.DealListDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
class DealRepositoryImplTest {

    @Autowired
    DealRepository dealRepository;

    @Test
    public void 중고거래목록() throws Exception{
        List<DealListDto> localCategoryDealList = dealRepository.findLocalCategoryDealList(2L, 8L);
        for (DealListDto dealListDto : localCategoryDealList) {
            System.out.println("dealListDto = " + dealListDto);
        }

    }


}