package com.example.demo.member.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    private Member member;

    public void setMember(Member member) {
        this.member = member;
    }

    @Builder
    public Post(String name) {
        this.name = name;
    }
}
