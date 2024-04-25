package com.example.demo.member.service;

import com.example.demo.member.dto.MemberDto;
import com.example.demo.member.entity.Member;
import com.example.demo.member.repository.MemberRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    @CacheEvict(cacheNames = "member", key = "#email", cacheManager = "contentCacheManager")
    public MemberDto createMember(MemberDto memberDto) {
        Member saveMember = memberRepository.save(new Member(memberDto));
        return new MemberDto(saveMember);
    }

    @Cacheable(cacheNames = "member", key = "#email", cacheManager = "contentCacheManager")
    public MemberDto searchMemberByEmail(String email) {
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(EntityNotFoundException::new);

        return new MemberDto(member);
    }

    @Transactional
    @CacheEvict(cacheNames = "member", key = "#email", cacheManager = "contentCacheManager")
    public void delete(String email) {
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(EntityNotFoundException::new);

        memberRepository.delete(member);
    }

    @Transactional
    @CachePut(cacheNames = "member", key = "#email", cacheManager = "contentCacheManager")
    public void updateMember(String email, String username) {
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(EntityNotFoundException::new);

        member.updateUsername(username);
    }
}
