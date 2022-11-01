package com.ssafy.nuguri.alarm.controller;

import com.ssafy.nuguri.alarm.service.AlarmService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RequiredArgsConstructor
@Slf4j
@RequestMapping("/alarm")
@RestController
public class SseController {

    private final AlarmService alarmService;

    @GetMapping(value = "/sub", produces = "text/event-stream")
    public SseEmitter subscribe(@PathVariable Long memberId) {
        return alarmService.subscribe(memberId);
    }


}
