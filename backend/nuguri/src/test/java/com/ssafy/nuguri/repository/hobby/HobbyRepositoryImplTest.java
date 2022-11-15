<<<<<<< HEAD
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
    JPAQueryFactory queryFactory = new JPAQueryFactory(em);

//    @Test
//    @BeforeEach
////    @Commit
//    public void before(){
//
//        queryFactory = new JPAQueryFactory(em);
//
//        BaseAddress ba1 = queryFactory
//                .selectFrom(baseAddress)
//                .where(baseAddress.id.eq(1L))
//                .fetchOne();
//        Category ca1 = queryFactory
//                .selectFrom(category)
//                .where(category.id.eq(1L))
//                .fetchOne();
//
//        BaseAddress ba2 = queryFactory
//                .selectFrom(baseAddress)
//                .where(baseAddress.id.eq(40L))
//                .fetchOne();
//        Category ca2 = queryFactory
//                .selectFrom(category)
//                .where(category.id.eq(17L))
//                .fetchOne();
//
//        Hobby hobbyEntity1 = Hobby.builder()
//                .baseAddress(ba1)
//                .category(ca1)
//                .title("취미방 1")
//                .content("1번방 입니다")
//                .endDate(LocalDateTime.now())
//                .meetingPlace("서울에서 모여요")
//                .isClosed(false)
//                .curNum(10)
//                .maxNum(10)
//                .fee(10)
//                .ageLimit(10)
//                .sexLimit((char)1)
//                .hobbyImage("aa")
//                .build();
//
//        Hobby hobbyEntity2 = Hobby.builder()
//                .baseAddress(ba2)
//                .category(ca2)
//                .title("취미방 2")
//                .content("2번방 입니다")
//                .endDate(LocalDateTime.now())
//                .meetingPlace("부산에서 모여요")
//                .isClosed(false)
//                .curNum(20)
//                .maxNum(20)
//                .fee(20)
//                .ageLimit(20)
//                .sexLimit((char)2)
//                .hobbyImage("bb")
//                .build();
//        Hobby hobbyEntity3 = Hobby.builder()
//                .baseAddress(ba1)
//                .category(ca2)
//                .title("취미방 3")
//                .content("3번방 입니다")
//                .endDate(LocalDateTime.now())
//                .meetingPlace("부산에서 모여요")
//                .isClosed(false)
//                .curNum(20)
//                .maxNum(20)
//                .fee(20)
//                .ageLimit(20)
//                .sexLimit((char)2)
//                .hobbyImage("bb")
//                .build();
//        Hobby hobbyEntity4 = Hobby.builder()
//                .baseAddress(ba1)
//                .category(ca1)
//                .title("취미방 4")
//                .content("4번방 입니다")
//                .endDate(LocalDateTime.now())
//                .meetingPlace("1번방이랑 모든 내용이 동일해요")
//                .isClosed(false)
//                .curNum(10)
//                .maxNum(10)
//                .fee(10)
//                .ageLimit(10)
//                .sexLimit((char)1)
//                .hobbyImage("aa")
//                .build();
//
//        em.persist(hobbyEntity1);
//        em.persist(hobbyEntity2);
//        em.persist(hobbyEntity3);
//        em.persist(hobbyEntity4);
//    }


    @Test
    public void 지역으로_찾기(){
        List<HobbyDto> result = hobbyRepository.findByRegionAndCategory(1L,null);
        for (HobbyDto a: result) {
            System.out.println("결과값: " + a.toString());
        }
    }

    @Test
    public void 지역과_카테고리로_찾기(){
        List<HobbyDto> result = hobbyRepository.findByRegionAndCategory(1L,1L);
        for (HobbyDto a: result) {
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

=======
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
        List<HobbyDto> result = hobbyRepository.findByRegionAndCategory(1L,null);
        for (HobbyDto a: result) {
            System.out.println("결과값: " + a.toString());
        }
    }

    @Test
    public void 지역과_카테고리로_찾기(){
        List<HobbyDto> result = hobbyRepository.findByRegionAndCategory(1L,1L);
        for (HobbyDto a: result) {
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

>>>>>>> f421eec681e1a56973f6516f24bedec91e81add4
}