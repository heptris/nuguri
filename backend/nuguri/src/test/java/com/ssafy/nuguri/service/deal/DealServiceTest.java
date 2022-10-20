package com.ssafy.nuguri.service.deal;

import com.ssafy.nuguri.dto.deal.DealDetailDto;
import com.ssafy.nuguri.dto.deal.DealLoginDetailDto;
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