package com.shop.sudal.domain.member.member.repository;

import com.shop.sudal.domain.member.member.model.MemberDto;

import java.util.List;

public interface MemberRepositoryQdsl {
    List<MemberDto> getMemberList();
}
