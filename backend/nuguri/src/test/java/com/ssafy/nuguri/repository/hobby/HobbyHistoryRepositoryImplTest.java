package com.ssafy.nuguri.repository.hobby;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.nuguri.domain.baseaddress.BaseAddress;
import com.ssafy.nuguri.domain.category.Category;
import com.ssafy.nuguri.domain.hobby.ApproveStatus;
import com.ssafy.nuguri.domain.hobby.Hobby;
import com.ssafy.nuguri.domain.hobby.HobbyHistory;
import com.ssafy.nuguri.domain.member.Member;
import com.ssafy.nuguri.dto.hobby.HobbyDto;
import com.ssafy.nuguri.dto.hobby.HobbyHistoryDto;
import com.ssafy.nuguri.dto.hobby.HobbyStatusDto;
import com.ssafy.nuguri.repository.member.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

import static com.ssafy.nuguri.domain.baseaddress.QBaseAddress.baseAddress;
import static com.ssafy.nuguri.domain.category.QCategory.category;
import static com.ssafy.nuguri.domain.hobby.QHobby.hobby;
import static com.ssafy.nuguri.domain.hobby.QHobbyHistory.hobbyHistory;
import static com.ssafy.nuguri.domain.member.QMember.member;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
@Sql("classpath:tableInit.sql")
class HobbyHistoryRepositoryImplTest {
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
        Member me = queryFactory
                .selectFrom(member)
                .where(member.id.eq(1L))
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

        HobbyHistory hobbyHistory1 = HobbyHistory.builder().member(me).hobby(hobbyEntity1).isPromoter(true).approveStatus(ApproveStatus.READY).build();
        HobbyHistory hobbyHistory2 = HobbyHistory.builder().member(me).hobby(hobbyEntity1).isPromoter(false).approveStatus(ApproveStatus.READY).build();
        HobbyHistory hobbyHistory3 = HobbyHistory.builder().member(me).hobby(hobbyEntity1).isPromoter(true).approveStatus(ApproveStatus.APPROVE).build();
        HobbyHistory hobbyHistory4 = HobbyHistory.builder().member(me).hobby(hobbyEntity1).isPromoter(false).approveStatus(ApproveStatus.APPROVE).build();
        HobbyHistory hobbyHistory5 = HobbyHistory.builder().member(me).hobby(hobbyEntity1).isPromoter(false).approveStatus(ApproveStatus.REJECT).build();

