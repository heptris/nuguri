package com.ssafy.nuguri.chat.service;

import com.querydsl.core.types.Predicate;
import com.ssafy.nuguri.chat.domain.ChatMessage;
import com.ssafy.nuguri.chat.domain.ChatRoom;
import com.ssafy.nuguri.chat.dto.CreateChatRoomDto;
import com.ssafy.nuguri.chat.dto.JoinChatRoomDto;
import com.ssafy.nuguri.chat.repository.ChatRoomRepository;
import com.ssafy.nuguri.exception.ex.CustomException;
import com.ssafy.nuguri.exception.ex.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;

    public List<ChatRoom> findAll() {
        return (List<ChatRoom>) chatRoomRepository.findAll();
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
       //      조회 시 있으면 채팅방 새로 안만들고 return
        log.info("createChatRoomDto = {}", createChatRoomDto);
            // 1대1 채팅일 시
//        if (createChatRoomDto.getIsOneToOne()) {
//            Optional<ChatRoom> chatRoom = chatRoomRepository.
//                    findByIsOneToOneAndUserListInAndUserListIn(true, createChatRoomDto.getSenderId(), createChatRoomDto.getReceiverId());
//            if (chatRoom.isPresent()) {
//                return chatRoom.get().getRoomId();
//            }
//        }

        /**
         * 중고거래 채팅일 시
         */
        if (createChatRoomDto.getDealId() != null) {
            Optional<ChatRoom> chatRoomOptional = chatRoomRepository.findChatRoomByDealId(createChatRoomDto.getDealId());
            if (chatRoomOptional.isPresent()) { // 이미 채팅방이 생성돼어 있을 경우
                return chatRoomOptional.get().getRoomId();
            } else {
                ChatRoom chatRoom = createChatRoomDto.toEntity();
                chatRoomRepository.save(chatRoom);
                return chatRoom.getRoomId();
            }
        }

        /**
         * 취미모임 채팅일 시
         */
        if (createChatRoomDto.getHobbyId() != null) {
            Optional<ChatRoom> chatRoomOptional = chatRoomRepository.findChatRoomByHobbyId(createChatRoomDto.getHobbyId());
            if (chatRoomOptional.isPresent()) { // 이미 채팅방이 생성돼어 있을 경우
                return chatRoomOptional.get().getRoomId();
            } else {
                ChatRoom chatRoom = createChatRoomDto.toEntity();
                chatRoomRepository.save(chatRoom);
                return chatRoom.getRoomId();
            }
        }
        return null;
    }

    /**
     * 채팅방 참가
     */
    public void join(JoinChatRoomDto joinChatRoomDto) {
        ChatRoom chatRoom = chatRoomRepository.findChatRoomByRoomId(joinChatRoomDto.getRoomId()).orElseThrow(
                () -> new CustomException(ErrorCode.ALREADY_USED_NICKNAME)
        );
        chatRoom.getUserList().add(joinChatRoomDto.getSenderId());
        chatRoom.getUserList().add(joinChatRoomDto.getReceiverId());
        chatRoomRepository.save(chatRoom);
    }

    public ChatRoom findChatRoom(String roomId) {
        ChatRoom chatRoom = chatRoomRepository.findChatRoomByRoomId(roomId).orElseThrow(
                () -> new CustomException(ErrorCode.ALREADY_USED_NICKNAME)
        );
        return chatRoom;
    }
}
