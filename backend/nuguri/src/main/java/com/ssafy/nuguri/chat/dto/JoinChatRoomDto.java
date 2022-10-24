package com.ssafy.nuguri.chat.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class JoinChatRoomDto {
    private Long senderId;
    private Long receiverId;    // 1대1 채팅일 경우만
    private String roomId;  // 참가할 채팅창 ID
}
