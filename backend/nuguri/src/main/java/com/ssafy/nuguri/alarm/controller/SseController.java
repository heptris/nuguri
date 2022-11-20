package com.ssafy.nuguri.alarm.controller;

import com.ssafy.nuguri.alarm.service.AlarmService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Api(tags = {"Alarm 구독"})
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/app/sse")
@RestController
public class SseController {

    private final AlarmService alarmService;

    @ApiOperation(value = "알람 구독", notes = "알람 구독 API")
    @GetMapping(value = "/sub/{memberId}", produces = "text/event-stream")
    public SseEmitter subscribe(@PathVariable Long memberId) {
        return alarmService.subscribe(memberId);
    }


}
