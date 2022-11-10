package com.ssafy.nuguri.chat.repository;

import com.ssafy.nuguri.chat.domain.ChatRoom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;
import java.util.Set;

public class ChatRoomRepositoryImpl implements ChatRoomRepositoryCustom {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void updateChatRoom(Long roomId, Set<Long> userList) {
        Query query = new Query();
        Update update = new Update();

        query.addCriteria(Criteria.where("_id").is(roomId));
        update.set("userList", userList);
        mongoTemplate.updateMulti(query, update, "chatroom");
    }
}
