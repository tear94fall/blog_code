package com.example.demo.member.service;

import com.example.demo.member.entity.Member;
import com.example.demo.member.entity.Post;
import com.example.demo.member.repository.MemberRepository;
import com.example.demo.member.repository.PostRepository;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
class MemberServiceTest {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private EntityManager em;

    @BeforeEach
    @DisplayName("초기화")
    public void init() {
        for(int i=0;i<10;i++) {
            Member member = Member.builder()
                    .email(i+"@test.com")
                    .username("user"+i)
                    .password("test"+i)
                    .build();

            memberRepository.save(member);

            List<Post> posts = new ArrayList<>();

            for(int j=0;j<10;j++) {
                Post post = Post.builder()
                        .name("post"+i)
                        .build();

                posts.add(post);
            }

            postRepository.saveAll(posts);

            member.setPosts(posts);
            memberRepository.save(member);
        }
    }

    @Test
    @DisplayName("멤버 생성")
    @Transactional
    public void TestCreateMember() {

        // given

        // when
        em.clear();
        List<Member> members = memberRepository.findAll();

        // then
        for (Member member : members) {
            List<Post> posts = member.getPosts();

            for (Post post : posts) {
                System.out.println(post.getName());
            }
        }
    }

    @Test
    @DisplayName("멤버 조회")
    @Transactional
    public void TestSearchMember() {

        // given
        Member.builder()
                .email("test@test.com")
                .username("test-user")
                .password("test1234")
                .build();

        // when

        // then
    }
}