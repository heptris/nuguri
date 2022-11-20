package com.ssafy.nuguri.chat.service;

import com.ssafy.nuguri.chat.domain.ChatMessage;
import com.ssafy.nuguri.chat.domain.ChatRoom;
import com.ssafy.nuguri.chat.dto.ChatAlarmDto;
import com.ssafy.nuguri.chat.dto.ChatMessageDto;
import com.ssafy.nuguri.chat.repository.ChatRepository;
import com.ssafy.nuguri.chat.repository.ChatRoomRepository;
import com.ssafy.nuguri.exception.ex.CustomException;
import com.ssafy.nuguri.exception.ex.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static com.ssafy.nuguri.exception.ex.ErrorCode.CHATROOM_NOT_FOUND;

@RequiredArgsConstructor
@Transactional
@Service
public class ChatService {
    private final ChatRepository chatRepository;
    private final ChatRoomRepository chatRoomRepository;
    private final ApplicationEventPublisher eventPublisher;


    public List<ChatMessage> getChatLog() {
        return chatRepository.findAll();
    }

    public ChatMessage save(ChatMessageDto chatMessageDto) {
        ChatRoom chatRoom = chatRoomRepository.findChatRoomByRoomId(chatMessageDto.getRoomId()).orElseThrow(
                () -> new CustomException(CHATROOM_NOT_FOUND)
        );

        /**
         * 채팅 메세지 알람 보내기
         */
        List<Long> alarmReceivers = chatRoom.getUserList().stream().filter(memberId -> !memberId.equals(chatMessageDto.getSenderId())).collect(Collectors.toList());
        alarmReceivers.forEach(alarmReceiver -> {
            eventPublisher.publishEvent(new ChatAlarmDto(alarmReceiver, chatMessageDto.getMessage()));
        });
        /**
         * 채팅 메세지 알람 보내기 끝
         */

        ChatMessage chatMessage = chatMessageDto.toChatMessage();
        return chatRepository.save(chatMessage);
    }
}
