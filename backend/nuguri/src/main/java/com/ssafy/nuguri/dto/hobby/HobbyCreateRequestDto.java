package com.ssafy.nuguri.dto.hobby;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class HobbyCreateRequestDto {
    private Long localId;
    private Long categoryId;
    private String title;

    private String content;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endDate;

    private String meetingPlace;

    private int maxNum;

    private int fee;

    private int ageLimit;

    private char sexLimit;


}
