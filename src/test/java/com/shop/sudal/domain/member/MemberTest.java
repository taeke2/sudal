package com.shop.sudal.domain.member;

import com.shop.sudal.domain.address.repository.AddressRepository;
import com.shop.sudal.domain.member.member.repository.MemberRepository;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class MemberTest {

    @Autowired
    MemberRepository memberRepository;
    @Autowired
    AddressRepository addressRepository;
    @Autowired
    EntityManager em;

    @Test
    @Transactional
    @Rollback(false)
    public void ___Test() {
        // given

        // when

        // then

    }
}