package com.example.demo.member.service;

import com.example.demo.member.entity.Team;
import com.example.demo.member.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeamService {

    private final TeamRepository teamRepository;

    public List<Team> searchAllTeam() {
        return teamRepository.findAll();
    }
}
