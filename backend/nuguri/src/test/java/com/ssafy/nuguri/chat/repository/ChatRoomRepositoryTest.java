package com.ssafy.nuguri.chat.repository;

import com.ssafy.nuguri.chat.domain.ChatMessage;
import com.ssafy.nuguri.chat.domain.ChatRoom;
import com.ssafy.nuguri.domain.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ChatRoomRepositoryTest {

    @Autowired
    private ChatRoomRepository chatRoomRepository;

    @Test
    public void test() {
        Optional<ChatRoom> chatRoomById = chatRoomRepository.findChatRoomByRoomId(2L);
        if (chatRoomById.isPresent()) {
            List<Long> collect = chatRoomById.get().getUserList().stream().filter(memberId -> !memberId.equals(1L)).collect(Collectors.toList());
            collect.forEach(System.out::println);
        }

    }

}