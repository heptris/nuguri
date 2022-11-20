package com.ssafy.nuguri.chat.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Comparator;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ChatRoomResponseDto implements Comparable<ChatRoomResponseDto> {

    private String roomName;
    private Long roomId;
    private String lastChatMessage;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastChatTime;
    private String senderImage;

    @Override
    public int compareTo(ChatRoomResponseDto o) {
        return o.getLastChatTime().compareTo(getLastChatTime());
    }
}
