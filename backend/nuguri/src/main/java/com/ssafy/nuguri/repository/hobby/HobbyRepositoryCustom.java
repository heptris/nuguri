package com.ssafy.nuguri.repository.hobby;

import com.ssafy.nuguri.domain.hobby.ApproveStatus;
import com.ssafy.nuguri.dto.hobby.HobbyDto;
import com.ssafy.nuguri.dto.hobby.HobbyStatusDto;

import java.util.List;

public interface HobbyRepositoryCustom {
    // 특정 지역의 모든 취미방 목록
    List<HobbyDto> findByRegion(Long RegionId);

    // 특정 지역에서 특정 카테고리에 포함되는 모든 취미방 목록
    List<HobbyDto> findByRegionAndCategory(Long RegionId, Long CategoryId);

    // 여러 지역에서 여러 카테고리 중 하나에 포함되는 모든 취미방 목록
    List<HobbyDto> findMultipleRegionAndCategory(List<Long> RegionIds, List<Long> CategoryIds);


    // hobbyId로 취미방 찾기(취미방 상세보기 클릭 시)
    HobbyDto hobbyDetail(Long hobbyId);

    List<HobbyStatusDto> findByMemberIdAndStatus(Long memberId, ApproveStatus approveStatus);
    List<HobbyStatusDto> findByMemberIdAndPromoter(Long memberId, boolean isPromoter);
    List<HobbyStatusDto> findByMemberIdAndFavorite(Long memberId, boolean isFavorite);

}
