package com.ssafy.nuguri.chat.dto;

import com.ssafy.nuguri.chat.domain.ChatMessage;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ChatMessageDto {

    private String sender;
    private String message;
    private String roomId;

    private ChatMessage.MessageType messageType;

    public ChatMessage toChatMessage() {
        ChatMessage chatMessage = ChatMessage.builder().message(message).sender(sender).
        createdDate(LocalDateTime.now()).roomId(roomId).messageType(messageType).build();
        return chatMessage;
    }
}
