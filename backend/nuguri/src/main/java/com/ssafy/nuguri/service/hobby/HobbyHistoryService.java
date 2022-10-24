package com.ssafy.nuguri.service.hobby;

import com.ssafy.nuguri.domain.hobby.ApproveStatus;
import com.ssafy.nuguri.domain.hobby.HobbyHistory;
import com.ssafy.nuguri.dto.hobby.HobbyDto;
import com.ssafy.nuguri.dto.hobby.HobbyHistoryDto;
import com.ssafy.nuguri.domain.member.Member;
import com.ssafy.nuguri.repository.hobby.HobbyHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class HobbyHistoryService {
    private final HobbyHistoryRepository hobbyHistoryRepository;

    @Transactional
    public Integer createHobbyHistory(HobbyHistoryDto hobbyHistoryDto){ // 취미방 생성 또는 참여신청
        HobbyHistory hobbyHistoryEntity = HobbyHistory.builder()
                .id(hobbyHistoryDto.getHobbyHistoryId())
//                .member(hobbyHistoryDto.getMemberId())
//                .hobby(hobbyHistoryDto.getHobbyId())
                .isPromoter(hobbyHistoryDto.isPromoter())
                .approveStatus(hobbyHistoryDto.getApproveStatus())
                .build();

        hobbyHistoryRepository.save(hobbyHistoryEntity);

        // 생성이 아닌 참여인 경우 조건검사
//        if(hobby.getCurNum() >= hobby.getMaxNum() // 정원초과
//                || hobby.getAgeLimit() > member.  // 나이제한
//                || hobby.getSexLimit() == member. // 성별제한
//                || hobby.getEndDate() <= LocalDateTime.now() // 모임기간 만료
//                || hobby.getBaseAddress() != member. // 참여 불가능한 위치
        return 1;
    }

    @Transactional
    public List<Member> findWaitingMemberList(Long hobbyId){ // 해당 취미방 신청 대기자
        return hobbyHistoryRepository.waiter(hobbyId);
    }

    @Transactional
    public List<Member> findParticipantList(Long hobbyId){ // 해당 취미방 참여자
        return hobbyHistoryRepository.participant(hobbyId);
    }

    @Transactional
    public boolean changeStatus(Long hobbyHistoryId, ApproveStatus status){ // 취미방 신청을 승인 또는 거절하기
        return hobbyHistoryRepository.changeStatus(hobbyHistoryId,status);
    }

    @Transactional
    public List<HobbyDto> findStatusHobbyList(Long userId, ApproveStatus status){ //유저가 참여중인, 대기중인, 만료된 방 목록 보여주기
        return hobbyHistoryRepository.findByStatus(userId,status);
    }




}
