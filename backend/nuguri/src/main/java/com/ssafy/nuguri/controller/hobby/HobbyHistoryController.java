package com.ssafy.nuguri.controller.hobby;

import com.ssafy.nuguri.domain.hobby.ApproveStatus;
import com.ssafy.nuguri.dto.hobby.HobbyHistoryDto;
import com.ssafy.nuguri.dto.response.ResponseDto;
import com.ssafy.nuguri.service.hobby.HobbyHistoryService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/hobby/history")
public class HobbyHistoryController {

    private final HobbyHistoryService hobbyHistoryService;
    @ApiOperation(value = "취미방 참여 신청")
    @PostMapping("/regist")
    public ResponseEntity regist(HobbyHistoryDto hobbyHistoryDto){
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseDto(HttpStatus.OK.value(), "취미방 참여", hobbyHistoryService.createHobbyHistory(hobbyHistoryDto))
        );
    }

    @ApiOperation(value = "취미방 참여자 리스트")
    @GetMapping("/{hobbyId}/participant")
    public ResponseEntity participant(@PathVariable Long hobbyId){
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseDto(HttpStatus.OK.value(), "취미방 참여자 목록", hobbyHistoryService.findParticipantList(hobbyId))
        );
    }

    @ApiOperation(value = "취미방 승인 대기자 리스트")
    @GetMapping("/{hobbyId}/waiting")
    public ResponseEntity waiting(@PathVariable Long hobbyId){
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseDto(HttpStatus.OK.value(), "취미방 승인 대기자 목록", hobbyHistoryService.findWaitingMemberList(hobbyId))
        );
    }

    @ApiOperation(value = "신청자 승인 또는 거절")
    @PutMapping("/changeStatus")
    public ResponseEntity changeStatus(Long hobbyHistoryId, ApproveStatus status){
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseDto(HttpStatus.OK.value(), "신청자 승인 또는 거절", hobbyHistoryService.changeStatus(hobbyHistoryId,status))
        );
    }

    @ApiOperation(value = "유저의 Status별 취미방 리스트")
    @GetMapping("/{userId}/{Status}/list")
    public ResponseEntity UserStatusHobbyList(@PathVariable Long userId, @PathVariable ApproveStatus status){
        return ResponseEntity.status(HttpStatus.OK).body(
                // 찜 숫자, 댓글숫자 담은 DTO로 보내주기
                new ResponseDto(HttpStatus.OK.value(), "상태를 기준으로 취미방 보여주기", hobbyHistoryService.findStatusHobbyList(userId,status))
        );
    }



}
