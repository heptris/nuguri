package com.ssafy.nuguri.chat.service;

import com.ssafy.nuguri.chat.domain.ChatMessage;
import com.ssafy.nuguri.chat.dto.ChatMessageDto;
import com.ssafy.nuguri.chat.repository.ChatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class ChatService {
    private final ChatRepository chatRepository;
    private final SimpMessagingTemplate simpMessagingTemplate;

    public List<ChatMessage> getChatLog() {
        return chatRepository.findAll();
    }

    public void save(ChatMessageDto chatMessageDto) {
        ChatMessage chatMessage = chatMessageDto.toChatMessage();
        chatRepository.save(chatMessage);
    }
}
