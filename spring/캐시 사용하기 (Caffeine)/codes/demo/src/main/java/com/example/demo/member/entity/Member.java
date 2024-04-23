package com.example.demo.member.entity;

import com.example.demo.member.dto.MemberDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String email;

    @Builder
    public Member(String username, String email) {
        this.username = username;
        this.email = email;
    }

    public Member(MemberDto memberDto) {
        this.username = memberDto.getUsername();
        this.email = memberDto.getEmail();
    }
}
