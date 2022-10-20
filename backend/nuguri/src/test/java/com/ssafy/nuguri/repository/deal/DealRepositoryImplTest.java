package com.ssafy.nuguri.repository.deal;

import com.ssafy.nuguri.dto.deal.DealDetailDto;
import com.ssafy.nuguri.dto.deal.DealListDto;
import com.ssafy.nuguri.dto.deal.DealLoginDetailDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import java.util.List;
import java.util.Optional;


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

    @Test
    public void 중고거래상세페이지() throws Exception{
        Optional<DealDetailDto> dealDetailDto = dealRepository.dealDetail(2L);
        System.out.println("dealDetailDto = " + dealDetailDto);
    }

//    @Test
//    public void 로그인시중고거래상세페이지() throws Exception{
//        //given
//        Optional<DealLoginDetailDto> dealLoginDetailDto = dealRepository.dealLoginDetail(1L, 3L);
//
//        System.out.println("dealLoginDetailDto = " + dealLoginDetailDto);
//
//     }

    @Test
    public void 즐겨찾기확인() throws Exception{

        boolean bool1 = dealRepository.findIsDealFavorite(1L, 2L);
        boolean bool2 = dealRepository.findIsDealFavorite(1L, 4L);
        boolean bool3 = dealRepository.findIsDealFavorite(4L, 2L);

        //true
        System.out.println("bool1 = " + bool1);
        //true
        System.out.println("bool2 = " + bool2);
        //false
        System.out.println("bool3 = " + bool3);

    }

}