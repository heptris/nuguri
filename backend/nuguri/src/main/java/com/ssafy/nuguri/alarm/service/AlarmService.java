package com.ssafy.nuguri.alarm.service;

import com.ssafy.nuguri.alarm.dto.AlarmDto;
import com.ssafy.nuguri.alarm.repository.AlarmRepository;
import com.ssafy.nuguri.alarm.repository.EmitterRepository;
import com.ssafy.nuguri.domain.alarm.Alarm;
import com.ssafy.nuguri.domain.member.Member;
import com.ssafy.nuguri.exception.ex.CustomException;
import com.ssafy.nuguri.exception.ex.ErrorCode;
import com.ssafy.nuguri.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static com.ssafy.nuguri.exception.ex.ErrorCode.*;
import static java.lang.Boolean.*;

@RequiredArgsConstructor
@Slf4j
@Transactional
@Service
public class AlarmService {

    private static final Long DEFAULT_TIMEOUT = 60L * 1000 * 60;
    private final AlarmRepository alarmRepository;
    private final EmitterRepository emitterRepository;

    public SseEmitter subscribe(Long memberId) {
        SseEmitter sseEmitter = emitterRepository.save(memberId, new SseEmitter(DEFAULT_TIMEOUT));
        sseEmitter.onCompletion(() -> emitterRepository.deleteById(memberId));
        sseEmitter.onTimeout(() -> emitterRepository.deleteById(memberId));
        try {
            // 연결 메세지 보내기(503에러 방지)
            sseEmitter.send(SseEmitter.event().name("connect"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sseEmitter;
    }

    /**
     * client에게 알람 전송
     */
    public void sendAlarmEvent(Alarm alarm) {
        // 알람 저장
        Alarm savedAlarm = alarmRepository.save(alarm);
        // 알람 전송(Sse)
        SseEmitter sseEmitter = emitterRepository.findByMemberId(alarm.getMember().getId());
        try {
            sseEmitter.send(SseEmitter.event().name("alarmEvent").data(alarm.toAlarmDto()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 내 알람리스트 조회
     */
    public List<AlarmDto> myAlarmList(Long memberId) {
        Member member = new Member();
        member.changeMemberId(memberId);

        List<Alarm> alarmList = alarmRepository.findAllByMember(member);
        return alarmList.stream().map(Alarm::toAlarmDto).collect(Collectors.toList());
    }

    /**
     * 알람 읽기
     */
    public void readAlarm(Long alarmId) {
        Alarm alarm = alarmRepository.findById(alarmId).orElseThrow(
                () -> new CustomException(ALARM_NOT_FOUND)
        );
        alarm.changeIsRead(TRUE);
    }

    /**
     * 테스트용 알림 저장
     */
    public void saveAll(List<Alarm> alarmList) {
        alarmRepository.saveAll(alarmList);
    }
}
