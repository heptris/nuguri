package com.ssafy.nuguri.alarm.repository;

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.Optional;

public interface EmitterRepository {
    SseEmitter save(Long memberId, SseEmitter sseEmitter);

    void deleteById(Long memberId);

    SseEmitter findByMemberId(Long memberId);
}
