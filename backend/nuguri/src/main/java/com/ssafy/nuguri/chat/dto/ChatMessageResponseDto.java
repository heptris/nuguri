package com.ssafy.nuguri.chat.dto;

import com.ssafy.nuguri.chat.domain.ChatMessage;
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
public class ChatMessageResponseDto {

    private String sender;
    private ChatMessage.MessageType messageType;
    private String message;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime chatTime;
}
