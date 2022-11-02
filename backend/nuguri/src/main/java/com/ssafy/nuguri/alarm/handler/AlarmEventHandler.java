package com.ssafy.nuguri.alarm.handler;

import com.ssafy.nuguri.alarm.dto.HobbyAlarmEventDto;
import com.ssafy.nuguri.alarm.service.AlarmService;
import com.ssafy.nuguri.domain.alarm.Alarm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

@Slf4j
@RequiredArgsConstructor
@Component
public class AlarmEventHandler {

    private final AlarmService alarmService;

    @Async
    @TransactionalEventListener
    public void hobbyAlarm(HobbyAlarmEventDto hobbyAlarmEventDto) throws InterruptedException{
        log.info("hobbyAlarm event 발생");
        Alarm alarm = hobbyAlarmEventDto.toAlarm();
        alarmService.sendAlarmEvent(alarm);
    }
}
