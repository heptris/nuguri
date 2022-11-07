package com.ssafy.nuguri.chat.service;

import com.ssafy.nuguri.chat.domain.ChatMessage;
import com.ssafy.nuguri.chat.domain.ChatRoom;
import com.ssafy.nuguri.chat.dto.ChatMessageDto;
import com.ssafy.nuguri.chat.repository.ChatRepository;
import com.ssafy.nuguri.chat.repository.ChatRoomRepository;
import com.ssafy.nuguri.exception.ex.CustomException;
import com.ssafy.nuguri.exception.ex.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static com.ssafy.nuguri.exception.ex.ErrorCode.CHATROOM_NOT_FOUND;

@RequiredArgsConstructor
@Transactional
@Service
public class ChatService {

    @Autowired
    private MongoTemplate mongoTemplate;
    private final ChatRepository chatRepository;
    private final ChatRoomRepository chatRoomRepository;

    public List<ChatMessage> getChatLog() {
        return chatRepository.findAll();
    }

    public ChatMessage save(ChatMessageDto chatMessageDto) {
        ChatMessage chatMessage = chatMessageDto.toChatMessage();
        return chatRepository.save(chatMessage);
    }
}
