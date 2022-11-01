package com.ssafy.nuguri.alarm.repository;

import com.ssafy.nuguri.domain.alarm.Alarm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

public interface AlarmRepository extends JpaRepository<Alarm, Long> {

}
