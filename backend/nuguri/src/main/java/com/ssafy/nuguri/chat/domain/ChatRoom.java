package com.ssafy.nuguri.chat.domain;

import com.querydsl.core.annotations.QueryEntity;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.*;

@Data
@Builder
@Document(collection = "chatroom")
public class ChatRoom {

    @Transient
    public static final String SEQUENCE_NAME = "chatroom_sequence";

    @Id
    private Long id;

    @Builder.Default
    private Set<Long> userList = new HashSet<>();

    private String roomName;

    private Long dealHistoryId;    // 중고거래를 위한 dealId
    private Long hobbyId;   // 취미 모임 채팅을 위한 hobbyId

    // 순수 1대1 채팅일 경우
    private Boolean isOneToOne;
    private Long senderId;
    private Long receiverId;
}
