package com.ssafy.nuguri.dto.group;

import com.ssafy.nuguri.domain.group.GroupPurchase;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GroupPurchaseRegistRequestDto {
    private Long categoryId;
    private String title;
    private String description;
    private String productUrl;
    private LocalDateTime endDate;
    private int goalNumber;
    private int price;

    public GroupPurchase toGroupPurchase(){
        return GroupPurchase.builder()
                .title(title)
                .description(description)
                .productUrl(productUrl)
                .endDate(endDate)
                .goalNumber(goalNumber)
                .reservedNumber(0)
                .price(price)
                .build();
    }
}
