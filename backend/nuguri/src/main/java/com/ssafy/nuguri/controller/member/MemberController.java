package com.ssafy.nuguri.controller.member;

import com.ssafy.nuguri.dto.response.ResponseDto;
import com.ssafy.nuguri.repository.member.MemberRepository;
import com.ssafy.nuguri.service.member.MemberService;
import com.ssafy.nuguri.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/app/member")
@RequiredArgsConstructor
public class MemberController {
    private final MemberRepository memberRepository;
    private final MemberService memberService;

    @GetMapping
    public ResponseEntity profile(){
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseDto<>(HttpStatus.OK.value(), "회원 프로필 조회", memberService.profile())
        );
    }
}
