package com.ssafy.nuguri.controller.hobby;

import com.ssafy.nuguri.dto.response.ResponseDto;
import com.ssafy.nuguri.service.hobby.HobbyFavoriteService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/app/hobby/favorite")
public class HobbyFavoriteController {
    private final HobbyFavoriteService hobbyFavoriteService;

    @ApiOperation(value = "취미방 즐겨찾기 등록/해제")
    @PostMapping("regist/{hobbyId}")
    public ResponseEntity createOrModifyHobbyFavorite(@PathVariable Long hobbyId){
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseDto(HttpStatus.OK.value(), "중고거래 즐겨찾기 등록/해제", (hobbyFavoriteService.createOrModifyHobbyFavorite(hobbyId))?"즐겨찾기 등록":"즐겨찾기 해제")
        );
    }

    @ApiOperation(value = "취미방에 등록된 즐겨찾기 수")
    @GetMapping("/cnt/{hobbyId}")
    public ResponseEntity getFavoriteCnt(@PathVariable Long hobbyId){
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseDto(HttpStatus.OK.value(),"취미방 즐겨찾기 수",hobbyFavoriteService.getFavoriteCnt(hobbyId))
        );
    }



}
