package com.ssafy.nuguri.chat.domain;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.*;

@Data
@Builder
@Document(collection = "chatroom")
public class ChatRoom {

    @Id
    private String id;

    private String roomId;

    private Boolean isGroup;

    @Builder.Default
    private Set<Long> userList = new HashSet<>();

    private String roomName;

    private Long dealId;    // 중고거래를 위한 dealId

    // 1대1 채팅을 위한
    private String sender;
    private String receiver;


}
