package com.ssafy.nuguri.service.member;

import com.ssafy.nuguri.domain.member.Member;
import com.ssafy.nuguri.repository.member.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    MemberRepository memberRepository;
    
    @Test
    public void 테스트(){
        Optional<Member> member = memberRepository.findById(1L);
        if(member.get().getDealList().isEmpty()) System.out.println("나 비었어요~~~~~!!!");
//        System.out.println("member.get().getDealList().get(0).getTitle() = " + member.get().getDealList());
    }
}