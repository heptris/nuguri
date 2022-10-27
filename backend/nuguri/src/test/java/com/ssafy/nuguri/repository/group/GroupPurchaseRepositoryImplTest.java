package com.ssafy.nuguri.repository.group;

import com.ssafy.nuguri.dto.group.GroupPurchaseListDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class GroupPurchaseRepositoryImplTest {
    @Autowired
    GroupPurchaseRepository groupPurchaseRepository;

    @Test
    public void 공동구매리스트() throws Exception{
        List<GroupPurchaseListDto> groupByLocalAndCategory = groupPurchaseRepository.findGroupByLocalAndCategory(1L, 8L);
        for (GroupPurchaseListDto groupPurchaseListDto : groupByLocalAndCategory) {
            System.out.println("groupPurchaseListDto = " + groupPurchaseListDto);
        }
    }
}