package com.ssafy.nuguri.chat.repository;

import com.ssafy.nuguri.chat.domain.ChatMessage;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import java.util.List;

public interface ChatRepository extends MongoRepository<ChatMessage, String> {

    public List<ChatMessage> findAll();

    public List<ChatMessage> findChatMessageByRoomId(String roomId);
}
