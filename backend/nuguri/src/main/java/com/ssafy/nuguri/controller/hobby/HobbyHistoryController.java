package com.ssafy.nuguri.controller.hobby;

import com.ssafy.nuguri.domain.hobby.Hobby;
import com.ssafy.nuguri.domain.member.Member;
import com.ssafy.nuguri.dto.response.ResponseDto;
import com.ssafy.nuguri.service.hobby.HobbyHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/hobby/history") // hobby_history hobbyHistory
public class HobbyHistoryController {

    private final HobbyHistoryService hobbyHistoryService;

    // 취미방 참여 신청
    @PostMapping("/insert")
    public ResponseEntity<ResponseDto> regist(@RequestBody Hobby hobby, @RequestBody Member member){
        hobbyHistoryService.createHobbyHistory(hobby, member, false);
        return new ResponseEntity<ResponseDto>(new ResponseDto<>(), HttpStatus.OK);
    }

    // 취미방 참여 승인 또는 거절
    @PostMapping("/change")
    public ResponseEntity update(Long hobbyId, Long memberId){
        hobbyHistoryService.updateHobbyHistory(hobbyId, memberId);
        return new ResponseEntity<ResponseDto>(new ResponseDto<>(), HttpStatus.OK);
    }

    // 취미방에 참여하고 있는 멤버들
    @GetMapping("member")
    public ResponseEntity member(Long hobbyId){
        List<Member> result = hobbyHistoryService.HobbyRoomMember(hobbyId);

        return new ResponseEntity<ResponseDto>(new ResponseDto<>(), HttpStatus.OK);
    }

}
