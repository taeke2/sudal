package com.shop.sudal.domain.member.member.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.shop.sudal.domain.member.member.model.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static com.shop.sudal.domain.entity.QMember.member;

@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class MemberRepositoryImpl implements MemberRepositoryQdsl{

    private final JPAQueryFactory queryFactory;

    @Override
    public List<MemberDto> getMemberList() {
        return queryFactory.select(Projections.constructor(MemberDto.class, member))
                .from(member)
                .fetch();
    }
}