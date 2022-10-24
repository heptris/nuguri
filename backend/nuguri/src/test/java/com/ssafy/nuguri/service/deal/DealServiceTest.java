package com.ssafy.nuguri.service.deal;

import com.ssafy.nuguri.dto.deal.DealDetailDto;
import com.ssafy.nuguri.dto.deal.DealLoginDetailDto;
import com.ssafy.nuguri.dto.deal.DealRegistRequestDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
class DealServiceTest {

    @Autowired
    DealService dealService;

    @Test
    public void 중고거래등록() throws Exception{
        //given
        DealRegistRequestDto dealRegistRequestDto = new DealRegistRequestDto(7L, "연필 팔아용", "연필이 아주 야무집니다",
                1000);

        dealService.dealRegist(1L, dealRegistRequestDto, null);

     }

    @Test
    public void 비로그인시중고거래상세페이지() throws Exception{
        DealDetailDto dealDetail = dealService.findDealDetail(4L);
        System.out.println("dealDetail = " + dealDetail);
    }

    @Test
    public void 로그인시중고거래상세페이지() throws Exception{
        DealLoginDetailDto loginDealDetail = dealService.findLoginDealDetail(2L, 4L);
        System.out.println("loginDealDetail = " + loginDealDetail);
    }

}