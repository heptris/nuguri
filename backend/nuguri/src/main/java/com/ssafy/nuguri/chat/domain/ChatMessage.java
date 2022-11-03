package com.ssafy.nuguri.chat.domain;

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

    private String sender;  // 보내는 사람 닉네임
    private String message;
    private LocalDateTime createdDate;
    private String roomId;
    private MessageType messageType;
}
