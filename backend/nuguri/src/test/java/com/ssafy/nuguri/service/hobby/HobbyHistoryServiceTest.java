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
//@Sql("classpath:tableInit.sql")
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
        HobbyHistoryDto before = hobbyHistoryService.findByIdDto(3L);
        System.out.println("변경 전: "+before.toString());
        ChangeStatusRequestDto changeStatusRequestDto = ChangeStatusRequestDto.builder()
                .hobbyId(before.getHobbyId())
                .participantId(before.getMemberId())
                .approveStatus(ApproveStatus.REJECT)
                .build();

        hobbyHistoryService.changeStatus(changeStatusRequestDto);

        System.out.println("변경 후: "+hobbyHistoryService.findByIdDto(1L).toString());
    }

    @Test
    public void 운영중인_방_찾기(){
        List<HobbyHistoryResponseDto> result = hobbyHistoryService.findOperatingsByUserId(1L);
        for (HobbyHistoryResponseDto hobbyHistoryResponseDto:result
             ) {
            System.out.println("hobbyHistoryResponseDto = " + hobbyHistoryResponseDto);
        }
    }
}