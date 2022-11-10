package com.ssafy.nuguri.chat.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class GetChatRoomHistoryDto {
    private Long senderId;
    private Long receiverId;    // 1대1 채팅일 경우만
    private String roomId;  // 참가할 채팅창 ID
}
