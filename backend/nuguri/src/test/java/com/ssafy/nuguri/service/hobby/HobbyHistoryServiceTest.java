package com.ssafy.nuguri.service.hobby;

import com.ssafy.nuguri.domain.hobby.ApproveStatus;
import com.ssafy.nuguri.domain.hobby.Hobby;
import com.ssafy.nuguri.domain.hobby.HobbyHistory;
import com.ssafy.nuguri.domain.member.Member;
import com.ssafy.nuguri.dto.hobby.HobbyHistoryDto;
import com.ssafy.nuguri.dto.hobby.HobbyStatusDto;
import com.ssafy.nuguri.repository.hobby.HobbyHistoryRepository;
import com.ssafy.nuguri.repository.hobby.HobbyRepository;
import com.ssafy.nuguri.repository.member.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
class HobbyHistoryServiceTest {

    @Autowired
    HobbyHistoryRepository hobbyHistoryRepository;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    HobbyRepository hobbyRepository;

    public Long createHobbyHistory(HobbyHistoryDto hobbyHistoryDto){ // 취미방 생성 또는 참여신청
        Member member = memberRepository.findById(hobbyHistoryDto.getMemberId()).orElseThrow();
        Hobby hobby = hobbyRepository.findById(hobbyHistoryDto.getHobbyId()).orElseThrow();

        // 조건 미달
        if(!hobbyHistoryDto.isPromoter() // 방장이 아니면서
                && hobby.getCurNum() >= hobby.getMaxNum() // 정원초과
                || hobby.getAgeLimit() > member.getAge() // 나이제한
                || hobby.getSexLimit() ==  member.getSex() // 성별제한
                || LocalDateTime.now().isAfter(hobby.getEndDate()) // 만료된 모임
                || hobby.getBaseAddress() != member.getBaseAddress()){ // 주소가 다름
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
    @Test
    public void test1(){

    }


    public List<HobbyHistoryDto> findWaitingMemberList(Long hobbyId){ // 해당 취미방 신청 대기자
        return hobbyHistoryRepository.waiter(hobbyId);
    }
    @Test
    public void test2(){

    }


    public List<HobbyHistoryDto> findParticipantList(Long hobbyId){ // 해당 취미방 참여자
        return hobbyHistoryRepository.participant(hobbyId);
    }
    @Test
    public void test3(){

    }


    public boolean changeStatus(Long hobbyHistoryId, ApproveStatus status){ // 취미방 신청을 승인 또는 거절하기
        return hobbyHistoryRepository.changeStatus(hobbyHistoryId,status);
    }
    @Test
    public void test4(){

    }


    public List<HobbyStatusDto> findStatusHobbyList(Long userId, ApproveStatus status){ //유저의 참여중인, 대기중인, 만료된 방 목록 보여주기
        return hobbyHistoryRepository.findByStatus(userId,status);
    }
    @Test
    public void test5(){

    }

}