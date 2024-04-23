package com.example.demo.member.service;

import com.example.demo.member.entity.Member;
import com.example.demo.member.repository.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;

import java.util.Optional;
import java.util.stream.IntStream;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@SpringBootTest
class MemberServiceTest {

    @SpyBean
    private MemberService memberService;

    @MockBean
    private MemberRepository memberRepository;

    @Test
    @DisplayName("멤버 조회 캐시 테스트")
    void searchCacheMemberTest() {
        // given
        Member mockMember = Member.builder()
                .username("ab1234")
                .email("ab1234@gmail.como")
                .build();

        when(memberRepository.findByEmail(anyString())).thenReturn(Optional.of(mockMember));

        // when
        IntStream.range(0, 10).forEach((i) -> memberService.searchMemberByEmail("abc1234"));

        // then
        verify(memberRepository, times((1))).findByEmail("abc1234");
    }
}