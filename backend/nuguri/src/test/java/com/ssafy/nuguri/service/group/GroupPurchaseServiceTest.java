package com.ssafy.nuguri.service.group;

import com.ssafy.nuguri.dto.group.GroupPurchaseRegistRequestDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import javax.transaction.Transactional;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
class GroupPurchaseServiceTest {

    @Autowired
    GroupPurchaseService groupPurchaseService;

    @Test
    @Commit
    public void 공동구매등록하기() throws Exception{
        groupPurchaseService.registGroupPurchase(GroupPurchaseRegistRequestDto.builder()
                        .categoryId(8L)
                        .title("연필 공구요")
                        .description("굉장히 싸게 구매가능")
                        .productUrl("https://www.erdcloud.com/d/K6k6QgsxRuZQyktkC")
                        .endDate(LocalDateTime.now().plusDays(1))
                        .goalNumber(5)
                        .price(2000)
                .build(),null);
     }

}