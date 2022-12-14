package com.ssafy.nuguri.controller.deal;

import com.ssafy.nuguri.dto.deal.DealListRequestCondition;
import com.ssafy.nuguri.dto.deal.DealRegistRequestDto;
import com.ssafy.nuguri.dto.deal.DealUpdateDto;
import com.ssafy.nuguri.dto.response.ResponseDto;
import com.ssafy.nuguri.service.deal.DealService;
import com.ssafy.nuguri.util.SecurityUtil;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/app/deal")
public class DealController {

    private final DealService dealService;

    @ApiOperation(value = "해당 지역, 취미에 대한 중고거래 목록 조회 + localId, categoryId는 null 넣으면 조건에 맞는 중고거래 전체 조회")
    @GetMapping("/list")
    public ResponseEntity findLocalCategoryDealList(@RequestParam(required = false) Long localId,
                                                    @RequestParam(required = false) Long categoryId,
                                                    @RequestParam Integer size,
                                                    @RequestParam Integer page){
        Pageable pageable = Pageable.ofSize(size).withPage(page);
        DealListRequestCondition condition = new DealListRequestCondition(localId, categoryId);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseDto(HttpStatus.OK.value(), "중고거래 목록",
                        dealService.findLocalCategoryDealList(condition, pageable))
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
    @ApiOperation(value = "중고거래 등록")
    @PostMapping("/regist")
    public ResponseEntity dealRegist(@RequestPart DealRegistRequestDto dealRegistRequestDto,
                                     @RequestPart(value = "file", required = false) MultipartFile dealImage){
        Long memberId = SecurityUtil.getCurrentMemberId();
        dealService.dealRegist(memberId, dealRegistRequestDto, dealImage);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseDto(HttpStatus.OK.value(), "중고거래 등록", "중고거래 등록 완료 !!")
        );
    }

    @ApiOperation(value = "등록된 중고거래 수정")
    @PutMapping
    public ResponseEntity updateDealDetail(@RequestPart DealUpdateDto dealUpdateDto,
                                           @RequestPart(value = "file", required = false) MultipartFile dealImage){
        dealService.updateDealDetail(dealUpdateDto, dealImage);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseDto(HttpStatus.OK.value(), "중고거래 수정", "중고거래 수정 완료 !!")
        );
    }

    @ApiOperation(value = "중고거래 즐겨찾기 등록/해제")
    @PostMapping("/{dealId}/favorite")
    public ResponseEntity createOrModifyDealFavorite(@PathVariable Long dealId){
        Long memberId = SecurityUtil.getCurrentMemberId();
        dealService.createOrModifyDealFavorite(memberId, dealId);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseDto(HttpStatus.OK.value(), "중고거래 즐겨찾기 등록/해제", "중고거래 즐겨찾기 등록/해제 완료 !!")
        );
    }

    @ApiOperation(value = "중복 증가 방지된 중고거래 조회수 증가")
    @PostMapping("/{dealId}/hit")
    public ResponseEntity increaseHit(@PathVariable Long dealId, HttpServletRequest request, HttpServletResponse response){
        dealService.increaseHit(dealId, request, response);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseDto(HttpStatus.OK.value(), "중복 증가 방지된 중고거래 조회수 증가", "중고거래 조회수 증가 완료 !!")
        );
    }

}
