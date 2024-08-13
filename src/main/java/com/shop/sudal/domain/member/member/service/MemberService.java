package com.shop.sudal.domain.member.member.service;

import com.shop.sudal.domain.entity.Member;
import com.shop.sudal.domain.entity.Role;
import com.shop.sudal.domain.entity.RoleType;
import com.shop.sudal.domain.member.member.model.CreateMemberRequest;
import com.shop.sudal.domain.member.member.repository.MemberRepository;
import com.shop.sudal.domain.member.role.repository.RoleRepository;
import com.shop.sudal.global.common.exception.MemberException;
import com.shop.sudal.global.common.exception.RoleException;
import com.shop.sudal.global.common.response.ResponseCode;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    public Void join(CreateMemberRequest createMemberRequest) {
        if (memberRepository.existsMemberByEmail(createMemberRequest.getEmail())) {
            throw new MemberException(ResponseCode.MEMBER_ALREADY_EXIST);
        } else {
            Member member = createMemberRequest.toEntityMember();
            member.encodePassword(passwordEncoder.encode(createMemberRequest.getPassword()));

            Role role = roleRepository.findByRoleType(RoleType.MEMBER)
                    .orElseThrow(() -> new RoleException(ResponseCode.ROLE_NOT_FOUND));

            member.addRole(role);

            memberRepository.save(member);
        }
        return null;
    }

}
