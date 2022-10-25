package com.ssafy.nuguri.controller.hobby;

import com.ssafy.nuguri.domain.hobby.ApproveStatus;
import com.ssafy.nuguri.domain.hobby.HobbyHistory;
import com.ssafy.nuguri.dto.hobby.HobbyDto;
import com.ssafy.nuguri.dto.hobby.HobbyHistoryDto;
import com.ssafy.nuguri.dto.response.ResponseDto;
import com.ssafy.nuguri.service.hobby.HobbyHistoryService;
import com.ssafy.nuguri.service.hobby.HobbyService;
import com.ssafy.nuguri.util.SecurityUtil;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        Long memberId = SecurityUtil.getCurrentMemberId();

        // 취미방 생성
        Long hobbyId = hobbyService.createHobby(hobbyDto);
        HobbyHistoryDto hobbyHistoryDto = HobbyHistoryDto.builder().hobbyId(hobbyId).memberId(memberId).isPromoter(true).approveStatus(ApproveStatus.READY).build();

        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseDto(HttpStatus.OK.value(), "취미방 생성 완료",hobbyHistoryService.createHobbyHistory(hobbyHistoryDto))
        );
    }



}
