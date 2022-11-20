package com.ssafy.nuguri.dto.deal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DealDetailDto {

    // 비로그인시
    private Long dealId;
    private String title;
    private String description;
    private int price;
    private int hit;
    private boolean isDeal;
    private String dealImage;
    // baseAddress Join
    private String dong;
    private Long sellerId;
    private String sellerNickname;
}
