package com.ssafy.nuguri.domain.alarm;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum AlarmCode {

    HOBBY_OWNER_ALARM("새로운 신청자가 있습니다", "프로필을 확인하고 모임 참여를 승인해주세요!");

    private final String title;
    private final String content;
}
