package com.ssafy.nuguri.chat.dto;

import com.ssafy.nuguri.chat.domain.ChatRoom;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class FindChatRoomDto {
    private String roomName;
    private Long dealHistoryId;    // 중고거래 1대1 채팅일 경우
    private Long hobbyId;   // 취미 등록 채팅일 경우
    private Boolean isOneToOne; // 순수 1대1 채팅을 위한 경우

    private Long senderId;
    private Long receiverId;    // 1대1 채팅일 시

    public ChatRoom toEntity() {
        return ChatRoom.builder().roomId(UUID.randomUUID().toString()).isOneToOne(isOneToOne)
                .hobbyId(hobbyId).dealHistoryId(dealHistoryId)
                .roomName(roomName).build();
    }
}
