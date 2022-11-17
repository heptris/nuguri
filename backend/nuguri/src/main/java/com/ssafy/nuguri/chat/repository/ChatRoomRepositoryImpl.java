package com.ssafy.nuguri.chat.repository;

import com.ssafy.nuguri.chat.domain.ChatRoom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.springframework.data.mongodb.core.query.Criteria.where;

public class ChatRoomRepositoryImpl implements ChatRoomRepositoryCustom {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void updateChatRoom(Long roomId, Set<Long> userList) {
        Query query = new Query();
        Update update = new Update();

        query.addCriteria(where("_id").is(roomId));
        update.set("userList", userList);
        mongoTemplate.updateMulti(query, update, "chatroom");
    }

    @Override
    public Optional<ChatRoom> findChatRoomByRoomId(Long roomId) {
        Query query = new Query();
        query.addCriteria(where("_id").is(roomId));
        return Optional.ofNullable(mongoTemplate.findOne(query, ChatRoom.class));
    }
}
