package com.ssafy.nuguri.chat.repository;

import com.ssafy.nuguri.chat.domain.ChatRoom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.List;

public class ChatRoomRepositoryImpl implements ChatRoomRepositoryCustom {

    @Autowired
    private MongoTemplate mongoTemplate;

}
