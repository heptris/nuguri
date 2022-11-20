package com.ssafy.nuguri.alarm.handler;

import com.ssafy.nuguri.alarm.dto.HobbyAlarmEventDto;
import com.ssafy.nuguri.alarm.repository.EmitterRepository;
import com.ssafy.nuguri.alarm.service.AlarmService;
import com.ssafy.nuguri.chat.dto.ChatAlarmDto;
import com.ssafy.nuguri.domain.alarm.Alarm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Component
public class AlarmEventHandler {

    private final AlarmService alarmService;
    private final EmitterRepository emitterRepository;

    @Async
    @TransactionalEventListener
    public void hobbyAlarm(HobbyAlarmEventDto hobbyAlarmEventDto) throws InterruptedException{
        log.info("hobbyAlarm event 발생");
        Alarm alarm = hobbyAlarmEventDto.toAlarm();
        alarmService.sendAlarmEvent(alarm);
    }

    @Async
    @TransactionalEventListener
    public void chatAlarm(ChatAlarmDto chatAlarmDto) throws InterruptedException {

        SseEmitter sseEmitter = emitterRepository.findByMemberId(chatAlarmDto.getReceiverId());
        try {
            sseEmitter.send(SseEmitter.event().name("messageAlarm").data(chatAlarmDto));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
