package com.ssafy.nuguri.service.hobby;

import com.ssafy.nuguri.alarm.dto.HobbyAlarmEventDto;
import com.ssafy.nuguri.domain.hobby.ApproveStatus;
import com.ssafy.nuguri.domain.hobby.Hobby;
import com.ssafy.nuguri.domain.hobby.HobbyHistory;
import com.ssafy.nuguri.dto.hobby.ChangeStatusRequestDto;
import com.ssafy.nuguri.dto.hobby.HobbyHistoryDto;
import com.ssafy.nuguri.domain.member.Member;
import com.ssafy.nuguri.dto.hobby.HobbyHistoryResponseDto;
import com.ssafy.nuguri.exception.ex.CustomException;
import com.ssafy.nuguri.exception.ex.ErrorCode;
import com.ssafy.nuguri.repository.hobby.HobbyHistoryRepository;
import com.ssafy.nuguri.repository.hobby.HobbyRepository;
import com.ssafy.nuguri.repository.member.MemberRepository;
import com.ssafy.nuguri.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.ssafy.nuguri.domain.alarm.AlarmCode.*;
import static com.ssafy.nuguri.exception.ex.ErrorCode.*;
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
    public Long createHobbyHistory(Long hobbyId) { // 취미방 생성 또는 참여신청 // 신청만
        Long memberId = SecurityUtil.getCurrentMemberId();
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new CustomException(MEMBER_NOT_FOUND));

        Hobby hobby = hobbyRepository.findById(hobbyId).orElseThrow(() -> new CustomException(HOBBY_NOT_FOUND));



//        // 조건 미달
//        if(hobby.getCurNum() >= hobby.getMaxNum()) throw new CustomException(FULL_HOBBY_ERROR); // 정원초과
//        else if(hobby.getRowAgeLimit() > member.getAge()) throw new CustomException(AGE_LIMIT_ERROR); // 나이제한
//        else if(hobby.getHighAgeLimit() < member.getAge()) throw new CustomException(AGE_LIMIT_ERROR); // 나이제한
//        else if(hobby.getSexLimit() ==  member.getSex()) throw new CustomException(SEX_LIMIT_ERROR); // 성별제한
//        else if(hobby.getBaseAddress() != member.getBaseAddress()) throw new CustomException(DIFF_ADDRESS_ERROR); // 나이제한

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
        HobbyAlarmEventDto hobbyAlarmEventDto = HobbyAlarmEventDto.builder().content(HOBBY_OWNER_ALARM.getContent()).title(HOBBY_OWNER_ALARM.getTitle())
                .isRead(FALSE).member(alarmReceiver).participantId(member.getId()).
                participantImage(member.getProfileImage()).hobbyId(hobbyId).build();
        eventPublisher.publishEvent(hobbyAlarmEventDto);
        /**
         * 알람 보내기 끝
         */

        return hobbyHistoryRepository.save(hobbyHistoryEntity).getId();
    }

    @Transactional
    public List<HobbyHistoryDto> findWaitingMemberList(Long hobbyId) { // 해당 취미방 신청 대기자
        return hobbyHistoryRepository.userByStatus(hobbyId,ApproveStatus.READY);
    }

    @Transactional
    public List<HobbyHistoryDto> findParticipantList(Long hobbyId) { // 해당 취미방 참여자
        return hobbyHistoryRepository.userByStatus(hobbyId,ApproveStatus.APPROVE);
    }

    @Transactional
    public ApproveStatus changeStatus(ChangeStatusRequestDto changeStatusRequestDto) { // 취미방 신청을 승인 또는 거절하기
        Long hobbyHistoryId = hobbyHistoryRepository.findByHobbyAndMemberIdDto(changeStatusRequestDto.getHobbyId(), changeStatusRequestDto.getParticipantId()).getHobbyHistoryId();
        /**
         * 알람 보내기 시작
         */
        Hobby hobby = hobbyRepository.findById(changeStatusRequestDto.getHobbyId()).orElseThrow(
                () -> new CustomException(HOBBY_NOT_FOUND)
        );
        Member alarmReceiver = new Member();
        alarmReceiver.changeMemberId(changeStatusRequestDto.getParticipantId());
        HobbyAlarmEventDto hobbyAlarmEventDto = HobbyAlarmEventDto.builder().member(alarmReceiver).isRead(FALSE).build();
        if (changeStatusRequestDto.getApproveStatus().equals(ApproveStatus.APPROVE)) {
            hobbyAlarmEventDto.setContent(HOBBY_PARTICIPANT_ALARM_APPROVE.getContent());
            hobbyAlarmEventDto.setTitle(hobby.getTitle() + HOBBY_PARTICIPANT_ALARM_APPROVE.getTitle());
        } else if (changeStatusRequestDto.getApproveStatus().equals(ApproveStatus.REJECT)) {
            hobbyAlarmEventDto.setContent(HOBBY_PARTICIPANT_ALARM_REJECT.getContent());
            hobbyAlarmEventDto.setTitle(hobby.getTitle() + HOBBY_PARTICIPANT_ALARM_REJECT.getTitle());
        }
        eventPublisher.publishEvent(hobbyAlarmEventDto);
        /**
         * 알람 보내기 끝
         */
        return hobbyHistoryRepository.changeStatus(hobbyHistoryId, changeStatusRequestDto.getApproveStatus());
    }

    @Transactional
    public List<HobbyHistoryResponseDto> findStatusHobbyList(Long userId, ApproveStatus status) { //유저의 참여중인, 대기중인, 만료된 방 목록 보여주기
        return hobbyHistoryRepository.findByStatus(userId, status);
    }

    @Transactional
    public HobbyHistoryDto findByIdDto(Long hobbyHistoryId) {
        return hobbyHistoryRepository.findByIdDto(hobbyHistoryId);
    }

    @Transactional
    public List<HobbyHistoryResponseDto> findOperatingsByUserId(Long userId){
        return hobbyHistoryRepository.findOperatings(userId);
    }
}
