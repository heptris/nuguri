package com.ssafy.nuguri.repository.category;

import com.ssafy.nuguri.dto.category.CategoryListDto;

import java.util.List;

public interface CategoryRepositoryCustom {
    List<CategoryListDto> findAllCategory();
}
