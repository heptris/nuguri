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
    private Long cursorId;
    private Long roomId;  // 참가할 채팅창 ID
}
