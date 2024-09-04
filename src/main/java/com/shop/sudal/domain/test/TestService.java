package com.shop.sudal.domain.test;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@Transactional
public class TestService {

    private final TestRepository testRepository;

    public void inputData(TestEntity entity) {
        testRepository.save(entity);
    }
}
