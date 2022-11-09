package com.ssafy.nuguri.chat.controller;

import com.ssafy.nuguri.chat.domain.ChatMessage;
import com.ssafy.nuguri.chat.dto.ChatMessageDto;
import com.ssafy.nuguri.chat.dto.ChatMessageResponseDto;
import com.ssafy.nuguri.chat.repository.ChatRepository;
import com.ssafy.nuguri.chat.service.ChatService;
import com.ssafy.nuguri.config.redis.RedisService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.converter.SimpleMessageConverter;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.ssafy.nuguri.chat.domain.ChatMessage.MessageType.*;

@RequiredArgsConstructor
@Slf4j
@RestController
public class ChatController {
    private final ChatService chatService;
    private final SimpMessagingTemplate template;

    private final RedisService redisService;

    @GetMapping()
    public List<ChatMessage> chatLog() {
        return chatService.getChatLog();
    }

    @MessageMapping(value = "/chat")
    public void save(@RequestBody ChatMessageDto message) {

        log.info("message : {}", message);
        String sender = redisService.getValues(String.valueOf(message.getSenderId()) + ".");
        if (message.getMessageType().equals(ENTER)) {
            message.setMessage(sender + " 님이 입장하셨습니다.");
        } else if (message.getMessageType().equals(LEAVE)) {
            message.setMessage(sender + "님이 나갔습니다");
        }

        ChatMessage chatMessage = chatService.save(message);
        ChatMessageResponseDto chatMessageResponseDto = chatMessage.toChatMessageResponseDto();
        chatMessageResponseDto.setSender(sender);
        template.convertAndSend("/sub/chat/room/" + message.getRoomId(), chatMessageResponseDto);
    }


}
