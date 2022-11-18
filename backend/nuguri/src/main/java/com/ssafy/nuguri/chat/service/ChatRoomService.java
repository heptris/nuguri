package com.ssafy.nuguri.chat.service;

import com.ssafy.nuguri.chat.domain.ChatMessage;
import com.ssafy.nuguri.chat.domain.ChatRoom;
import com.ssafy.nuguri.chat.dto.*;
import com.ssafy.nuguri.chat.repository.ChatRepository;
import com.ssafy.nuguri.chat.repository.ChatRoomRepository;
import com.ssafy.nuguri.config.redis.RedisService;
import com.ssafy.nuguri.exception.ex.CustomException;
import com.ssafy.nuguri.exception.ex.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;
    private final ChatRepository chatRepository;
    private final RedisService redisService;

    private final ApplicationEventPublisher eventPublisher;


    public List<ChatRoom> findAll() {
        return chatRoomRepository.findAll();
//        List<ChatRoom> chatRoomList= chatRoomRepository.findAll();
//        List<ChatRoomResponseDto> chatRoomResponseDtoList = new ArrayList<>();
//        chatRoomList.forEach(chatRoom -> {
//            ChatMessage chatMessage = chatRepository.lastChatMessage(chatRoom.getRoomId());
//            ChatRoomResponseDto chatRoomResponseDto = ChatRoomResponseDto.builder().roomName(chatRoom.getRoomName())
//                    .roomId(chatRoom.getRoomId()).lastChatMessage(chatMessage.getMessage())
//                    .lastChatTime(chatMessage.getCreatedDate()).build();
//            chatRoomResponseDtoList.add(chatRoomResponseDto);
//        });
//
//        Collections.sort(chatRoomResponseDtoList);
//        chatRoomResponseDtoList.forEach(chatRoomResponseDto -> System.out.println(chatRoomResponseDto));
//        return chatRoomList;
    }

    /**
     * 내가 속해있는 채팅방 조회(채팅창 목록)
     */
    public List<ChatRoomResponseDto> findMyRoomList(Long memberId) {
        List<ChatRoom> chatRoomList = chatRoomRepository.findAllByUserListIn(memberId);
        List<ChatRoomResponseDto> chatRoomResponseDtoList = new ArrayList<>();
        chatRoomList.forEach(chatRoom -> {
            Optional<ChatMessage> chatMessageOp = chatRepository.lastChatMessage(chatRoom.getId());
            if (chatMessageOp.isPresent()) {
                ChatMessage chatMessage = chatMessageOp.get();
                ChatRoomResponseDto chatRoomResponseDto = null;
                if (chatRoom.getDealHistoryId() != null) {
                    String roomName = null;
                    for (Long next : chatRoom.getUserList()) {
                        if (!next.equals(memberId)) {
                            roomName = redisService.getValues(String.valueOf(next) + ".");
                        }
                    }
                    chatRoomResponseDto = ChatRoomResponseDto.builder()
                            .roomName(roomName)
                            .roomId(chatRoom.getId()).lastChatMessage(chatMessage.getMessage())
                            .lastChatTime(chatMessage.getCreatedDate()).build();
                    chatRoomResponseDto.setSenderImage(redisService.getValues(String.valueOf(chatMessage.getSenderId()) + "@"));
                } else if (chatRoom.getHobbyId() != null) {
                    chatRoomResponseDto = ChatRoomResponseDto.builder().roomName(chatRoom.getRoomName())
                            .roomId(chatRoom.getId()).lastChatMessage(chatMessage.getMessage())
                            .lastChatTime(chatMessage.getCreatedDate()).build();
                    chatRoomResponseDto.setSenderImage(redisService.getValues(String.valueOf(chatMessage.getSenderId()) + "@"));
                }
                chatRoomResponseDtoList.add(chatRoomResponseDto);
            }
        });

        Collections.sort(chatRoomResponseDtoList);
        return chatRoomResponseDtoList;
    }

    /**
     * 채팅방 생성
     */
    public Long createChatRoom(FindChatRoomDto findChatRoomDto) {
        log.info("createChatRoomDto = {}", findChatRoomDto);
            // 1대1 채팅일 시
        if (findChatRoomDto.getIsOneToOne() != null && findChatRoomDto.getIsOneToOne()) {
           // 1대1 채팅방 있는지 찾기
            Set<Long> collect = Stream.of(findChatRoomDto.getSenderId(), findChatRoomDto.getReceiverId()).collect(Collectors.toSet());
//            List<ChatRoom> chatRooms = mongoTemplate.find(
//                    Query.query(Criteria.where("userList").is(collect)), ChatRoom.class);
            Optional<ChatRoom> chatRoomOptional = chatRoomRepository.findChatRoomByUserList(collect);
            if (chatRoomOptional.isPresent()) { // 이미 1대1 채팅방이 있을 경우
                return chatRoomOptional.get().getId();
            } else {
                ChatRoom chatRoom = findChatRoomDto.toEntity();
                chatRoomRepository.save(chatRoom);
                return chatRoom.getId();
            }
        }

        /**
         * 중고거래 채팅일 시
         */
        if (findChatRoomDto.getDealHistoryId() != null) {
            Optional<ChatRoom> chatRoomOptional = chatRoomRepository.findChatRoomByDealHistoryId(findChatRoomDto.getDealHistoryId());
            if (chatRoomOptional.isPresent()) { // 이미 채팅방이 생성돼어 있을 경우
                ChatRoom chatRoom = chatRoomOptional.get();
//                chatRoom.getUserList().add(findChatRoomDto.getSenderId());
//                chatRoom.getUserList().add(findChatRoomDto.getReceiverId());
//                chatRoomRepository.updateChatRoom(chatRoom.getId(), chatRoom.getUserList());
                return chatRoom.getId();
            } else {
                ChatRoom chatRoom = findChatRoomDto.toEntity();
                chatRoom.getUserList().add(findChatRoomDto.getSenderId());
                chatRoom.getUserList().add(findChatRoomDto.getReceiverId());
                chatRoomRepository.save(chatRoom);
                return chatRoom.getId();
            }
        }

        /**
         * 취미모임 채팅일 시
         */
        if (findChatRoomDto.getHobbyId() != null) {
            Optional<ChatRoom> chatRoomOptional = chatRoomRepository.findChatRoomByHobbyId(findChatRoomDto.getHobbyId());
            if (chatRoomOptional.isPresent()) { // 이미 채팅방이 생성돼어 있을 경우
                ChatRoom chatRoom = chatRoomOptional.get();
                chatRoom.getUserList().add(findChatRoomDto.getSenderId());
                chatRoomRepository.updateChatRoom(chatRoom.getId(), chatRoom.getUserList());
                return chatRoom.getId();
            } else {
                ChatRoom chatRoom = findChatRoomDto.toEntity();
                chatRoom.getUserList().add(findChatRoomDto.getSenderId());
                chatRoomRepository.save(chatRoom);
                return chatRoom.getId();
            }
        }
        return null;
    }

    /**
     * 채팅방 참가 및 채팅방 채팅 기록 가져오기(커서 페이징)
     */
    public CursorResult<List<ChatMessageResponseDto>> get(Long roomId, Long cursorId, Pageable pageable) {
        List<ChatMessage> chatRoomHistory = getChatRoomHistory(roomId, cursorId, pageable);
        Long lastIdOfList = chatRoomHistory.isEmpty() ? null : chatRoomHistory.get(chatRoomHistory.size() - 1).getId();

        // chatmessage -> ChatMessResponeDto
        List<ChatMessageResponseDto> chatMessageResponseDtoList = getChatMessageResponseDtos(chatRoomHistory);

        return new CursorResult<List<ChatMessageResponseDto>>(chatMessageResponseDtoList, hasNext(roomId, lastIdOfList), lastIdOfList);
    }

    /**
     * ChatMessage -> ChatMessageResponseDto
     */
    private List<ChatMessageResponseDto> getChatMessageResponseDtos(List<ChatMessage> chatRoomHistory) {
        List<ChatMessageResponseDto> chatMessageResponseDtoList = new ArrayList<>();
        chatRoomHistory.forEach(chatMessage -> {
            ChatMessageResponseDto chatMessageResponseDto = chatMessage.toChatMessageResponseDto();
            chatMessageResponseDto.setSender(redisService.getValues(String.valueOf(chatMessage.getSenderId()) + "."));
            chatMessageResponseDtoList.add(chatMessageResponseDto);
        });
        return chatMessageResponseDtoList;
    }

    public List<ChatMessage> getChatRoomHistory(Long roomId, Long cursorId, Pageable pageable) {

        return cursorId == null ? chatRepository.findChatHistoryPage(roomId, pageable) :
                chatRepository.findChatHistoryPageLessThan(roomId, cursorId, pageable);
    }

    public Boolean hasNext(Long roomId, Long cursorId) {
        if (cursorId == null) return Boolean.FALSE;
        return chatRepository.existsByLessThan(roomId, cursorId);
    }

    public ChatRoom findChatRoom(Long roomId) {
        ChatRoom chatRoom = chatRoomRepository.findChatRoomById(roomId).orElseThrow(
                () -> new CustomException(ErrorCode.ALREADY_USED_NICKNAME)
        );
        System.out.println(chatRoom);
        return chatRoom;
    }

    /**
     * 테스트 용도로 만듬
     */
    public String createChatRoomTest(FindChatRoomDto createChatRoomDto) {
        chatRoomRepository.save(createChatRoomDto.toEntity());
        return createChatRoomDto.getRoomName();
    }

    /**
     * 테스트용도
     */
    public void joinTest(Long roomId) {
        List<ChatMessage> chatMessages = chatRepository.findChatMessageByRoomIdOrderByCreatedDateDesc(roomId);
        chatMessages.forEach(System.out::println);
    }


}
