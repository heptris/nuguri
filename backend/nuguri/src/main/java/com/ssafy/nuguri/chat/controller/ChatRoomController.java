package com.ssafy.nuguri.chat.controller;

import com.ssafy.nuguri.chat.domain.ChatRoom;
import com.ssafy.nuguri.chat.dto.*;
import com.ssafy.nuguri.chat.service.ChatRoomService;
import com.ssafy.nuguri.dto.response.ResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/app/chat/room")
public class ChatRoomController {

    private final ChatRoomService chatRoomService;

    /**
     * 채팅방 생성
     */
    @PostMapping
    public ResponseEntity findChatRoom(@RequestBody FindChatRoomDto findChatRoomDto) {
        Long roomId = chatRoomService.createChatRoom(findChatRoomDto);
        return ResponseEntity.ok().body(
                new ResponseDto<>(200, "채팅방 Id 조회", roomId)
        );
    }

    /**
     * (채팅방 이전 채팅 기록 조회)
     */
    @PostMapping("/log")
    public ResponseEntity getChatRoomHistory(@RequestBody GetChatRoomHistoryDto getChatRoomHistoryDto) {

        CursorResult<?> cursorResult = chatRoomService.get(getChatRoomHistoryDto.getRoomId(), getChatRoomHistoryDto.getCursorId(),
                PageRequest.of(0, 10));
        return ResponseEntity.ok().body(
                new ResponseDto<>(200, "채팅 로그 불러오기", cursorResult)
        );
    }

    /**
     * 모든 채팅방 조회
     */
    @GetMapping()
    public List<ChatRoom> chatRoomList() {
        return chatRoomService.findAll();
    }

    /**
     * 내가 속해있는 채팅방 조회
     */
    @GetMapping("/{memberId}")
    public List<ChatRoomResponseDto> getMyRoomList(@PathVariable Long memberId) {
        return chatRoomService.findMyRoomList(memberId);
    }
}
