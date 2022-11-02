package com.ssafy.nuguri.service.hobby;

import com.ssafy.nuguri.alarm.dto.HobbyAlarmEventDto;
import com.ssafy.nuguri.domain.hobby.ApproveStatus;
import com.ssafy.nuguri.domain.hobby.Hobby;
import com.ssafy.nuguri.domain.hobby.HobbyHistory;
import com.ssafy.nuguri.dto.hobby.HobbyHistoryDto;
import com.ssafy.nuguri.domain.member.Member;
import com.ssafy.nuguri.dto.hobby.HobbyStatusDto;
import com.ssafy.nuguri.repository.hobby.HobbyHistoryRepository;
import com.ssafy.nuguri.repository.hobby.HobbyRepository;
import com.ssafy.nuguri.repository.member.MemberRepository;
import com.ssafy.nuguri.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.ssafy.nuguri.domain.alarm.AlarmCode.HOBBY_ALARM;
import static java.lang.Boolean.*;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class HobbyHistoryService {
    private final HobbyHistoryRepository hobbyHistoryRepository;
    private final MemberRepository memberRepository;
    private final HobbyRepository hobbyRepository;

    private final ApplicationEventPublisher eventPublisher;


    @Transactional
    public Long createHobbyHistory(Long hobbyId){ // 취미방 생성 또는 참여신청 // 신청만
        Member member = new Member();
        member.changeMemberId(SecurityUtil.getCurrentMemberId());

        Hobby hobby = hobbyRepository.findById(hobbyId).orElseThrow();

//        // 조건 미달
//        if(!hobbyHistoryDto.isPromoter() // 방장이 아니면서
//            && hobby.getCurNum() >= hobby.getMaxNum() // 정원초과
//            || hobby.getAgeLimit() > member.getAge() // 나이제한
//            || hobby.getSexLimit() ==  member.getSex() // 성별제한
//            || LocalDateTime.now().isAfter(hobby.getEndDate()) // 만료된 모임
//            || hobby.getBaseAddress() != member.getBaseAddress()){ // 주소가 다름
//            System.out.println("입장하실 수 없습니다");
//            return -1L;
//        }


        HobbyHistory hobbyHistoryEntity = HobbyHistory.builder()
                .member(member)
                .hobby(hobby)
                .isPromoter(false)
                .approveStatus(ApproveStatus.READY)
                .build();

        /**
         * 알람 보내기 시작
         */
        Long ownerId = hobbyHistoryRepository.findOwnerId(hobby);
        Member alarmReceiver = new Member();
        alarmReceiver.changeMemberId(ownerId);
        HobbyAlarmEventDto hobbyAlarmEventDto = HobbyAlarmEventDto.builder().content(HOBBY_ALARM.getContent()).title(HOBBY_ALARM.getTitle())
                .isRead(FALSE).member(alarmReceiver).participantId(member.getId()).participantImage(member.getProfileImage()).build();
        if (ownerId != member.getId()) {
            eventPublisher.publishEvent(hobbyAlarmEventDto);
        }
        /**
         * 알람 보내기 끝
         */

        return hobbyHistoryRepository.save(hobbyHistoryEntity).getId();
    }

    @Transactional
    public List<HobbyHistoryDto> findWaitingMemberList(Long hobbyId){ // 해당 취미방 신청 대기자
        return hobbyHistoryRepository.waitingPerson(hobbyId);
    }

    @Transactional
    public List<HobbyHistoryDto> findParticipantList(Long hobbyId){ // 해당 취미방 참여자
        return hobbyHistoryRepository.participant(hobbyId);
    }

    @Transactional
    public ApproveStatus changeStatus(Long hobbyHistoryId, ApproveStatus status){ // 취미방 신청을 승인 또는 거절하기
        return hobbyHistoryRepository.changeStatus(hobbyHistoryId,status);
    }

    @Transactional
    public List<HobbyStatusDto> findStatusHobbyList(Long userId, ApproveStatus status){ //유저의 참여중인, 대기중인, 만료된 방 목록 보여주기
        return hobbyHistoryRepository.findByStatus(userId,status);
    }

    @Transactional
    public void findByIdDto(Long hobbyHistoryId){
        hobbyHistoryRepository.findByIdDto(hobbyHistoryId);
    }
}
