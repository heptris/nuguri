package com.ssafy.nuguri.controller.deal;

import com.ssafy.nuguri.dto.deal.DealHistoryUpdateDto;
import com.ssafy.nuguri.dto.response.ResponseDto;
import com.ssafy.nuguri.service.deal.DealHistoryService;
import com.ssafy.nuguri.util.SecurityUtil;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/app/deal-history")
public class DealHistoryController {

    private final DealHistoryService dealHistoryService;

    @ApiOperation(value = "구매자가 채팅하기 눌렀을 때 로그 쌓이기 위한 API")
    @PostMapping("/{dealId}/create")
    public ResponseEntity pushChatButton(@PathVariable Long dealId){
        Long memberId = SecurityUtil.getCurrentMemberId();
        dealHistoryService.createDealHistory(memberId, dealId);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseDto(HttpStatus.OK.value(), "중고거래 History 생성", "중고거래 History 생성 완료 !!")
        );
    }

    @ApiOperation(value = "채팅 중 문의자를 구매예정자로 바꾸는 버튼을 누르고 약속 시간, 장소를 정했을 때 API")
    @PutMapping("/update")
    public ResponseEntity updateToReserver(@RequestBody DealHistoryUpdateDto dealHistoryUpdateDto){
        dealHistoryService.updateToReserver(dealHistoryUpdateDto);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseDto(HttpStatus.OK.value(), "약속시간, 장소 저장", "약속시간, 장소 저장 완료 !!")
        );
    }
}
