package com.ssafy.nuguri.chat.repository;

import com.ssafy.nuguri.chat.domain.ChatRoom;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface ChatRoomRepository extends MongoRepository<ChatRoom, String>, ChatRoomRepositoryCustom  {

    public List<ChatRoom> findAllByUserListIn(Long memberId);

    Optional<ChatRoom> findChatRoomById(Long roomId);

    /**
     * 1대1 채팅방 찾기
     */
    Optional<ChatRoom> findChatRoomByUserList(Set<Long> collect);

    /**
     * 중고거래 했던 채팅방 찾기
     */
    Optional<ChatRoom> findChatRoomByDealHistoryId(Long dealHistoryId);

    /**
     * 취미모임 채팅방 찾기
     */
    Optional<ChatRoom> findChatRoomByHobbyId(Long hobbyId);



}
