package com.shop.sudal.global.common;

import com.shop.sudal.domain.entity.Member;
import com.shop.sudal.domain.member.auth.model.MemberDetails;
import com.shop.sudal.domain.member.member.repository.MemberRepository;
import com.shop.sudal.global.response.ResponseCode;
import com.shop.sudal.global.exception.MemberException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class ValidationService {

    private final MemberRepository memberRepository;

    public Member validateMemberByEmail(String email) {
        return memberRepository.findByEmail(email)
                .orElseThrow(() -> new MemberException(ResponseCode.MEMBER_NOT_FOUND));
    }

    public Member validateMemberById(Long id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new MemberException(ResponseCode.MEMBER_NOT_FOUND));
    }

    public Long validateMemberIdByAuth() {
        return Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication())
                .filter(Authentication::isAuthenticated)
                .map(Authentication::getPrincipal)
                .filter(principal -> principal instanceof MemberDetails)
                .map(principle -> ((MemberDetails) principle).getAuthMemberDto().getId())
                .orElseThrow(() -> new MemberException(ResponseCode.AUTHENTICATION_INVALID));
    }
}
