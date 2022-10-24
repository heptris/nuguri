package com.ssafy.nuguri.dto.deal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DealHistoryUpdateDto {

    private Long buyerId;
    private Long dealId;
    private LocalDateTime promiseTime;
    private String promiseLocation;

}
