package com.ssafy.nuguri.chat.controller;

import com.ssafy.nuguri.chat.domain.ChatRoom;
import com.ssafy.nuguri.chat.dto.CreateChatRoomDto;
import com.ssafy.nuguri.chat.dto.JoinChatRoomDto;
import com.ssafy.nuguri.chat.repository.ChatRoomRepository;
import com.ssafy.nuguri.chat.service.ChatRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/room")
public class ChatRoomController {

    private final ChatRoomService chatRoomService;

    /**
     * 채팅방 생성
     */
    @PostMapping
    public String createChatRoom(@RequestBody CreateChatRoomDto createChatRoomDto) {
        return chatRoomService.createChatRoom(createChatRoomDto);
    }

    /**
     * 채팅방 참가
     */
    @PostMapping("/join")
    public void joinChatRoom(@RequestBody JoinChatRoomDto joinChatRoomDto) {
        chatRoomService.join(joinChatRoomDto);
    }

    /**
     * 모든 채팅방 조회
     */
    @GetMapping()
    public List<ChatRoom> chatRoomList() {
        return chatRoomService.findAll();
    }

    /**
     *  내 채팅방 조회 
     */
    @GetMapping("/{memberId}")
    public List<ChatRoom> getMyRoomList(@PathVariable Long memberId) {
        return chatRoomService.findMyRoomList(memberId);
    }
}
