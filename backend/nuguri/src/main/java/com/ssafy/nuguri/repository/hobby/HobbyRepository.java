package com.ssafy.nuguri.repository.hobby;

import com.ssafy.nuguri.domain.hobby.Hobby;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HobbyRepository extends JpaRepository<Hobby,Long> {
    @Query(value = "select * from hobby where region = :region",nativeQuery = true)
    public List<Hobby> findByRegion(@Param("region") String region);

    @Query(value = "select * from hobby where region = :category",nativeQuery = true)
    List<Hobby> findByCategory(@Param("category") String category);

    @Query(value ="select * from hobby where region = :region and category = :category",nativeQuery = true)
    List<Hobby> findByRegionAndCategory(@Param("region") String region, @Param("category") String category);


}
