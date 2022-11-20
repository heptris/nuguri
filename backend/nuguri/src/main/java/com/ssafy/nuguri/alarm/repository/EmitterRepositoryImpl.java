package com.ssafy.nuguri.alarm.repository;

import com.ssafy.nuguri.exception.ex.CustomException;
import com.ssafy.nuguri.exception.ex.ErrorCode;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import static com.ssafy.nuguri.exception.ex.ErrorCode.SSE_NOT_FOUND;

@Repository
public class EmitterRepositoryImpl implements EmitterRepository {

    public static Map<Long, SseEmitter> sseEmitters = new ConcurrentHashMap<>();

    @Override
    public SseEmitter save(Long memberId, SseEmitter sseEmitter) {
        sseEmitters.put(memberId, sseEmitter);
        return sseEmitter;
    }

    @Override
    public void deleteById(Long memberId) {
        sseEmitters.remove(memberId);
    }

    @Override
    public SseEmitter findByMemberId(Long memberId) {
        if (sseEmitters.containsKey(memberId)) {
            return sseEmitters.get(memberId);
        } else {
            throw new CustomException(SSE_NOT_FOUND);
        }
    }
}
