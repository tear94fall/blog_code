package com.example.demo.member.dto;

import com.example.demo.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class MemberDto {

    private String username;
    private String email;

    public MemberDto(Member member) {
        this.username = member.getUsername();
        this.email = member.getEmail();
    }
}
