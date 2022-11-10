package com.ssafy.nuguri.chat.repository;

import com.ssafy.nuguri.chat.domain.ChatMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;
import java.util.Optional;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.*;

public class ChatRepositoryImpl implements ChatRepositoryCustom{

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public Optional<ChatMessage> lastChatMessage(Long roomId) {
        Query query = new Query(where("roomId").is(roomId))
              .with(Sort.by(Sort.Direction.DESC, "_id")).limit(1);
        ChatMessage chatMessage = mongoTemplate.findOne(query, ChatMessage.class);
        return Optional.ofNullable(chatMessage);
    }

    @Override
    public List<ChatMessage> findChatHistoryPage(Long roomId, Pageable pageable) {
        Query query = new Query().with(pageable).addCriteria(where("roomId").is(roomId))
                .with(Sort.by(Sort.Direction.DESC, "_id"));
        List<ChatMessage> chatMessages = mongoTemplate.find(query, ChatMessage.class);
        return chatMessages;
    }

    @Override
    public List<ChatMessage> findChatHistoryPageLessThan(Long roomId, Long cursorId, Pageable pageable) {
        Query query = new Query().with(pageable).addCriteria(where("roomId").is(roomId)
                        .and("_id").lt(cursorId))
                .with(Sort.by(Sort.Direction.DESC, "_id"));
        List<ChatMessage> chatMessages = mongoTemplate.find(query, ChatMessage.class);
        return chatMessages;
    }

    @Override
    public Boolean existsByLessThan(Long roomId, Long cursorId) {

        Query query = query(where("roomId").is(roomId).and("_id").lt(cursorId));
        return mongoTemplate.exists(query, ChatMessage.class);
    }

}
