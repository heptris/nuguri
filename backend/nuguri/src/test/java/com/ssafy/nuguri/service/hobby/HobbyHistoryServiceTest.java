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
    HobbyHistoryRepository hobbyHistoryRepository;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    HobbyRepository hobbyRepository;
    @Autowired
    BaseaddressRepository baseaddressRepository;
    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    EntityManager em;
    JPAQueryFactory queryFactory;

    @Test
    @BeforeEach
    public void before() {
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
        List<Hobby> result = hobbyRepository.findAll();
        for (Hobby m : result
        ) {
            System.out.println(m.getId());
        }
    }

    public Long createHobbyHistory(Long hobbyId) { // 취미방 생성 또는 참여신청 // 신청만
        Member member = new Member();
        member.changeMemberId(SecurityUtil.getCurrentMemberId());

        Hobby hobby = hobbyRepository.findById(hobbyId).orElseThrow();

        HobbyHistory hobbyHistoryEntity = HobbyHistory.builder()
                .member(member)
                .hobby(hobby)
                .isPromoter(false)
                .approveStatus(ApproveStatus.READY)
                .build();
        return hobbyHistoryRepository.save(hobbyHistoryEntity).getId();
    }

    public List<HobbyHistoryDto> findWaitingMemberList(Long hobbyId) { // 해당 취미방 신청 대기자
        return hobbyHistoryRepository.userByStatus(hobbyId, ApproveStatus.READY);
    }

    public List<HobbyHistoryDto> findParticipantList(Long hobbyId) { // 해당 취미방 참여자
        return hobbyHistoryRepository.userByStatus(hobbyId, ApproveStatus.APPROVE);
    }

    public ApproveStatus changeStatus(ChangeStatusRequestDto changeStatusRequestDto) { // 취미방 신청을 승인 또는 거절하기
        Long hobbyHistoryId = hobbyHistoryRepository.findByHobbyAndMemberIdDto(changeStatusRequestDto.getHobbyId(), changeStatusRequestDto.getParticipantId()).getHobbyHistoryId();
        return hobbyHistoryRepository.changeStatus(hobbyHistoryId, changeStatusRequestDto.getApproveStatus());
    }

    public List<HobbyHistoryResponseDto> findStatusHobbyList(Long userId, ApproveStatus status) { //유저의 참여중인, 대기중인, 만료된 방 목록 보여주기
        return hobbyHistoryRepository.findByStatus(userId, status);
    }

    public HobbyHistoryDto findByIdDto(Long hobbyHistoryId) {
        return hobbyHistoryRepository.findByIdDto(hobbyHistoryId);
    }

    @Test
    public void 히스토리_등록() {

        Long id = createHobbyHistory(1L);
        System.out.println(id + "@@@@@@@@@@@@@@@@@@@@@@@@@@2");
        HobbyHistoryDto result = findByIdDto(id);
        System.out.println("생성 결과: " + result.toString());
    }

    @Test
    public void 취미1_승인_대기자() {
        List<HobbyHistoryDto> result = findWaitingMemberList(1L);
        for (HobbyHistoryDto h : result
        ) {
            System.out.println("취미1 승인 대기자: " + h);
        }

    }

    @Test
    public void 취미1_참여자() {
        System.out.println("====취미1에 참여중인 사람====");
        List<HobbyHistoryDto> result2 = findParticipantList(1L);
        for (HobbyHistoryDto h : result2
        ) {
            System.out.println("취미1 참여자: " + h);
        }

    }

    @Test
    public void 멤버1_참여_현황() {

        List<HobbyHistoryResponseDto> result4 = findStatusHobbyList(1L, ApproveStatus.APPROVE);
        for (HobbyHistoryResponseDto h : result4
        ) {
            System.out.println("멤버1의 참여방: " + h);
        }

    }

    @Test
    public void 멤버1_대기_현황() {

        List<HobbyHistoryResponseDto> result4 = findStatusHobbyList(1L, ApproveStatus.READY);
        for (HobbyHistoryResponseDto h : result4
        ) {
            System.out.println("멤버1의 대기방: " + h);
        }

    }

    @Test
    public void 멤버1_거절_현황() {
        List<HobbyHistoryResponseDto> result5 = findStatusHobbyList(1L, ApproveStatus.REJECT);
        for (HobbyHistoryResponseDto h : result5
        ) {
            System.out.println("멤버1의 거절방: " + h);
        }
    }

    @Test
    public void 취미방_정보_변경() {
        HobbyHistoryDto before = findByIdDto(1L);
        System.out.println("변경 전: " + before.toString());
        ChangeStatusRequestDto changeStatusRequestDto = ChangeStatusRequestDto.builder()
                .hobbyId(before.getHobbyId())
                .participantId(before.getMemberId())
                .approveStatus(ApproveStatus.REJECT)
                .build();

        changeStatus(changeStatusRequestDto);

        System.out.println("변경 후: " + findByIdDto(1L).toString());
    }

}