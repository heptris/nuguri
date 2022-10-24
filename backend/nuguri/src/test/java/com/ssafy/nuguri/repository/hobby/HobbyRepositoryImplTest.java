package com.ssafy.nuguri.repository.hobby;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.nuguri.domain.baseaddress.BaseAddress;
import com.ssafy.nuguri.domain.category.Category;
import com.ssafy.nuguri.domain.hobby.Hobby;
import com.ssafy.nuguri.domain.hobby.HobbyHistory;
import com.ssafy.nuguri.dto.hobby.HobbyDto;
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
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class HobbyRepositoryImplTest {
    @Autowired
    EntityManager em;
    JPAQueryFactory queryFactory;

    @Test
    @BeforeEach
//    @Commit
    public void before(){

        queryFactory = new JPAQueryFactory(em);

        BaseAddress ba = queryFactory
                .selectFrom(baseAddress)
                .where(baseAddress.id.eq(1L))
                .fetchOne();
        Category ca = queryFactory
                .selectFrom(category)
                .where(category.id.eq(1L))
                .fetchOne();



        Hobby hobbyEntity1 = Hobby.builder()
                .baseAddress(ba)
                .category(ca)
                .title("취미방 1")
                .content("1번방 입니다")
                .endDate(LocalDateTime.now())
                .meetingPlace("서울에서 모여요")
                .isClosed(false)
                .curNum(10)
                .maxNum(10)
                .fee(10)
                .ageLimit(10)
                .sexLimit((char)1)
                .hobbyImage("aa")
                .build();

        Hobby hobbyEntity2 = Hobby.builder()
                .baseAddress(ba)
                .category(ca)
                .title("취미방 2")
                .content("2번방 입니다")
                .endDate(LocalDateTime.now())
                .meetingPlace("부산에서 모여요")
                .isClosed(false)
                .curNum(20)
                .maxNum(20)
                .fee(20)
                .ageLimit(20)
                .sexLimit((char)2)
                .hobbyImage("bb")
                .build();


        em.persist(hobbyEntity1);
        em.persist(hobbyEntity2);
    }


    public List<HobbyDto> findByRegion(Long RegionId) {

        List<HobbyDto> hobbyDtoList = queryFactory.select(Projections.constructor(HobbyDto.class,
                        hobby.id,
                        baseAddress.id,
                        category.id,
                        hobby.title,
                        hobby.content,
                        hobby.endDate,
                        hobby.meetingPlace,
                        hobby.isClosed,
                        hobby.curNum,
                        hobby.maxNum,
                        hobby.fee,
                        hobby.ageLimit,
                        hobby.sexLimit,
                        hobby.hobbyImage
                ))
                .from(hobby)
//                .innerJoin()
//                .innerJoin()
                .where(category.id.eq(RegionId))
                .fetch();
        return hobbyDtoList;
    }


    public List<HobbyDto> findByRegionAndCategory(Long RegionId, Long CategoryId) {
        List<HobbyDto> hobbyDtoList = queryFactory.select(Projections.constructor(HobbyDto.class,
                        hobby.id,
                        baseAddress.id,
                        category.id,
                        hobby.title,
                        hobby.content,
                        hobby.endDate,
                        hobby.meetingPlace,
                        hobby.isClosed,
                        hobby.curNum,
                        hobby.maxNum,
                        hobby.fee,
                        hobby.ageLimit,
                        hobby.sexLimit,
                        hobby.hobbyImage
                ))
                .from(hobby)
//                  .innerJoin()
//                  .innerJoin()
                .where(
                        category.id.eq(RegionId)
                                .and(baseAddress.id.eq(RegionId))
                )
                .fetch();
        return hobbyDtoList;
    }

    public HobbyDto hobbyDetail(Long HobbyId) {
        HobbyDto hobbyDto = queryFactory.select(Projections.constructor(HobbyDto.class,
                        hobby.id,
                        baseAddress.id,
                        category.id,
                        hobby.title,
                        hobby.content,
                        hobby.endDate,
                        hobby.meetingPlace,
                        hobby.isClosed,
                        hobby.curNum,
                        hobby.maxNum,
                        hobby.fee,
                        hobby.ageLimit,
                        hobby.sexLimit,
                        hobby.hobbyImage
                ))
                .from(hobby)
                .where(
                        hobby.id.eq(HobbyId)
                )
                .fetchOne();
        return hobbyDto;
    }

    public void createHobby(HobbyDto hobbyDto) {

    }



    @Test
    public void func(){
        System.out.println("findByRegion: " + findByRegion(1L));
        System.out.println("findByRegionandCategory: "+ findByRegionAndCategory(1L,1L));
        System.out.println("findHobbyDetail: "+ hobbyDetail(5L));
        System.out.println("취미방 생성 시도");
        createHobby(HobbyDto.builder()
                .localId(9L)
                .categoryId(5L)
                .title("생성 테스트")
                .content("만들기")
                .endDate(LocalDateTime.now())
                .meetingPlace("어디서 만날까요")
                .isClosed(false)
                .curNum(30)
                .maxNum(30)
                .fee(30)
                .ageLimit(30)
                .sexLimit((char)1)
                .hobbyImage("cc")
                .build());
        System.out.println("취미방 생성 확인: "+findByRegion(9L));

    }


}