package com.ssafy.nuguri.dto.deal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DealLoginDetailDto {

    // 로그인시
    private Long dealId;
    private String title;
    private String description;
    private int price;
    private int hit;
    private boolean isDeal;
    private String dealImage;
    // baseAddress Join
    private String dong;
    // 즐겨찾기
    private boolean isFavorite;
    private Long sellerId;

}
