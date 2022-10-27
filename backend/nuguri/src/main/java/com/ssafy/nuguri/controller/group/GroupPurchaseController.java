package com.ssafy.nuguri.controller.group;

import com.ssafy.nuguri.dto.deal.DealRegistRequestDto;
import com.ssafy.nuguri.dto.group.GroupPurchaseRegistRequestDto;
import com.ssafy.nuguri.dto.response.ResponseDto;
import com.ssafy.nuguri.service.group.GroupPurchaseService;
import com.ssafy.nuguri.util.SecurityUtil;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/app/group")
public class GroupPurchaseController {

    private final GroupPurchaseService groupPurchaseService;

    @ApiOperation(value = "해당 지역, 취미에 대한 공동구매 목록 조회")
    @GetMapping("/{localId}/{categoryId}/list")
    public ResponseEntity findGroupByLocalAndCategory(@PathVariable Long localId,@PathVariable Long categoryId){
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseDto(HttpStatus.OK.value(), "공동구매 목록 조회", groupPurchaseService.findGroupByLocalAndCategory(localId, categoryId))
        );
    }

    @ApiOperation(value = "공동구매 등록")
    @PostMapping("/regist")
    public ResponseEntity dealRegist(@RequestPart GroupPurchaseRegistRequestDto groupPurchaseRegistRequestDto,
                                     @RequestPart(value = "file", required = false) MultipartFile groupImage){
        groupPurchaseService.registGroupPurchase(groupPurchaseRegistRequestDto, groupImage);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseDto(HttpStatus.OK.value(), "공동구매 등록", "공동구매 등록 완료 !!")
        );
    }

}
