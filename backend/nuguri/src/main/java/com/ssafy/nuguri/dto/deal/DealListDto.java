package com.ssafy.nuguri.dto.deal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DealListDto {

    private Long dealId;
    private Long categoryId;
    private Long localId;
    private String title;
    private String description;
    private int price;
    private int hit;
    private boolean isDeal;
    private String dealImage;
//    무슨 동인지도 필요할 거 같음
//    private String dong;
}
