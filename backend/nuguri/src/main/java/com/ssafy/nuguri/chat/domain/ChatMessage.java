package com.ssafy.nuguri.chat.domain;

import com.ssafy.nuguri.chat.dto.ChatMessageResponseDto;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Builder
@Getter
@Data
@Document(collection = "chat")
public class ChatMessage {

    public enum MessageType {
        TALK, ENTER, LEAVE
    }

    @Id
    private String id;

    private Long senderId;  // 보내는 사람 Id
    private String message;
    private LocalDateTime createdDate;
    private String roomId;
    private MessageType messageType;

    public ChatMessageResponseDto toChatMessageResponseDto() {
        return ChatMessageResponseDto.builder().message(message).messageType(messageType).chatTime(createdDate)
                .build();
    }
}
