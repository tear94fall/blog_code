package com.example.demo.member.dto;

import com.example.demo.member.entity.Member;
import lombok.Data;

@Data
public class MemberDto {

    private Long id;
    private String email;
    private String password;
    private String username;

    public static MemberDto toMemberDto(Member member) {
        MemberDto memberDto = new MemberDto();

        memberDto.setId(member.getId());
        memberDto.setEmail(member.getEmail());
        memberDto.setUsername(member.getUsername());
        memberDto.setPassword(member.getPassword());

        return memberDto;
    }
}
