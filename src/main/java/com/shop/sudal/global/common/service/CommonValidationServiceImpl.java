package com.shop.sudal.global.common.service;

import com.shop.sudal.domain.entity.Member;
import com.shop.sudal.domain.member.respository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class CommonValidationServiceImpl implements CommonValidationService {

    private final MemberRepository memberRepository;

    @Override
    public Member validateMemberByEmail(String email, String message) {
        return memberRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException(message));
    }

    @Override
    public Member validateMemberById(Long id, String message) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(message));
    }
}
