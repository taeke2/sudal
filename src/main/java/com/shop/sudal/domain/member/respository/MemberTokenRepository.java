package com.shop.sudal.domain.member.respository;

import com.shop.sudal.domain.entity.Member;
import com.shop.sudal.domain.entity.MemberToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberTokenRepository extends JpaRepository<MemberToken, Long> {
    Optional<MemberToken> findByMember(Member member);
}
