package com.ssafy.nuguri.chat.controller;

import com.ssafy.nuguri.chat.domain.ChatMessage;
import com.ssafy.nuguri.chat.dto.ChatMessageDto;
import com.ssafy.nuguri.chat.repository.ChatRepository;
import com.ssafy.nuguri.chat.service.ChatService;
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
@RequestMapping("/chat")
@RestController
public class ChatController {
    private final ChatService chatService;
    private final SimpMessagingTemplate template;

    @GetMapping()
    public List<ChatMessage> chatLog() {
        return chatService.getChatLog();
    }

    @MessageMapping
    public void save(@RequestBody ChatMessageDto message)
    {
        if (message.getMessageType().equals(ENTER)) {
            message.setMessage(message.getSender()+ "님이 입장하셨습니다.");
        } else if (message.getMessageType().equals(LEAVE)) {
            message.setMessage(message.getSender() + "님이 나갔습니다");
        }
        chatService.save(message);
        template.convertAndSend("/sub/chat/room/" + message.getRoomId(), message);
    }


}
