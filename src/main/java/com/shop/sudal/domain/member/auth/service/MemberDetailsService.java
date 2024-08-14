package com.shop.sudal.domain.member.auth.service;

import com.shop.sudal.domain.entity.Member;
import com.shop.sudal.domain.member.auth.model.AuthMemberDto;
import com.shop.sudal.domain.member.auth.model.MemberDetails;
import com.shop.sudal.domain.member.member.repository.MemberRepository;
import com.shop.sudal.global.common.response.ResponseCode;
import com.shop.sudal.global.exception.MemberException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class MemberDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String memberId) throws UsernameNotFoundException {
        Member member = memberRepository.findById(Long.valueOf(memberId))
                .orElseThrow(() -> new MemberException(ResponseCode.MEMBER_NOT_FOUND));

        AuthMemberDto authMemberDto = new AuthMemberDto(member);

        return new MemberDetails(authMemberDto);
    }
}
