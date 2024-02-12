package com.example.demo.member.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@Getter
@Entity
@SQLDelete(sql = "UPDATE member SET deleted = true where member_id = ?")
@SQLRestriction("deleted = false")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private String username;
    private String email;
    private int age;

    private boolean deleted = Boolean.FALSE;

    @Builder
    public Member(String username, String email, int age) {
        this.username = username;
        this.email = email;
        this.age = age;
    }

    public static Member createMember(String username, String email, int age) {
        return Member.builder()
                .username(username)
                .email(email)
                .age(age)
                .build();
    }
}
