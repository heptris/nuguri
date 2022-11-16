package com.ssafy.nuguri.repository.category;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.nuguri.domain.category.QCategory;
import com.ssafy.nuguri.dto.category.CategoryListDto;

import javax.persistence.EntityManager;
import java.util.List;

import static com.ssafy.nuguri.domain.category.QCategory.category;

public class CategoryRepositoryImpl implements CategoryRepositoryCustom{
    private final JPAQueryFactory queryFactory;

    public CategoryRepositoryImpl(EntityManager em){
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<CategoryListDto> findAllCategory() {
        List<CategoryListDto> categoryListDtoList = queryFactory
                .select(Projections.constructor(CategoryListDto.class,
                        category.id,
                        category.parent.id,
                        category.categoryName
                ))
                .from(category)
                .fetch();
        return categoryListDtoList;
    }
}
