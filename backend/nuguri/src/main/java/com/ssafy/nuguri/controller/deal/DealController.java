package com.ssafy.nuguri.controller.deal;

import com.ssafy.nuguri.dto.deal.DealListRequestDto;
import com.ssafy.nuguri.dto.response.ResponseDto;
import com.ssafy.nuguri.service.deal.DealService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/app/deal")
public class DealController {

    private final DealService dealService;

    @ApiOperation(value = "해당 지역, 취미에 대한 중고거래 목록 조회")
    @GetMapping("/{localId}/{categoryId}/list")
    public ResponseEntity findLocalCategoryDealList(@PathVariable Long localId, @PathVariable Long categoryId){
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseDto(HttpStatus.OK.value(), "중고거래 목록", dealService.findLocalCategoryDealList(localId, categoryId))
        );
    }

}
