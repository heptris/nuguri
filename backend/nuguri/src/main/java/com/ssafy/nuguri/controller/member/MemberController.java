package com.ssafy.nuguri.controller.member;

import com.ssafy.nuguri.dto.member.MemberModifyDto;
import com.ssafy.nuguri.dto.member.MemberProfileRequestDto;
import com.ssafy.nuguri.dto.response.ResponseDto;
import com.ssafy.nuguri.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/app/member")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @PostMapping
    public ResponseEntity profile(@RequestBody MemberProfileRequestDto requestDto){
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseDto<>(HttpStatus.OK.value(), "회원 프로필 조회", memberService.profile(requestDto))
        );
    }

    @PostMapping("/modify")
    public ResponseEntity profileModify(@RequestBody MemberModifyDto requestDto){
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseDto<>(HttpStatus.OK.value(), "회원 프로필 수정", memberService.profileModify(requestDto))
        );
    }

    @PostMapping("/deal/on-sale")
    public ResponseEntity profileDealOnSale(@RequestBody MemberProfileRequestDto requestDto){
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseDto<>(HttpStatus.OK.value(), "회원 중고 거래(판매 중) 조회", memberService.profileDealOnSale(requestDto))
        );
    }

    @PostMapping("/deal/sold-out")
    public ResponseEntity profileDealSoldOut(@RequestBody MemberProfileRequestDto requestDto){
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseDto<>(HttpStatus.OK.value(), "회원 중고 거래(판매 완료) 조회", memberService.profileDealSoldOut(requestDto))
        );
    }
    @PostMapping("/deal/purchase")
    public ResponseEntity profileDealPurchase(@RequestBody MemberProfileRequestDto requestDto){
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseDto<>(HttpStatus.OK.value(), "회원 중고 거래(구매 완료) 조회", memberService.profileDealPurchase(requestDto))
        );
    }
    @PostMapping("/deal/favorite")
    public ResponseEntity profileDealFavorite(@RequestBody MemberProfileRequestDto requestDto){
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseDto<>(HttpStatus.OK.value(), "회원 중고 거래(찜) 조회", memberService.profileDealFavorite(requestDto))
        );
    }
//    APPROVE, READY, REJECT
    @PostMapping("/hobby/ready")
    public ResponseEntity profileHobbyReady(@RequestBody MemberProfileRequestDto requestDto){
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseDto<>(HttpStatus.OK.value(), "회원 취미 모임방(대기 중) 조회", memberService.profileHobbyReady(requestDto))
        );
    }

    @PostMapping("/hobby/participation")
    public ResponseEntity profileHobbyParticipation(@RequestBody MemberProfileRequestDto requestDto){
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseDto<>(HttpStatus.OK.value(), "회원 취미 모임방(참여 중) 조회", memberService.profileHobbyParticipation(requestDto))
        );
    }
    @PostMapping("/hobby/manage")
    public ResponseEntity profileHobbyManage(@RequestBody MemberProfileRequestDto requestDto){
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseDto<>(HttpStatus.OK.value(), "회원 취미 모임방(운영 중) 조회", memberService.profileHobbyManage(requestDto))
        );
    }
    @PostMapping("/hobby/favorite")
    public ResponseEntity profileHobbyFavorite(@RequestBody MemberProfileRequestDto requestDto){
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseDto<>(HttpStatus.OK.value(), "회원 취미 모임방(찜) 조회", memberService.profileHobbyFavorite(requestDto))
        );
    }
}
