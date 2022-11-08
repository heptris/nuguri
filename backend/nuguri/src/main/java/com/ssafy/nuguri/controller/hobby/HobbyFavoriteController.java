package com.ssafy.nuguri.controller.hobby;

import com.ssafy.nuguri.dto.response.ResponseDto;
import com.ssafy.nuguri.service.hobby.HobbyFavoriteService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


}
