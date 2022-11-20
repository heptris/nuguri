package com.ssafy.nuguri.repository.baseaddress;

import com.ssafy.nuguri.domain.baseaddress.BaseAddress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BaseaddressRepository extends JpaRepository<BaseAddress, Long>, BaseaddressRepositoryCustom {
    Optional<BaseAddress> findBySidoAndGugunAndDong(String sido, String gugun, String dong);
}
