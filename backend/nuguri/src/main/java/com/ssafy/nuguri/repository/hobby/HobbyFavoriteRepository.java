package com.ssafy.nuguri.repository.hobby;


import com.ssafy.nuguri.domain.hobby.HobbyFavorite;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HobbyFavoriteRepository extends JpaRepository<HobbyFavorite,Long>,HobbyFavoriteRepositoryCustom {

}

