package com.ssafy.nuguri.repository.hobby;


import com.ssafy.nuguri.domain.hobby.HobbyFavorite;

public interface HobbyFavoriteRepositoryCustom {
    HobbyFavorite findByMemberIdAndHobbyId(Long memberId, Long hobbyId);

    Integer getFavoriteNumberByHobbyId(Long hobbyId);

    boolean favoriteCheck(Long memberId, Long hobbyId);
}
