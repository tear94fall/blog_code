package com.example.demo.member.service;

import com.example.demo.member.entity.Member;
import com.example.demo.member.entity.Team;
import com.example.demo.member.repository.MemberRepository;
import com.example.demo.member.repository.TeamRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
class TeamServiceTest {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Test
    @Rollback(value = false)
    @DisplayName("N+1 문제")
    void npo_test() {
        List<Member> members = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Member member = Member.builder().name("name-"+i).build();
            members.add(member);
        }

        memberRepository.saveAll(members);

        List<Team> teams = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Team team = Team.builder().name("team-"+i).build();
            team.setMembers(members);
            teams.add(team);
        }

        teamRepository.saveAll(teams);

        List<Team> teamList = teamRepository.findAll();
    }
}