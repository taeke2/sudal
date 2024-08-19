package com.shop.sudal.domain.member.member.repository;

import com.shop.sudal.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long>, MemberRepositoryQdsl {
    Optional<Member> findByEmail(String email);

    Boolean existsMemberByEmail(String email);
}
