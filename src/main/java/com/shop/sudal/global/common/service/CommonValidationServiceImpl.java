package com.shop.sudal.global.common.service;

import com.shop.sudal.domain.entity.Member;
import com.shop.sudal.domain.member.respository.MemberRepository;
import com.shop.sudal.global.common.ResponseCode;
import com.shop.sudal.global.common.exception.MemberException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class CommonValidationServiceImpl implements CommonValidationService {

    private final MemberRepository memberRepository;

    @Override
    public Member validateMemberByEmail(String email) {
        return memberRepository.findByEmail(email)
                .orElseThrow(() -> new MemberException(ResponseCode.MEMBER_NOT_FOUND));
    }

    @Override
    public Member validateMemberById(Long id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new MemberException(ResponseCode.MEMBER_NOT_FOUND));
    }
}
