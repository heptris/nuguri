package com.ssafy.nuguri.service.hobby;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.nuguri.alarm.dto.HobbyAlarmEventDto;
import com.ssafy.nuguri.domain.baseaddress.BaseAddress;
import com.ssafy.nuguri.domain.category.Category;
import com.ssafy.nuguri.domain.hobby.ApproveStatus;
import com.ssafy.nuguri.domain.hobby.Hobby;
import com.ssafy.nuguri.domain.hobby.HobbyHistory;
import com.ssafy.nuguri.domain.member.Member;
import com.ssafy.nuguri.dto.hobby.ChangeStatusRequestDto;
import com.ssafy.nuguri.dto.hobby.HobbyHistoryDto;
import com.ssafy.nuguri.dto.hobby.HobbyHistoryResponseDto;
import com.ssafy.nuguri.repository.baseaddress.BaseaddressRepository;
import com.ssafy.nuguri.repository.category.CategoryRepository;
import com.ssafy.nuguri.repository.hobby.HobbyHistoryRepository;
import com.ssafy.nuguri.repository.hobby.HobbyRepository;
import com.ssafy.nuguri.repository.member.MemberRepository;
import com.ssafy.nuguri.util.SecurityUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.jdbc.Sql;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static com.ssafy.nuguri.domain.alarm.AlarmCode.HOBBY_OWNER_ALARM;
import static com.ssafy.nuguri.domain.baseaddress.QBaseAddress.baseAddress;
import static com.ssafy.nuguri.domain.category.QCategory.category;
import static com.ssafy.nuguri.domain.hobby.QHobby.hobby;
import static com.ssafy.nuguri.domain.hobby.QHobbyHistory.hobbyHistory;
import static com.ssafy.nuguri.domain.member.QMember.member;
import static java.lang.Boolean.FALSE;

@SpringBootTest
@Transactional
@Sql("classpath:tableInit.sql")
class HobbyHistoryServiceTest {
    @Autowired
    HobbyHistoryService hobbyHistoryService;
    @Autowired
    HobbyRepository hobbyRepository;
    @Autowired
    HobbyHistoryRepository hobbyHistoryRepository;

    @Autowired
    EntityManager em;
    JPAQueryFactory queryFactory;

    @Test
    @BeforeEach
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
        Member me1 = queryFactory // 25살, m, 청운동
                .selectFrom(member)
                .where(member.id.eq(1L))
                .fetchOne();
        Member me2 = queryFactory // 25살, m, 청운동
                .selectFrom(member)
                .where(member.id.eq(2L))
                .fetchOne();
        Member me3 = queryFactory // 25살, m, 청운동
                .selectFrom(member)
                .where(member.id.eq(3L))
                .fetchOne();


        Hobby hobbyEntity1 = Hobby.builder()
                .baseAddress(ba)
                .category(ca)
                .title("취미방 1")
                .content("1번방 입니다")
                .endDate(LocalDateTime.now().plusDays(10))
                .meetingPlace("서울에서 모여요")
                .isClosed(false)
                .curNum(1)
                .maxNum(10)
                .fee(10)
                .ageLimit(10)
                .sexLimit('f')
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
                .curNum(2)
                .maxNum(20)
                .fee(20)
                .ageLimit(20)
                .sexLimit('m')
                .hobbyImage("bb")
                .build();

        HobbyHistory hobbyHistory1 = HobbyHistory.builder().member(me1).hobby(hobbyEntity1).isPromoter(true).approveStatus(ApproveStatus.READY).build();
        HobbyHistory hobbyHistory2 = HobbyHistory.builder().member(me1).hobby(hobbyEntity2).isPromoter(false).approveStatus(ApproveStatus.READY).build();
        HobbyHistory hobbyHistory3 = HobbyHistory.builder().member(me2).hobby(hobbyEntity1).isPromoter(true).approveStatus(ApproveStatus.APPROVE).build();
        HobbyHistory hobbyHistory4 = HobbyHistory.builder().member(me2).hobby(hobbyEntity2).isPromoter(false).approveStatus(ApproveStatus.APPROVE).build();
        HobbyHistory hobbyHistory5 = HobbyHistory.builder().member(me3).hobby(hobbyEntity1).isPromoter(false).approveStatus(ApproveStatus.REJECT).build();


        em.persist(hobbyEntity1);
        em.persist(hobbyEntity2);
        em.persist(hobbyHistory1);
        em.persist(hobbyHistory2);
        em.persist(hobbyHistory3);
        em.persist(hobbyHistory4);
        em.persist(hobbyHistory5);
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1");

        List<HobbyHistory> result = hobbyHistoryRepository.findAll();
        for (HobbyHistory m :result
             ) {
            System.out.println(m.getId());
        }
    }

    @Test
    public void 취미1_승인_대기자(){
        List<HobbyHistoryDto> result = hobbyHistoryService.findWaitingMemberList(1L);
        for (HobbyHistoryDto h: result
        ) {
            System.out.println("취미1 승인 대기자: " + h);
        }

    }

    @Test
    public void 취미1_참여자() {
        System.out.println("====취미1에 참여중인 사람====");

        List<HobbyHistoryDto> result2 = hobbyHistoryService.findParticipantList(1L);
        for (HobbyHistoryDto h: result2

        ) {
            System.out.println("취미1 참여자: " + h);
        }

    }

    @Test
    public void 멤버1_참여_현황() {

        List<HobbyHistoryResponseDto> result4 = hobbyHistoryService.findStatusHobbyList(1L, ApproveStatus.APPROVE);
        for (HobbyHistoryResponseDto h: result4

        ) {
            System.out.println("멤버1의 참여방: " + h);
        }

    }

    @Test
    public void 멤버1_대기_현황() {

        List<HobbyHistoryResponseDto> result4 = hobbyHistoryService.findStatusHobbyList(1L, ApproveStatus.READY);
        for (HobbyHistoryResponseDto h: result4

        ) {
            System.out.println("멤버1의 대기방: " + h);
        }

    }

    @Test
    public void 멤버1_거절_현황(){
        List<HobbyHistoryResponseDto> result5 = hobbyHistoryService.findStatusHobbyList(1L, ApproveStatus.REJECT);
        for (HobbyHistoryResponseDto h: result5

        ) {
            System.out.println("멤버1의 거절방: " + h);
        }
    }

    @Test
    public void 취미방_정보_변경(){
        HobbyHistoryDto before = hobbyHistoryService.findByIdDto(1L);
        System.out.println("변경 전: "+before.toString());
        ChangeStatusRequestDto changeStatusRequestDto = ChangeStatusRequestDto.builder()
                .hobbyId(before.getHobbyId())
                .participantId(before.getMemberId())
                .approveStatus(ApproveStatus.REJECT)
                .build();

        hobbyHistoryService.changeStatus(changeStatusRequestDto);

        System.out.println("변경 후: "+hobbyHistoryService.findByIdDto(1L).toString());
    }

}