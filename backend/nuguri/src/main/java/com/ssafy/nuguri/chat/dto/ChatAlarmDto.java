package com.ssafy.nuguri.chat.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ChatAlarmDto {

    private Long receiverId;
    private String message;
}
