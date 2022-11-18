package com.ssafy.nuguri.repository.deal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class DealFavoriteRepositoryTest {

    @Autowired
    DealFavoriteRepository dealFavoriteRepository;

    @Test
    public void 즐겨찾기개수세기() throws Exception{
        int i = dealFavoriteRepository.countDealFavoriteByDealId(2L);
        System.out.println("i = " + i);
    }



}