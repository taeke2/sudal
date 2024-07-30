package com.shop.sudal.global.common.service;

import com.shop.sudal.domain.entity.User;
import com.shop.sudal.domain.user.respository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class CommonValidationServiceImpl implements CommonValidationService {

    private final UserRepository userRepository;

    @Override
    public User validateUserByEmail(String email, String message) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException(message));
    }

    @Override
    public User validateUserById(Long id, String message) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(message));
    }
}
