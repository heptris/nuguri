package com.ssafy.nuguri.dto.category;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryListDto {

    private Long categoryId;
    private Long parentId;
    private String categoryName;
}
