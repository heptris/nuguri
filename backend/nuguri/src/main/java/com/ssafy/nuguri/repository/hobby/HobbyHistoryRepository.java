package com.ssafy.nuguri.repository.hobby;


import com.ssafy.nuguri.domain.hobby.HobbyHistory;
import com.ssafy.nuguri.dto.hobby.HobbyHistoryDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HobbyHistoryRepository extends JpaRepository<HobbyHistory,Long>, HobbyHistoryRepositoryCustom{
}
