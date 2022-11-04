package com.ssafy.nuguri.controller.baseaddress;

import com.ssafy.nuguri.dto.deal.DealListRequestDto;
import com.ssafy.nuguri.dto.response.ResponseDto;
import com.ssafy.nuguri.service.baseaddress.BaseaddressService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/app/base-address")
@RequiredArgsConstructor
public class BaseAddressController {

    private final BaseaddressService baseaddressService;

    @ApiOperation(value = "지역 검색")
    @PostMapping("/{keyword}/search")
    public ResponseEntity findBaseaddressByDongList(@PathVariable String keyword){
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseDto(HttpStatus.OK.value(), "지역 검색 목록",
                        baseaddressService.findBaseaddressByDong(keyword))
        );
    }
}
