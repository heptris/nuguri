package com.ssafy.nuguri.repository.hobby;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.nuguri.domain.baseaddress.BaseAddress;
import com.ssafy.nuguri.domain.category.Category;
import com.ssafy.nuguri.domain.hobby.ApproveStatus;
import com.ssafy.nuguri.domain.hobby.Hobby;
import com.ssafy.nuguri.domain.hobby.HobbyHistory;
import com.ssafy.nuguri.dto.hobby.HobbyDto;
import com.ssafy.nuguri.dto.hobby.HobbyHistoryRegionCategoryRequestDto;
import com.ssafy.nuguri.dto.hobby.HobbyHistoryResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

import static com.ssafy.nuguri.domain.baseaddress.QBaseAddress.baseAddress;
import static com.ssafy.nuguri.domain.category.QCategory.category;
import static com.ssafy.nuguri.domain.hobby.QHobby.hobby;
import static com.ssafy.nuguri.domain.hobby.QHobbyFavorite.hobbyFavorite;
import static com.ssafy.nuguri.domain.hobby.QHobbyHistory.hobbyHistory;
import static com.ssafy.nuguri.domain.member.QMember.member;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class HobbyRepositoryImplTest {
    @Autowired
    EntityManager em;
    @Autowired
    HobbyRepositoryImpl hobbyRepository;

    @Test
    public void 지역으로_찾기(){
        List<HobbyHistoryRegionCategoryRequestDto> result = hobbyRepository.findByRegionAndCategory(1L,null);
        for (HobbyHistoryRegionCategoryRequestDto a: result) {
            System.out.println("결과값: " + a.toString());
        }
    }

    @Test
    public void 지역과_카테고리로_찾기(){
        List<HobbyHistoryRegionCategoryRequestDto> result = hobbyRepository.findByRegionAndCategory(1L,1L);
        for (HobbyHistoryRegionCategoryRequestDto a: result) {
            System.out.println("결과값: " + a.toString());
        }
    }


    @Test
    public void 취미방_상세보기(){
        HobbyDto result = hobbyRepository.hobbyDetail(1L);
        if(result!=null){
            System.out.println("취미방 상세보기: "+ result.toString());
        }
    }

}
