package com.ssafy.nuguri.chat.service;

import com.ssafy.nuguri.chat.dto.ChatMessageResponseDto;
import com.ssafy.nuguri.chat.dto.CursorResult;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ChatRoomServiceTest {

    @Autowired
    private ChatRoomService chatRoomService;

    @Test
    public void test() {
        CursorResult<List<ChatMessageResponseDto>> listCursorResult = chatRoomService.get(1L, null, PageRequest.of(0, 10));
        System.out.println(listCursorResult);

    }

}