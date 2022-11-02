package com.ssafy.nuguri.alarm.controller;

import com.ssafy.nuguri.alarm.service.AlarmService;
import com.ssafy.nuguri.dto.response.ResponseDto;
import com.ssafy.nuguri.util.SecurityUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.OK;

@RequestMapping("/alarm")
@Slf4j
@RequiredArgsConstructor
@RestController
public class AlarmController {

    private final AlarmService alarmService;
    @ApiOperation(value ="알람 조회")
    @GetMapping
    public ResponseEntity myAlarmList() {
        Long memberId = SecurityUtil.getCurrentMemberId();
        return ResponseEntity.status(OK).body(
                new ResponseDto<>(200, "알람 조회", alarmService.myAlarmList(memberId))
        );
    }

    /**
     * 알람 읽기
     */
    @ApiOperation(value = "알림 읽기 요청")
    @GetMapping("/{alarmId}")
    public ResponseEntity readAlarm(@PathVariable Long alarmId) {
        alarmService.readAlarm(alarmId);
        return ResponseEntity.ok().body(
                new ResponseDto<>(200, "알람 읽기 성공", null)
        );
    }
}
