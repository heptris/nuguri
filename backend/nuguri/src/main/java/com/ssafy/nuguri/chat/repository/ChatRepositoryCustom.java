package com.ssafy.nuguri.chat.repository;

import com.ssafy.nuguri.chat.domain.ChatMessage;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ChatRepositoryCustom {

    Optional<ChatMessage> lastChatMessage(Long roomId);

    List<ChatMessage> findChatHistoryPage(Long roomId, Pageable pageable);

    List<ChatMessage> findChatHistoryPageLessThan(Long roomId, Long cursorId, Pageable pageable);

    Boolean existsByLessThan(Long roomId, Long cursorId);

}
