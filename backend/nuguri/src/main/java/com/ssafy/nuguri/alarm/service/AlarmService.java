package com.ssafy.nuguri.alarm.service;

import com.ssafy.nuguri.alarm.repository.AlarmRepository;
import com.ssafy.nuguri.alarm.repository.EmitterRepository;
import com.ssafy.nuguri.domain.alarm.Alarm;
import com.ssafy.nuguri.repository.hobby.HobbyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;

@RequiredArgsConstructor
@Slf4j
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
}
