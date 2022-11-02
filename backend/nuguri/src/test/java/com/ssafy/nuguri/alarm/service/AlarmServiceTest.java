package com.ssafy.nuguri.alarm.service;

import com.ssafy.nuguri.domain.alarm.Alarm;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class AlarmServiceTest {

    @Autowired
    AlarmService alarmService;

    @Test
    @DisplayName("알람 구독")
    public void subscribe() throws Exception{
        assertDoesNotThrow(() -> alarmService.subscribe(1L));
    }

    @Test
    @DisplayName("알림 메세지 전송")
    public void send() throws Exception {
        Alarm.builder().member()
        assertDoesNotThrow(() -> alarmService.sendAlarmEvent());
    }

}