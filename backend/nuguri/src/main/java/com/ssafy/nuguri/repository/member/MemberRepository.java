package com.ssafy.nuguri.repository.member;

import com.ssafy.nuguri.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
