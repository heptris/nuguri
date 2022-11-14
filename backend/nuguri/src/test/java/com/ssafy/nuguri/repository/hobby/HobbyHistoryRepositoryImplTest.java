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
    HobbyHistoryRepository hobbyHistoryRepository;



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
        HobbyHistory testData = hobbyHistoryRepository.findById(1L).orElseThrow();
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
        HobbyHistoryDto result = hobbyHistoryRepository.findByHobbyAndMemberIdDto(3L,3L);
        System.out.println("@@@@@@@@@@@@@"+result.toString());
    }

    @Test
    public void Id로_값_찾기(){
        System.out.println("hobbyHistoryId가 1인 hobbyHistory정보: "+hobbyHistoryRepository.findByIdDto(1L).toString());
    }


}