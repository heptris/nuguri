package com.ssafy.nuguri.domain.alarm;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum AlarmCode {

    HOBBY_OWNER_ALARM("새로운 신청자가 있습니다", "프로필을 확인하고 모임 참여를 승인해주세요!"),
    HOBBY_PARTICIPANT_ALARM_APPROVE(" 모임에 승인됐습니다", "모임에 참가해주시기 바랍니다"),
    HOBBY_PARTICIPANT_ALARM_REJECT(" 모임에 거절됐습니다", "다시 참여해주시기 바랍니다");


    private final String title;
    private final String content;
}
