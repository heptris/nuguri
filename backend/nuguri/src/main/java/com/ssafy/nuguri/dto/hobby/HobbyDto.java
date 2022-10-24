package com.ssafy.nuguri.dto.hobby;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class HobbyDto {
    private Long hobbyId;
    private Long localId;
    private Long categoryId;
    private String title;

    private String content;

    private LocalDateTime endDate;

    private String meetingPlace;

    private boolean isClosed;

    private int curNum;

    private int maxNum;

    private int fee;

    private int ageLimit;

    private char sexLimit;

    private String hobbyImage;

}
