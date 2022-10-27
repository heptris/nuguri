package com.ssafy.nuguri.dto.group;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GroupPurchaseListDto {

    private Long groupPurchaseId;
    private Long categoryId;
    private String title;
    private String description;
    private String productUrl;
    private LocalDateTime endDate;
    private int goalNumber;
    private int reservedNumber;
    private int price;
    private String groupPurchaseImage;
    private String dong;
}
