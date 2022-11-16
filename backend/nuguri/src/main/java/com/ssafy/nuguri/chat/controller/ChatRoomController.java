package com.ssafy.nuguri.chat.controller;

import com.ssafy.nuguri.chat.domain.ChatRoom;
import com.ssafy.nuguri.chat.dto.*;
import com.ssafy.nuguri.chat.service.ChatRoomService;
import com.ssafy.nuguri.domain.s3.AwsS3;
import com.ssafy.nuguri.dto.response.ResponseDto;
import com.ssafy.nuguri.exception.ex.CustomException;
import com.ssafy.nuguri.service.s3.AwsS3Service;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import static com.ssafy.nuguri.exception.ex.ErrorCode.FILE_UPLOAD_ERROR;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/app/chat/room")
public class ChatRoomController {

    private final ChatRoomService chatRoomService;
    private final AwsS3Service awsS3Service;

    /**
     * 채팅방 생성
     */
    @ApiOperation(value = "채팅 할 채팅방 조회", notes = "채팅 할 채팅방 조희 API")
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
    @ApiOperation(value = "채팅방 이전 채팅 기록 조회", notes = "채팅방 이전 채팅 기록 조회 API")
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
    @ApiOperation(value = "내 채팅방 목록 조회", notes = "내 채팅방 목록 조회 API")
    @GetMapping("/{memberId}")
    public List<ChatRoomResponseDto> getMyRoomList(@PathVariable Long memberId) {
        return chatRoomService.findMyRoomList(memberId);
    } 

    @ApiOperation(value = "채팅방 파일 업로드", notes = "채팅방 파일 업로드 API")
    @PostMapping("/file")
    public ResponseEntity fileUpLoad(@RequestParam("file") MultipartFile file, @RequestPart Long roomId) {
        try {
            AwsS3 upload = awsS3Service.upload(file, "Room" + String.valueOf(roomId));
            return ResponseEntity.ok().body(
                    new ResponseDto(200, "file upload 성공", upload.getPath())
            );
        } catch (IOException e) {
            throw new CustomException(FILE_UPLOAD_ERROR);
        }
    }
}
