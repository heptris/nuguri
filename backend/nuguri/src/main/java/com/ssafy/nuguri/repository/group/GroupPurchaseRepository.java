package com.ssafy.nuguri.repository.group;

import com.ssafy.nuguri.domain.group.GroupPurchase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupPurchaseRepository extends JpaRepository<GroupPurchase, Long>, GroupPurchaseRepositoryCustom {
}
