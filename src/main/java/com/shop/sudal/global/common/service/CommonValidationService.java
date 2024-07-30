package com.shop.sudal.global.common.service;

import com.shop.sudal.domain.entity.User;

public interface CommonValidationService {

    User validateUserByEmail(String email, String message);

    User validateUserById(Long id, String message);
}
