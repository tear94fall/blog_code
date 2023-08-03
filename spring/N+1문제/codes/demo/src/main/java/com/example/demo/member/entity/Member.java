package com.example.demo.member.entity;

import com.example.demo.member.dto.MemberDto;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String email;

    @Column
    private String password;

    @Column
    private String username;

    @OneToMany(mappedBy = "member", fetch = FetchType.EAGER)
    private List<Post> posts = new ArrayList<>();

    @Builder
    public Member(String email, String username, String password) {
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public static Member toMemberEntity(MemberDto memberDto) {
        Member member = new Member();

        member.email = memberDto.getEmail();
        member.username = memberDto.getUsername();
        member.password = memberDto.getPassword();

        return member;
    }
}