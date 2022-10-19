package com.ssafy.nuguri.repository.hobby;


import com.ssafy.nuguri.domain.hobby.HobbyHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HobbyHistoryRepository extends JpaRepository<HobbyHistory,Long> {

    @Query(value = "select member_id from hobby_history where hobbyid = :hobby_id",nativeQuery = true)
    List<Long> findMemberByHobbyId(@Param("hobby_id") Long hobbyId);
}
