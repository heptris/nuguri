package com.ssafy.nuguri.chat.repository;

import com.ssafy.nuguri.chat.domain.ChatMessage;
import com.ssafy.nuguri.chat.domain.ChatRoom;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface ChatRoomRepositoryCustom {

    public void updateChatRoom(Long roomId, Set<Long> userList);

    public Optional<ChatRoom> findChatRoomByRoomId(Long roomId);
}
