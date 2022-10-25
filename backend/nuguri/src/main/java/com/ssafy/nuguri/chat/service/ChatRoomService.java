package com.ssafy.nuguri.chat.service;

import com.ssafy.nuguri.chat.domain.ChatMessage;
import com.ssafy.nuguri.chat.domain.ChatRoom;
import com.ssafy.nuguri.chat.dto.CreateChatRoomDto;
import com.ssafy.nuguri.chat.dto.JoinChatRoomDto;
import com.ssafy.nuguri.chat.repository.ChatRepository;
import com.ssafy.nuguri.chat.repository.ChatRoomRepository;
import com.ssafy.nuguri.exception.ex.CustomException;
import com.ssafy.nuguri.exception.ex.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class ChatRoomService {

    @Autowired
    private MongoTemplate mongoTemplate;
    private final ChatRoomRepository chatRoomRepository;
    private final ChatRepository chatRepository;

    public List<ChatRoom> findAll() {
        return mongoTemplate.findAll(ChatRoom.class);
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
        log.info("createChatRoomDto = {}", createChatRoomDto);
            // 1대1 채팅일 시
        if (createChatRoomDto.getIsOneToOne()) {
           // 1대1 채팅방 있는지 찾기
            Set<Long> collect = Stream.of(createChatRoomDto.getSenderId(), createChatRoomDto.getReceiverId()).collect(Collectors.toSet());
//            List<ChatRoom> chatRooms = mongoTemplate.find(
//                    Query.query(Criteria.where("userList").is(collect)), ChatRoom.class);
            Optional<ChatRoom> chatRoomOptional = chatRoomRepository.findChatRoomByUserList(collect);
            if (chatRoomOptional.isPresent()) { // 이미 1대1 채팅방이 있을 경우
                return chatRoomOptional.get().getRoomId();
            } else {
                ChatRoom chatRoom = createChatRoomDto.toEntity();
                chatRoomRepository.save(chatRoom);
                return chatRoom.getRoomId();
            }
        }

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
     *
     * @return
     */
    public List<ChatMessage> join(JoinChatRoomDto joinChatRoomDto) {
        ChatRoom chatRoom = chatRoomRepository.findChatRoomByRoomId(joinChatRoomDto.getRoomId()).orElseThrow(
                () -> new CustomException(ErrorCode.CHATROOM_NOT_FOUND)
        );
        chatRoom.getUserList().add(joinChatRoomDto.getSenderId());
        if (joinChatRoomDto.getReceiverId() != null) {
            chatRoom.getUserList().add(joinChatRoomDto.getReceiverId());
        }
        chatRoomRepository.save(chatRoom);
        List<ChatMessage> chatMessages = chatRepository.findChatMessageByRoomId(chatRoom.getRoomId());
        return chatMessages;
    }

    public ChatRoom findChatRoom(String roomId) {
        ChatRoom chatRoom = chatRoomRepository.findChatRoomByRoomId(roomId).orElseThrow(
                () -> new CustomException(ErrorCode.ALREADY_USED_NICKNAME)
        );
        return chatRoom;
    }

    /**
     * 테스트 용도로 만듬
     */
    public String createChatRoomTest(CreateChatRoomDto createChatRoomDto) {
        chatRoomRepository.save(createChatRoomDto.toEntity());
        return createChatRoomDto.getRoomName();
    }

    /**
     * 테스트용도
     */
    public void joinTest(String roomId) {
        List<ChatMessage> chatMessages = chatRepository.findChatMessageByRoomId(roomId);
        chatMessages.forEach(chatMessage -> {
            System.out.println(chatMessage);
        });
    }
}
