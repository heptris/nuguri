package com.ssafy.nuguri.repository.group;

import com.ssafy.nuguri.dto.group.GroupPurchaseListDto;

import java.util.List;

public interface GroupPurchaseRepositoryCustom {
    List<GroupPurchaseListDto> findGroupByLocalAndCategory(Long localId, Long categoryId);
}
