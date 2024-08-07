package com.shop.sudal.global.common.service;

import com.shop.sudal.domain.entity.Member;

public interface CommonValidationService {

    Member validateMemberByEmail(String email);

    Member validateMemberById(Long id);
}
