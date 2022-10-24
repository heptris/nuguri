package com.ssafy.nuguri.repository.deal;

import com.ssafy.nuguri.domain.deal.Deal;
import com.ssafy.nuguri.dto.deal.DealListDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DealRepository extends JpaRepository<Deal, Long>, DealRepositoryCustom {
    List<Deal> findByMemberId(Long memberId);
}
