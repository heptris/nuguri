package com.ssafy.nuguri.chat.repository;

import com.ssafy.nuguri.chat.domain.ChatMessage;

import java.util.Optional;

public interface ChatRepositoryCustom {

    Optional<ChatMessage> lastChatMessage(Long roomId);
}
