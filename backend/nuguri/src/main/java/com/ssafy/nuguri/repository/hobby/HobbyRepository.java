package com.ssafy.nuguri.repository.hobby;

import com.ssafy.nuguri.domain.hobby.ApproveStatus;
import com.ssafy.nuguri.domain.hobby.Hobby;
import com.ssafy.nuguri.dto.hobby.HobbyDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HobbyRepository extends JpaRepository<Hobby,Long>, HobbyRepositoryCustom {
}
