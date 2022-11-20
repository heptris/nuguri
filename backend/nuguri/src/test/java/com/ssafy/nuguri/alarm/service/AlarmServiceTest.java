package com.ssafy.nuguri.alarm.service;

import com.ssafy.nuguri.alarm.dto.AlarmDto;
import com.ssafy.nuguri.alarm.repository.AlarmRepository;
import com.ssafy.nuguri.domain.alarm.Alarm;
import com.ssafy.nuguri.domain.member.Member;
import com.ssafy.nuguri.exception.ex.CustomException;
import com.ssafy.nuguri.exception.ex.ErrorCode;
import com.ssafy.nuguri.util.SecurityUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

import static com.ssafy.nuguri.exception.ex.ErrorCode.*;
import static java.lang.Boolean.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Rollback(value = false)
@Sql("classpath:tableInit.sql")
@Transactional
class AlarmServiceTest {

    @BeforeEach
    public void beforeEach() {
        Member member = new Member();
        member.changeMemberId(1L);
        List<Alarm> alarmList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            Alarm alarm = Alarm.builder().member(member).title("title" + String.valueOf(i)).content("content" + String.valueOf(i))
                    .isRead(FALSE).participantId(Long.valueOf(i)).build();
            alarmList.add(alarm);
        }
        alarmService.saveAll(alarmList);
    }

    @Autowired
    AlarmService alarmService;
    @Autowired
    AlarmRepository alarmRepository;
    @Autowired
    EntityManager em;

    @Test
    @DisplayName("알람 구독")
    public void subscribe() throws Exception {
        assertDoesNotThrow(() -> alarmService.subscribe(1L));
    }

    @Test
    @DisplayName("알림 메세지 전송")
    public void send() throws Exception {
        alarmService.subscribe(1L);

        Member member = new Member();
        member.changeMemberId(1L);

        Alarm alarm = Alarm.builder().member(member).content("content").title("title").id(1L).isRead(FALSE).participantId(2L).build();
        assertDoesNotThrow(() -> alarmService.sendAlarmEvent(alarm));
    }

    @Test
    @DisplayName("알람 리스트 조회")
    public void 알람_리스트_조회() {
        List<AlarmDto> alarmList = alarmService.myAlarmList(1L);
        assertThat(alarmList.size()).isEqualTo(20);
    }

    @Test
    @DisplayName("알람 읽기")
    public void 알람_읽기() {
        alarmService.readAlarm(1L);
        Alarm alarm = alarmRepository.findById(1L).orElseThrow(
                () -> new CustomException(ALARM_NOT_FOUND)
        );
        assertThat(alarm.getIsRead()).isEqualTo(TRUE);
    }

}