        em.persist(hobbyEntity1);
        em.persist(hobbyEntity2);
        em.persist(hobbyHistory1);
        em.persist(hobbyHistory2);
        em.persist(hobbyHistory3);
        em.persist(hobbyHistory4);
        em.persist(hobbyHistory5);

    }



    public List<HobbyHistoryDto> waiter(Long hobbyId) {
        List<HobbyHistoryDto> hobbyHistoryDtoList = queryFactory.select(Projections.constructor(HobbyHistoryDto.class,
                        hobbyHistory.id,
                        hobby.id,
                        member.id,
                        hobbyHistory.isPromoter,
                        hobbyHistory.approveStatus
                ))
                .from(hobbyHistory)
                .innerJoin(hobbyHistory.hobby,hobby)
                .innerJoin(hobbyHistory.member,member)
                .where(hobby.id.eq(hobbyId)
                        .and(hobbyHistory.approveStatus.eq(ApproveStatus.READY)))
                .fetch();
        return hobbyHistoryDtoList;
    }
    @Test
    public void 승인_대기자_목록(){
        List<HobbyHistoryDto> result = waiter(1L);
        for (HobbyHistoryDto m :result
             ) {
            System.out.println("hobbyId 1을 대기중인 멤버: "+ m);
        }
    }


    public List<HobbyHistoryDto> participant(Long hobbyId) {
        List<HobbyHistoryDto> hobbyHistoryDtoList = queryFactory.select(Projections.constructor(HobbyHistoryDto.class,
                        hobbyHistory.id,
                        hobby.id,
                        member.id,
                        hobbyHistory.isPromoter,
                        hobbyHistory.approveStatus
                ))
                .from(hobbyHistory)
                .innerJoin(hobbyHistory.hobby,hobby)
                .innerJoin(hobbyHistory.member,member)
                .where(hobby.id.eq(hobbyId)
                        .and(hobbyHistory.approveStatus.eq(ApproveStatus.APPROVE)))
                .fetch();
        return hobbyHistoryDtoList;
    }
    @Test
    public void 취미방_참가자_목록(){
        List<HobbyHistoryDto> result = participant(1L);
        for (HobbyHistoryDto m :result
        ) {
            System.out.println("hobbyId 1에 참여중인 멤버: "+ m);
        }
    }


    public boolean changeStatus(Long hobbyHistoryId, ApproveStatus status) {
        queryFactory.selectFrom(hobbyHistory)
                .where(hobbyHistory.id.eq(hobbyHistoryId))
                .fetchOne()
                .updateApproveStatus(status);
        return false;
    }
    @Test
    public void 취미참여자_상태_변경(){
        HobbyHistory testData = queryFactory.selectFrom(hobbyHistory)
                .where(hobbyHistory.id.eq(1L))
                .fetchOne();
        System.out.println("변경 전: "+testData.getApproveStatus());
        changeStatus(testData.getId(),ApproveStatus.REJECT);
        System.out.println("변경 후: "+testData.getApproveStatus());

    }


    public List<HobbyStatusDto> findByStatus(Long userId, ApproveStatus status) {
        List<HobbyStatusDto> hobbyStatusDtoList = queryFactory.select(Projections.constructor(HobbyStatusDto.class,
                        hobby.id,
                        category.id,
                        hobby.title,
                        hobby.endDate,
                        hobby.curNum,
                        hobby.maxNum,
                        hobby.curNum, // wishlistNum으로 변경
                        hobby.curNum, // chatNum으로 변경
                        hobby.hobbyImage,
                        hobbyHistory.approveStatus
                ))
                .from(hobbyHistory)
                .innerJoin(hobbyHistory.hobby, hobby)
                .innerJoin(hobby.category, category)
                .where(hobbyHistory.member.id.eq(userId)
                        .and(hobbyHistory.approveStatus.eq(status)))
                .fetch();

        return hobbyStatusDtoList;
    }
    @Test
    public void 상태별_취미방_목록(){
        List<HobbyStatusDto> ready = findByStatus(1L,ApproveStatus.READY);
        List<HobbyStatusDto> reject = findByStatus(1L,ApproveStatus.REJECT);
        List<HobbyStatusDto> approve = findByStatus(1L,ApproveStatus.APPROVE);
        for (HobbyStatusDto data: ready) {
            System.out.println("승인 대기중인 신청: "+data);
        }
        for (HobbyStatusDto data: reject) {
            System.out.println("승인 거절된 신청: "+data);
        }
        for (HobbyStatusDto data: approve) {
            System.out.println("승인된 신청: "+data);
        }

    }
    public HobbyHistoryDto findByIdDto(Long hobbyHistoryId) {
        HobbyHistoryDto hobbyHistoryDto = queryFactory
                .select(Projections.constructor(HobbyHistoryDto.class,
                        hobbyHistory.id,
                        hobby.id,
                        member.id,
                        hobbyHistory.isPromoter,
                        hobbyHistory.approveStatus
                ))
                .from(hobbyHistory)
                .innerJoin(hobbyHistory.hobby,hobby)
                .innerJoin(hobbyHistory.member,member)
                .where(hobbyHistory.id.eq(hobbyHistoryId))
                .fetchOne();
        return hobbyHistoryDto;
    }
    @Test
    public void Id로_값_찾기(){
        System.out.println(findByIdDto(1L).toString());
    }


}