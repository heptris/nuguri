package com.ssafy.nuguri.alarm.dto;

import com.ssafy.nuguri.domain.alarm.Alarm;
import com.ssafy.nuguri.domain.member.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class HobbyAlarmEventDto {

    private Member member;
    private String title;
    private String content;
    private Boolean isRead;
    private Long participantId;
    private String participantImage;

    private Long hobbyId;

    public Alarm toAlarm() {
        return Alarm.builder().member(member).title(title).content(content).isRead(isRead).participantId(participantId)
                .participantImage(participantImage).hobbyId(hobbyId).build();
    }

}
