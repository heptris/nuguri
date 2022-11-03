package com.ssafy.nuguri.chat.repository;

import com.ssafy.nuguri.chat.domain.ChatMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

public class ChatRepositoryImpl implements ChatRepositoryCustom{

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public ChatMessage lastChatMessage(String roomId) {
        Query query = new Query(Criteria.where("roomId").is(roomId)).
                limit(1).with(Sort.by(Sort.Direction.DESC, "createdDate"));
        ChatMessage chatMessage = mongoTemplate.findOne(query, ChatMessage.class);
        return chatMessage;
    }
}
