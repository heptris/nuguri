package com.ssafy.nuguri.chat.controller;

import com.ssafy.nuguri.chat.domain.ChatMessage;
import com.ssafy.nuguri.chat.dto.CreateChatRoomDto;
import com.ssafy.nuguri.chat.service.ChatRoomService;
import com.ssafy.nuguri.chat.service.ChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
public class RoomController {

    private final ChatService chatService;
    private final ChatRoomService chatRoomService;

    @GetMapping("/chat/rooms")
    public ModelAndView rooms(){

        log.info("# All Chat Rooms");
        ModelAndView mv = new ModelAndView("chat/rooms");

        mv.addObject("list", chatRoomService.findAll());

        return mv;
    }

    //채팅방 개설
    @PostMapping(value = "/chat/room")
    public String create(@RequestParam String name, RedirectAttributes rttr){
        log.info("# Create Chat Room , name: " + name);
        CreateChatRoomDto createChatRoomDto = CreateChatRoomDto.builder().roomName(name).build();
        rttr.addFlashAttribute("roomName", chatRoomService.createChatRoom(createChatRoomDto));
        return "redirect:/chat/rooms";
    }

 //   채팅방 조회
    @GetMapping("/chat/room")
    public void getRoom(String roomId, Model model){

        log.info("# get Chat Room, roomID : " + roomId);
        model.addAttribute("room", chatRoomService.findChatRoom(roomId));
    }

}
