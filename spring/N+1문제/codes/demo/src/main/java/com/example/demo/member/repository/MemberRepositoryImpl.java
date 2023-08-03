package com.example.demo.member.repository;

import com.example.demo.member.entity.Member;
import com.example.demo.member.entity.QMember;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import static com.example.demo.member.entity.QMember.*;

@Repository
@AllArgsConstructor
public class MemberRepositoryImpl implements MemberRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Member findByMemberId(Long id) {
        return queryFactory
                .selectFrom(member)
                .where(member.id .eq(id))
                .fetchOne();
    }
}
