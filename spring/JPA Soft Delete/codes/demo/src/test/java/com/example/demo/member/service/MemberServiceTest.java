package com.example.demo.member.service;

import com.example.demo.member.entity.Member;
import com.example.demo.member.repository.MemberRepository;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static com.example.demo.member.entity.Member.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional(readOnly = true)
class MemberServiceTest {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    EntityManager em;

    @Test
    @Transactional
    @DisplayName("조회")
    public void searchMemberTest() {
        //given
        Member member = memberRepository.save(createMember("asdf", "asdf@test.com", 25));

        //when
        em.flush();
        em.clear();

        Member findMember = memberRepository.findById(member.getId())
                .orElseThrow(IllegalArgumentException::new);

        //then
        assertEquals(member.getUsername(), findMember.getUsername());
    }

    @Test
    @Transactional
    @DisplayName("soft delete 테스트")
    public void softDeleteTest() {
        //given
        Member member = memberRepository.save(createMember("asdf", "asdf@test.com", 25));

        //when
        memberRepository.delete(member);
        em.flush();
        em.clear();

        //then
        assertThrows(IllegalArgumentException.class, () -> memberRepository.findById(member.getId())
                .orElseThrow(IllegalArgumentException::new));
    }
}