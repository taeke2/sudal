package com.shop.sudal.domain.member.member.service;

import com.shop.sudal.domain.entity.Member;
import com.shop.sudal.domain.entity.Role;
import com.shop.sudal.domain.entity.RoleType;
import com.shop.sudal.domain.member.member.model.AddMemberRoleRequest;
import com.shop.sudal.domain.member.member.model.CreateMemberRequest;
import com.shop.sudal.domain.member.member.model.MemberDto;
import com.shop.sudal.domain.member.member.repository.MemberRepository;
import com.shop.sudal.global.common.ValidationService;
import com.shop.sudal.global.exception.MemberException;
import com.shop.sudal.global.response.ResponseCode;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final ValidationService validationService;

    public void signup(CreateMemberRequest createMemberRequest) {
        if (memberRepository.existsMemberByEmail(createMemberRequest.getEmail()))
            throw new MemberException(ResponseCode.MEMBER_ALREADY_EXIST);

        Member member = createMemberRequest.toEntityMember();
        member.encodePassword(passwordEncoder.encode(createMemberRequest.getPassword()));

        Role role = validationService.validateRoleByRoleType(RoleType.MEMBER);

        member.addRole(role);

        memberRepository.save(member);
    }

    public void addMemberRole(AddMemberRoleRequest addMemberRoleRequest) {
        Long memberId = validationService.validateMemberIdByAuth();
        Member member = validationService.validateMemberById(memberId);
        RoleType roleType = addMemberRoleRequest.getRole();
        if (member.getRoleTypes().contains(roleType))
            throw new MemberException(ResponseCode.MEMBER_ROLE_ALREADY_EXIST);
        Role role = validationService.validateRoleByRoleType(roleType);
        member.addRole(role);
    }

    public List<MemberDto> getMemberList() {
        return memberRepository.getMemberList();
    }

    public void deleteMember() {
        Long memberId = validationService.validateMemberIdByAuth();
        Member member = validationService.validateMemberById(memberId);
        member.deleteMember();
    }

}
