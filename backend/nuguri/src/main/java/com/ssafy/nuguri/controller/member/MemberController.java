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

    @GetMapping("/deal/on-sale")
    public ResponseEntity profileDealOnSale(){
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseDto<>(HttpStatus.OK.value(), "회원 중고 거래(판매 중) 조회", memberService.profileDealOnSale())
        );
    }

    @GetMapping("/deal/sold-out")
    public ResponseEntity profileDealSoldOut(){
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseDto<>(HttpStatus.OK.value(), "회원 중고 거래(판매 완료) 조회", memberService.profileDealSoldOut())
        );
    }
    @GetMapping("/deal/purchase")
    public ResponseEntity profileDealPurchase(){
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseDto<>(HttpStatus.OK.value(), "회원 중고 거래(구매 완료) 조회", memberService.profileDealPurchase())
        );
    }
    @GetMapping("/deal/favorite")
    public ResponseEntity profileDealFavorite(){
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseDto<>(HttpStatus.OK.value(), "회원 중고 거래(찜) 조회", memberService.profileDealFavorite())
        );
    }
}
