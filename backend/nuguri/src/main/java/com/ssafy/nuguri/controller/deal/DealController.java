package com.ssafy.nuguri.controller.deal;

import com.ssafy.nuguri.dto.deal.DealListRequestDto;
import com.ssafy.nuguri.dto.deal.DealRegistRequestDto;
import com.ssafy.nuguri.dto.response.ResponseDto;
import com.ssafy.nuguri.service.deal.DealService;
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
@RequestMapping("/ap/deal")
public class DealController {

    private final DealService dealService;

    @ApiOperation(value = "해당 지역, 취미에 대한 중고거래 목록 조회")
    @GetMapping("/{localId}/{categoryId}/list")
    public ResponseEntity findLocalCategoryDealList(@PathVariable Long localId, @PathVariable Long categoryId){
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseDto(HttpStatus.OK.value(), "중고거래 목록", dealService.findLocalCategoryDealList(localId, categoryId))
        );
    }

    @ApiOperation(value = "비로그인시 중고거래 상세페이지 조회")
    @GetMapping("/{dealId}/detail")
    public ResponseEntity findDealDetail(@PathVariable Long dealId){
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseDto(HttpStatus.OK.value(), "비로그인시 중고거래 상세페이지", dealService.findDealDetail(dealId))
        );
    }

    @ApiOperation(value = "로그인시 중고거래 상세페이지 조회")
    @GetMapping("/{dealId}/login/detail")
    public ResponseEntity findLoginDealDetail(@PathVariable Long dealId){
        Long memberId = SecurityUtil.getCurrentMemberId();
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseDto(HttpStatus.OK.value(), "비로그인시 중고거래 상세페이지", dealService.findLoginDealDetail(memberId, dealId))
        );
    }

    @PostMapping("/regist")
    public ResponseEntity dealRegist(@RequestPart DealRegistRequestDto dealRegistRequestDto,
                                     @RequestPart(value = "file", required = false) MultipartFile dealImage){
        Long memberId = SecurityUtil.getCurrentMemberId();
        dealService.dealRegist(memberId, dealRegistRequestDto, dealImage);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseDto(HttpStatus.OK.value(), "중고거래 등록", "중고거래 등록 완료 !!")
        );

    }

}
