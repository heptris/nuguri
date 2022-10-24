package com.ssafy.nuguri.chat.controller;

import com.ssafy.nuguri.chat.domain.ChatMessage;
import com.ssafy.nuguri.chat.dto.ChatMessageDto;
import com.ssafy.nuguri.chat.repository.ChatRepository;
import com.ssafy.nuguri.chat.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.converter.SimpleMessageConverter;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ChatController {
    private final ChatService chatService;

    @GetMapping("/chat")
    public List<ChatMessage> chatLog() {
        return chatService.getChatLog();
    }

    @GetMapping("/chatlog/{roomId}")
    public List<ChatMessage> chatLogRoomId(@PathVariable String roomId) {
        return chatService.getChatLogByRoomId(roomId);
    }

    @PostMapping("/chat")
    public void update(@RequestBody ChatMessageDto chatMessageDto) {
        chatService.save(chatMessageDto);
    }

}
