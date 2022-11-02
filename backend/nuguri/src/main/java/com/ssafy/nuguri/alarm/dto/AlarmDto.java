package com.ssafy.nuguri.alarm.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class AlarmDto {

    private Long alarmId;
    private String title;
    private String content;
    private Long participantId;
    private Boolean isRead;
    private String participantImage;
    private Long hobbyId;

}
