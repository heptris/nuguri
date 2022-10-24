package com.ssafy.nuguri.controller.hobby;

import com.ssafy.nuguri.domain.hobby.Hobby;
import com.ssafy.nuguri.dto.hobby.HobbyDto;
import com.ssafy.nuguri.dto.member.member.Member;
import com.ssafy.nuguri.dto.response.ResponseDto;
import com.ssafy.nuguri.service.hobby.HobbyHistoryService;
import com.ssafy.nuguri.service.hobby.HobbyService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/hobby")
public class HobbyController {
    private final HobbyService hobbyService;
    private final HobbyHistoryService hobbyHistoryService;

    @ApiOperation(value ="해당 지역에 대한 취미방 목록 조회")
    @GetMapping("/{localId}/list")
    public ResponseEntity findLocalHobbyList(@PathVariable Long localId){
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseDto(HttpStatus.OK.value(), "지역기반 취미방 목록",hobbyService.findLocalHobbyList(localId))
        );
    }

    @ApiOperation(value ="해당 지역과 카테고리에 대한 취미방 목록 조회")
    @GetMapping("/{localId}/{categoryId}/list")
    public ResponseEntity findLocalCategoryHobbyList(@PathVariable Long localId, @PathVariable Long categoryId){
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseDto(HttpStatus.OK.value(), "지역과 카테고리 기반 취미방 목록",hobbyService.findLocalCategoryHobbyList(localId, categoryId))
        );
    }

    @ApiOperation(value="취미방 상세페이지 조회")
    @GetMapping("/{hobbyId}/detail")
    public ResponseEntity findHobbyDetail(@PathVariable Long hobbyId){
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseDto(HttpStatus.OK.value(), "취미방 상세정보",hobbyService.findHobbyDetail(hobbyId))
        );
    }

    @ApiOperation(value="취미방 생성")
    @PostMapping("/regist")
    public ResponseEntity regist(HobbyDto hobbyDto){
        // HobbyHistoryController의 regist호출 필요

        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseDto(HttpStatus.OK.value(), "취미방 생성",hobbyService.createHobby(hobbyDto))
        );
    }



}
