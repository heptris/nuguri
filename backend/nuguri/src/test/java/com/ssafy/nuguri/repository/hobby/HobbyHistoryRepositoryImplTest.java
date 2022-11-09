package com.ssafy.nuguri.repository.hobby;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.nuguri.domain.baseaddress.BaseAddress;
import com.ssafy.nuguri.domain.category.Category;
import com.ssafy.nuguri.domain.hobby.ApproveStatus;
import com.ssafy.nuguri.domain.hobby.Hobby;
import com.ssafy.nuguri.domain.hobby.HobbyFavorite;
import com.ssafy.nuguri.domain.hobby.HobbyHistory;
import com.ssafy.nuguri.domain.member.Member;
import com.ssafy.nuguri.dto.hobby.HobbyHistoryDto;
import com.ssafy.nuguri.dto.hobby.HobbyHistoryResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
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

@SpringBootTest
@Transactional
//@Sql("classpath:tableInit.sql")
class HobbyHistoryRepositoryImplTest {
    @Autowired
    EntityManager em;

    @Autowired
    HobbyHistoryRepositoryImpl hobbyHistoryRepository;
    JPAQueryFactory queryFactory;


//    @Test
//    @BeforeEach
////    @Commit
//    public void before(){
//
//        queryFactory = new JPAQueryFactory(em);
//
//        BaseAddress ba = queryFactory
//                .selectFrom(baseAddress)
//                .where(baseAddress.id.eq(1L))
//                .fetchOne();
//        Category ca = queryFactory
//                .selectFrom(category)
//                .where(category.id.eq(1L))
//                .fetchOne();
//        Member me = queryFactory
//                .selectFrom(member)
//                .where(member.id.eq(1L))
//                .fetchOne();
//
//        Hobby hobbyEntity1 = Hobby.builder()
//                .baseAddress(ba)
//                .category(ca)
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
//                .baseAddress(ba)
//                .category(ca)
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
//
//        HobbyHistory hobbyHistory1 = HobbyHistory.builder().member(me).hobby(hobbyEntity1).isPromoter(true).approveStatus(ApproveStatus.READY).build();
//        HobbyHistory hobbyHistory2 = HobbyHistory.builder().member(me).hobby(hobbyEntity1).isPromoter(false).approveStatus(ApproveStatus.REJECT).build();
//        HobbyHistory hobbyHistory3 = HobbyHistory.builder().member(me).hobby(hobbyEntity1).isPromoter(true).approveStatus(ApproveStatus.APPROVE).build();
//        HobbyHistory hobbyHistory4 = HobbyHistory.builder().member(me).hobby(hobbyEntity1).isPromoter(false).approveStatus(ApproveStatus.APPROVE).build();
//        HobbyHistory hobbyHistory5 = HobbyHistory.builder().member(me).hobby(hobbyEntity1).isPromoter(false).approveStatus(ApproveStatus.REJECT).build();
//        HobbyFavorite hobbyFavorite1 = HobbyFavorite.builder().member(me).hobby(hobbyEntity1).isFavorite(true).build();
//        HobbyFavorite hobbyFavorite2 = HobbyFavorite.builder().member(me).hobby(hobbyEntity1).isFavorite(true).build();
//
//
//
//        em.persist(hobbyEntity1);
//        em.persist(hobbyEntity2);
//        em.persist(hobbyHistory1);
//        em.persist(hobbyHistory2);
//        em.persist(hobbyHistory3);
//        em.persist(hobbyHistory4);
//        em.persist(hobbyHistory5);
//        em.persist(hobbyFavorite1);
//        em.persist(hobbyFavorite2);
//    }

    @Test
    public void 승인_대기자_목록(){
        List<HobbyHistoryDto> result = hobbyHistoryRepository.userByStatus(1L,ApproveStatus.READY);
        for (HobbyHistoryDto m :result
             ) {
            System.out.println("hobbyId 1을 대기중인 멤버: "+ m);
        }
    }

    @Test
    public void 취미방_참가자_목록(){
        List<HobbyHistoryDto> result = hobbyHistoryRepository.userByStatus(1L,ApproveStatus.APPROVE);
        for (HobbyHistoryDto m :result
        ) {
            System.out.println("hobbyId 1에 참여중인 멤버: "+ m);
        }
    }

    @Test
    public void 취미참여자_상태_변경(){
        HobbyHistory testData = queryFactory.selectFrom(hobbyHistory)
                .where(hobbyHistory.id.eq(1L))
                .fetchOne();
        System.out.println("변경 전: "+testData.getApproveStatus());
        hobbyHistoryRepository.changeStatus(testData.getId(),ApproveStatus.REJECT);
        System.out.println("변경 후: "+testData.getApproveStatus());

    }


    @Test
    public void 상태별_취미방_목록(){
        List<HobbyHistoryResponseDto> ready = hobbyHistoryRepository.findByStatus(1L,ApproveStatus.READY);
        List<HobbyHistoryResponseDto> reject = hobbyHistoryRepository.findByStatus(1L,ApproveStatus.REJECT);
        List<HobbyHistoryResponseDto> approve = hobbyHistoryRepository.findByStatus(1L,ApproveStatus.APPROVE);
        for (HobbyHistoryResponseDto data: ready) {
            System.out.println("승인 대기중인 신청: "+data);
        }
        for (HobbyHistoryResponseDto data: reject) {
            System.out.println("승인 거절된 신청: "+data);
        }
        for (HobbyHistoryResponseDto data: approve) {
            System.out.println("승인된 신청: "+data);
        }

    }

    @Test
    public void 자신이_방장인_방들(){
        List<HobbyHistoryResponseDto> result = hobbyHistoryRepository.findOperatings(1L);
        for (HobbyHistoryResponseDto m :result
        ) {
            System.out.println("유저1이 운영중인(방장인) 취미방: "+ m);
        }
    }

    @Test
    void 취미와_멤버로_대기중인_history_찾기(){ // 알람으로 온 신청을 승인 또는 거절할 때 사용됨
        HobbyHistoryDto result = hobbyHistoryRepository.findByHobbyAndMemberIdDto(1L,1L);
        System.out.println(result.toString());
    }

    @Test
    public void Id로_값_찾기(){
        System.out.println("hobbyHistoryId가 1인 hobbyHistory정보: "+hobbyHistoryRepository.findByIdDto(1L).toString());
    }


}