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
import com.ssafy.nuguri.dto.hobby.HobbyHistoryListDto;
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
    public void ??????1_??????_?????????(){
        List<HobbyHistoryListDto> result = hobbyHistoryService.findWaitingMemberList(1L);
        for (HobbyHistoryListDto h: result
        ) {
            System.out.println("??????1 ?????? ?????????: " + h);
        }

    }

    @Test
    public void ??????1_?????????() {
        System.out.println("====??????1??? ???????????? ??????====");

        List<HobbyHistoryListDto> result2 = hobbyHistoryService.findParticipantList(1L);
        for (HobbyHistoryListDto h: result2

        ) {
            System.out.println("??????1 ?????????: " + h);
        }

    }

    @Test
    public void ??????1_??????_??????() {

        List<HobbyHistoryResponseDto> result4 = hobbyHistoryService.findStatusHobbyList(1L, ApproveStatus.APPROVE);
        for (HobbyHistoryResponseDto h: result4

        ) {
            System.out.println("??????1??? ?????????: " + h);
        }

    }

    @Test
    public void ??????1_??????_??????() {

        List<HobbyHistoryResponseDto> result4 = hobbyHistoryService.findStatusHobbyList(1L, ApproveStatus.READY);
        for (HobbyHistoryResponseDto h: result4

        ) {
            System.out.println("??????1??? ?????????: " + h);
        }

    }

    @Test
    public void ??????1_??????_??????(){
        List<HobbyHistoryResponseDto> result5 = hobbyHistoryService.findStatusHobbyList(1L, ApproveStatus.REJECT);
        for (HobbyHistoryResponseDto h: result5

        ) {
            System.out.println("??????1??? ?????????: " + h);
        }
    }

    @Test
    public void ?????????_??????_??????(){
        HobbyHistoryDto before = hobbyHistoryService.findByIdDto(3L);
        System.out.println("?????? ???: "+before.toString());
        ChangeStatusRequestDto changeStatusRequestDto = ChangeStatusRequestDto.builder()
                .hobbyId(before.getHobbyId())
                .participantId(before.getMemberId())
                .approveStatus(ApproveStatus.REJECT)
                .build();

        hobbyHistoryService.changeStatus(changeStatusRequestDto);

        System.out.println("?????? ???: "+hobbyHistoryService.findByIdDto(1L).toString());
    }

    @Test
    public void ????????????_???_??????(){
        List<HobbyHistoryResponseDto> result = hobbyHistoryService.findOperatingsByUserId(1L);
        for (HobbyHistoryResponseDto hobbyHistoryResponseDto:result
             ) {
            System.out.println("hobbyHistoryResponseDto = " + hobbyHistoryResponseDto);
        }
    }
}