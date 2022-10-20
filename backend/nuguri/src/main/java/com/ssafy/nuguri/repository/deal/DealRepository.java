package com.ssafy.nuguri.repository.deal;

import com.ssafy.nuguri.domain.deal.Deal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DealRepository extends JpaRepository<Deal, Long>, DealRepositoryCustom {
}
