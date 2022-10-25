package com.ssafy.nuguri.dto.deal;

import com.ssafy.nuguri.domain.deal.Deal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DealRegistRequestDto {

    private Long categoryId;
    private String title;
    private String description;
    private int price;

    public Deal toDeal(){
        return Deal.builder()
                .title(title)
                .description(description)
                .price(price)
                .hit(0)
                .isDeal(false)
                .build();
    }

}
