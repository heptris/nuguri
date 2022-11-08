package com.ssafy.nuguri.controller.hobby;

import com.ssafy.nuguri.domain.hobby.ApproveStatus;
import com.ssafy.nuguri.domain.hobby.HobbyHistory;
import com.ssafy.nuguri.dto.hobby.HobbyCreateRequestDto;
import com.ssafy.nuguri.dto.hobby.HobbyDto;
import com.ssafy.nuguri.dto.hobby.HobbyHistoryDto;
import com.ssafy.nuguri.dto.hobby.HobbyListRequestDto;
import com.ssafy.nuguri.dto.response.ResponseDto;
import com.ssafy.nuguri.service.hobby.HobbyHistoryService;
import com.ssafy.nuguri.service.hobby.HobbyService;
import com.ssafy.nuguri.util.SecurityUtil;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("app/hobby")
public class HobbyController {
    private final HobbyService hobbyService;

    @ApiOperation(value ="해당 지역과 카데고리에 대한 취미방 목록 조회, 카테고리가 null인 경우 지역에 대한 취미방만 조회")
    @PostMapping("/list")
    public ResponseEntity findLocalCategoryHobbyList(@RequestBody HobbyListRequestDto hobbyListRequestDto){
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseDto(HttpStatus.OK.value(), "지역기반 취미방 목록",hobbyService.findLocalCategoryHobbyList(hobbyListRequestDto))
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
    public ResponseEntity regist(@RequestPart HobbyCreateRequestDto hobbyCreateRequestDto,
                                 @RequestPart(value = "file", required = false) MultipartFile hobbyImage){
        // 취미방 생성
        hobbyService.createHobby(hobbyCreateRequestDto,hobbyImage);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseDto(HttpStatus.OK.value(), "취미방 생성","취미방 생성 완료")
        );
    }




}
