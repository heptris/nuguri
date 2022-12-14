package com.ssafy.nuguri.chat.controller;

import com.ssafy.nuguri.chat.dto.FindChatRoomDto;
import com.ssafy.nuguri.chat.service.ChatRoomService;
import com.ssafy.nuguri.chat.service.ChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("chat/test")
@Controller
public class RoomController {

    private final ChatService chatService;
    private final ChatRoomService chatRoomService;

    @GetMapping("/rooms")
    public ModelAndView rooms(){

        log.info("# All Chat Rooms");
        ModelAndView mv = new ModelAndView("chat/test/rooms");

        mv.addObject("list", chatRoomService.findAll());

        return mv;
    }

    //채팅방 개설
    @PostMapping(value = "/room")
    public String create(@RequestParam String name, RedirectAttributes rttr){
        log.info("# Create Chat Room , name: " + name);
        FindChatRoomDto createChatRoomDto = FindChatRoomDto.builder().roomName(name).build();
        chatRoomService.createChatRoomTest(createChatRoomDto);
        return "redirect:/chat/test/rooms";
    }

 //   채팅방 조회
    @GetMapping("/room")
    public void getRoom(Long roomId, Model model){

        log.info("# get Chat Room, roomID : " + roomId);
        //chatRoomService.joinTest(roomId);
        model.addAttribute("room", chatRoomService.findChatRoom(roomId));
    }

}
