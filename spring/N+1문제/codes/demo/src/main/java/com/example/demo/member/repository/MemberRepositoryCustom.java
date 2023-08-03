
package com.example.demo.member.repository;

import com.example.demo.member.entity.Member;

public interface MemberRepositoryCustom {

    Member findByMemberId(Long id);
}