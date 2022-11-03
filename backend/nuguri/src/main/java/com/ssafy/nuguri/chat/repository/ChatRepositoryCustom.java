package com.ssafy.nuguri.chat.repository;

import com.ssafy.nuguri.chat.domain.ChatMessage;

public interface ChatRepositoryCustom {

    ChatMessage lastChatMessage(String roomId);
}
