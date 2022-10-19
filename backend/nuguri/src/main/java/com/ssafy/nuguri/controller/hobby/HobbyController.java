package com.ssafy.nuguri.controller.hobby;

import com.ssafy.nuguri.domain.hobby.Hobby;
import com.ssafy.nuguri.domain.member.Member;
import com.ssafy.nuguri.dto.response.ResponseDto;
import com.ssafy.nuguri.service.hobby.HobbyHistoryService;
import com.ssafy.nuguri.service.hobby.HobbyService;
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

    // 취미방 생성
    @PostMapping("/make/Room")
    public ResponseEntity regist(@RequestBody Hobby hobby, @RequestBody Member member){
        hobbyService.createHobby(hobby);
        hobbyHistoryService.createHobbyHistory(hobby,member,true);
        return new ResponseEntity<ResponseDto>(new ResponseDto<>(), HttpStatus.OK);
    }

    // 특정 지역의 모든 취미방
    @GetMapping("/searchBy/Region")
    public ResponseEntity findByRegion(String region){
        List<Hobby> result = hobbyService.searchAllHobbyByRegion(region);
        return new ResponseEntity<ResponseDto>(new ResponseDto<>(), HttpStatus.OK);
    }

    // 특정 카테고리의 모든 취미방
    @GetMapping("/searchBy/Category")
    public ResponseEntity findByCategory(String category){
        List<Hobby> result = hobbyService.searchAllHobbyByCategory(category);
        return new ResponseEntity<ResponseDto>(new ResponseDto<>(), HttpStatus.OK);
    }

    // 특정 지역의 특정 카테고리의 모든 취미방
    @GetMapping("/searchBy/RegionAndCategory")
    public ResponseEntity findByHobbyAndCategory(String region, String hobby){
        List<Hobby> result = hobbyService.searchAllHobbyByRegionAndCategory(region, hobby);
        return new ResponseEntity<ResponseDto>(new ResponseDto<>(), HttpStatus.OK);
    }



}
