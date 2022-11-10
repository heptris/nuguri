package com.ssafy.nuguri.chat.controller;

import com.ssafy.nuguri.chat.domain.ChatRoom;
import com.ssafy.nuguri.chat.dto.ChatMessageResponseDto;
import com.ssafy.nuguri.chat.dto.ChatRoomResponseDto;
import com.ssafy.nuguri.chat.dto.FindChatRoomDto;
import com.ssafy.nuguri.chat.dto.GetChatRoomHistoryDto;
import com.ssafy.nuguri.chat.service.ChatRoomService;
import com.ssafy.nuguri.dto.response.ResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
     * 채팅방 참가
     *
     * @return
     */
    @PostMapping("/join")
    public ResponseEntity getChatRoomHistory(@RequestBody GetChatRoomHistoryDto getChatRoomHistoryDto) {
        List<ChatMessageResponseDto> chatMessageResponseDtoList = chatRoomService.join(getChatRoomHistoryDto);
        return ResponseEntity.ok().body(
                new ResponseDto<>(200, "채팅 로그 불러오기", chatMessageResponseDtoList)
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
     * 내 채팅방 조회
     */
    @GetMapping("/{memberId}")
    public List<ChatRoomResponseDto> getMyRoomList(@PathVariable Long memberId) {
        return chatRoomService.findMyRoomList(memberId);
    }
}
