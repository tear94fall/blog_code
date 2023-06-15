package com.example.demo.member.repository;

import com.example.demo.member.entity.Member;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepositoryCustom {

    List<Member> findAllMember();
}
