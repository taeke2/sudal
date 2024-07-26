package com.shop.sudal.domain.user;

import com.shop.sudal.domain.address.repository.AddressRepository;
import com.shop.sudal.domain.user.respository.UserRepository;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class UserTest {

    @Autowired
    UserRepository userRepository;
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