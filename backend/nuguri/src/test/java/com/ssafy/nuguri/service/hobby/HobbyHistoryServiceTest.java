package com.ssafy.nuguri.service.hobby;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.nuguri.domain.baseaddress.BaseAddress;
import com.ssafy.nuguri.domain.category.Category;
import com.ssafy.nuguri.domain.hobby.ApproveStatus;
import com.ssafy.nuguri.domain.hobby.Hobby;
import com.ssafy.nuguri.domain.hobby.HobbyHistory;
import com.ssafy.nuguri.domain.member.Member;
import com.ssafy.nuguri.dto.hobby.HobbyHistoryDto;
import com.ssafy.nuguri.dto.hobby.HobbyStatusDto;
import com.ssafy.nuguri.repository.baseaddress.BaseaddressRepository;
import com.ssafy.nuguri.repository.category.CategoryRepository;
import com.ssafy.nuguri.repository.hobby.HobbyHistoryRepository;
import com.ssafy.nuguri.repository.hobby.HobbyRepository;
import com.ssafy.nuguri.repository.member.MemberRepository;
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
import static com.ssafy.nuguri.domain.hobby.QHobbyHistory.hobbyHistory;
import static com.ssafy.nuguri.domain.member.QMember.member;

@SpringBootTest
@Transactional
class HobbyHistoryServiceTest {

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
        Member me = queryFactory // 25살, m, 청운동
                .selectFrom(member)
                .where(member.id.eq(1L))
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

    public Long createHobbyHistory(HobbyHistoryDto hobbyHistoryDto){ // 취미방 생성 또는 참여신청
        Member member = memberRepository.findById(hobbyHistoryDto.getMemberId()).orElseThrow();
        Hobby hobby = hobbyRepository.findById(hobbyHistoryDto.getHobbyId()).orElseThrow();

        // 조건 미달
//        if(!hobbyHistoryDto.isPromoter()){
//            System.out.println("손님임");
//        };
//        if(hobby.getCurNum() >= hobby.getMaxNum()){
//            System.out.println("정원초과");
//        }
//        if(hobby.getAgeLimit() > member.getAge()){
//            System.out.println("나이제한");
//        }
//        if(hobby.getSexLimit() ==  member.getSex()){
//            System.out.println("성별제한");
//        }
//        if (LocalDateTime.now().isAfter(hobby.getEndDate())){
//            System.out.println("만료된 모임");
//        }
//        if(hobby.getBaseAddress() != member.getBaseAddress()){
//            System.out.println("주소가 다름");
//        }

        if(!hobbyHistoryDto.isPromoter() &&// 방장이 아니면서
                hobby.getCurNum() >= hobby.getMaxNum() || // 정원초과
                hobby.getAgeLimit() > member.getAge() || // 나이제한
                hobby.getSexLimit() ==  member.getSex() || // 성별제한
                LocalDateTime.now().isAfter(hobby.getEndDate()) || // 만료된 모임
                hobby.getBaseAddress() != member.getBaseAddress()){ // 주소가 다름
            System.out.println("입장하실 수 없습니다");
            return -1L;
        }

        HobbyHistory hobbyHistoryEntity = HobbyHistory.builder()
                .member(member)
                .hobby(hobby)
                .isPromoter(hobbyHistoryDto.isPromoter())
                .approveStatus(hobbyHistoryDto.getApproveStatus())
                .build();

        return hobbyHistoryRepository.save(hobbyHistoryEntity).getId();
    }

    public List<HobbyHistoryDto> findWaitingMemberList(Long hobbyId){ // 해당 취미방 신청 대기자
        return hobbyHistoryRepository.waitingPerson(hobbyId);
    }



    public List<HobbyHistoryDto> findParticipantList(Long hobbyId){ // 해당 취미방 참여자
        return hobbyHistoryRepository.participant(hobbyId);
    }



    public boolean changeStatus(Long hobbyHistoryId, ApproveStatus status){ // 취미방 신청을 승인 또는 거절하기
        return hobbyHistoryRepository.changeStatus(hobbyHistoryId,status);
    }



    public List<HobbyStatusDto> findStatusHobbyList(Long userId, ApproveStatus status){ //유저의 참여중인, 대기중인, 만료된 방 목록 보여주기
        return hobbyHistoryRepository.findByStatus(userId,status);
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
    public void 히스토리_등록(){
        HobbyHistoryDto hobbyHistoryDto = HobbyHistoryDto.builder()
                .hobbyId(1L)
                .memberId(1L)
                .isPromoter(false)
                .approveStatus(ApproveStatus.READY)
                .build();
        Long id = createHobbyHistory(hobbyHistoryDto);
        System.out.println(id);
        HobbyHistoryDto result = findByIdDto(id);
        System.out.println("생성 결과: "+ result.toString());
    }

    @Test
    public void 취미1_승인_대기자(){
        List<HobbyHistoryDto> result = findWaitingMemberList(1L);
        for (HobbyHistoryDto h: result
        ) {
            System.out.println("취미1 승인 대기자: "+h);
        }

    }

    @Test
    public void 취미1_참여자(){
        System.out.println("====취미1에 참여중인 사람====");
        List<HobbyHistoryDto> result2 = findParticipantList(1L);
        for (HobbyHistoryDto h: result2
        ) {
            System.out.println("취미1 참여자: "+h);
        }

    }

    @Test
    public void 멤버1_참여_현황(){

        List<HobbyStatusDto> result4 = findStatusHobbyList(1L, ApproveStatus.APPROVE);
        for (HobbyStatusDto h: result4
        ) {
            System.out.println("멤버1의 참여방: "+h);
        }

    }
    @Test
    public void 멤버1_대기_현황(){

        List<HobbyStatusDto> result4 = findStatusHobbyList(1L, ApproveStatus.READY);
        for (HobbyStatusDto h: result4
        ) {
            System.out.println("멤버1의 대기방: "+h);
        }

    }
    @Test
    public void 멤버1_거절_현황(){
        List<HobbyStatusDto> result5 = findStatusHobbyList(1L, ApproveStatus.REJECT);
        for (HobbyStatusDto h: result5
        ) {
            System.out.println("멤버1의 거절방: "+h);
        }
    }

    @Test
    public void 취미방_정보_변경(){
        System.out.println("변경 전: "+findByIdDto(1L));
        changeStatus(1L,ApproveStatus.REJECT);
        System.out.println("변경 후: "+findByIdDto(1L));
    }



}