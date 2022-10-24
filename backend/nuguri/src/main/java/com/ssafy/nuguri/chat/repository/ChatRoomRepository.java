package com.ssafy.nuguri.chat.repository;

import com.ssafy.nuguri.chat.domain.ChatMessage;
import com.ssafy.nuguri.chat.domain.ChatRoom;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface ChatRoomRepository extends MongoRepository<ChatRoom, String> {

    public List<ChatRoom> findAll();

    public List<ChatRoom> findAllByUserListIn(Long memberId);

    Optional<ChatRoom> findChatRoomByRoomId(String roomId);

}
