package com.ssafy.nuguri.dto.deal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DealListRequestDto {

    private Long localId;

    private Long categoryId;

}
