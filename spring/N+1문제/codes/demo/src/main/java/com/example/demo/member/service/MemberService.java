package com.example.demo.member.service;

import com.example.demo.member.dto.MemberDto;
import com.example.demo.member.entity.Member;
import com.example.demo.member.repository.MemberRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberDto findMemberById(Long id) {
        Member member = memberRepository.findByMemberId(id);
        return MemberDto.toMemberDto(member);
    }

    public MemberDto createMember(MemberDto memberDto) {
        Member member = Member.toMemberEntity(memberDto);
        Member saveMember = memberRepository.save(member);

        return MemberDto.toMemberDto(saveMember);
    }
}
