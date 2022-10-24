package com.ssafy.nuguri.chat.service;

import com.ssafy.nuguri.chat.domain.ChatMessage;
import com.ssafy.nuguri.chat.domain.ChatRoom;
import com.ssafy.nuguri.chat.dto.CreateChatRoomDto;
import com.ssafy.nuguri.chat.dto.JoinChatRoomDto;
import com.ssafy.nuguri.chat.repository.ChatRoomRepository;
import com.ssafy.nuguri.exception.ex.CustomException;
import com.ssafy.nuguri.exception.ex.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@RequiredArgsConstructor
@Transactional
@Service
public class ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;

    public List<ChatRoom> findAll() {
        return chatRoomRepository.findAll();
    }

    /**
     * 내가 속해있는 채팅방 조회
     */
    public List<ChatRoom> findMyRoomList(Long memberId) {
        return chatRoomRepository.findAllByUserListIn(memberId);
    }

    /**
     * 채팅방 생성 
     */
    public String createChatRoom(CreateChatRoomDto createChatRoomDto) {
            ChatRoom chatRoom = ChatRoom.builder().roomName(createChatRoomDto.getRoomName()).roomId(UUID.randomUUID().toString())
                    .isGroup(createChatRoomDto.getIsGroup()).build();
            chatRoomRepository.save(chatRoom);
            return chatRoom.getRoomId();
    }

    /**
     * 채팅방 참가
     */
    public void join(JoinChatRoomDto joinChatRoomDto) {
        ChatRoom chatRoom = chatRoomRepository.findChatRoomByRoomId(joinChatRoomDto.getRoomId()).orElseThrow(
                () -> new CustomException(ErrorCode.ALREADY_USED_NICKNAME)
        );
        System.out.println(chatRoom);
        chatRoom.getUserList().add(joinChatRoomDto.getSenderId());
        chatRoomRepository.save(chatRoom);
    }

    public ChatRoom findChatRoom(String roomId) {
        ChatRoom chatRoom = chatRoomRepository.findChatRoomByRoomId(roomId).orElseThrow(
                () -> new CustomException(ErrorCode.ALREADY_USED_NICKNAME)
        );
        return chatRoom;
    }
}
