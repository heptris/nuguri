package com.ssafy.nuguri.chat.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CreateChatRoomDto {
    private String roomName;
    private Boolean isGroup;
}
