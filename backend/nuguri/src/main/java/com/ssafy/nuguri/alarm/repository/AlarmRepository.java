package com.ssafy.nuguri.alarm.repository;

import com.ssafy.nuguri.domain.alarm.Alarm;
import com.ssafy.nuguri.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.List;

public interface AlarmRepository extends JpaRepository<Alarm, Long>, AlarmRepositoryCustom {

    List<Alarm> findAllByMember(Member member);
}
