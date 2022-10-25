package com.ssafy.nuguri.repository.deal;

import com.ssafy.nuguri.domain.deal.DealFavorite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DealFavoriteRepository extends JpaRepository<DealFavorite, Long> {
    DealFavorite findByMemberIdAndDealId(Long memberId, Long dealId);
    List<DealFavorite> findByMemberIdAndIsFavorite(Long memberId, boolean isFavorite);
}
