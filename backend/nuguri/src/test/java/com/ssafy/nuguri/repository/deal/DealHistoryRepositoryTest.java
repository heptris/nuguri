package com.ssafy.nuguri.repository.deal;

import com.ssafy.nuguri.domain.deal.DealHistory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
class DealHistoryRepositoryTest {

    @Autowired
    DealHistoryRepository dealHistoryRepository;

    @Test
    public void 중고거래기록확인() throws Exception{
        //given
        DealHistory dealHistory = dealHistoryRepository.findByMemberIdAndDealId(1L, 4L);
//        System.out.println("dealHistory.getId() = " + dealHistory.getId());
        if(dealHistory == null){
            System.out.println("나 없다~~~~~!!");
        }
     }

     @Test
     public void 해당중고거래채팅개수() throws Exception{
         int i = dealHistoryRepository.countDealHistoryByDealId(4L);
         System.out.println("i = " + i);
     }

}