package com.ssafy.nuguri.service.category;

import com.ssafy.nuguri.dto.category.CategoryListDto;
import com.ssafy.nuguri.repository.category.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<CategoryListDto> findAllCategory(){
        return categoryRepository.findAllCategory();
    }

}
