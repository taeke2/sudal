package com.shop.sudal.domain.member.member.service;

import com.shop.sudal.domain.entity.Member;
import com.shop.sudal.domain.entity.Role;
import com.shop.sudal.domain.entity.RoleType;
import com.shop.sudal.domain.member.member.model.CreateMemberRequest;
import com.shop.sudal.domain.member.member.model.MemberDto;
import com.shop.sudal.domain.member.member.model.UpdateMemberRoleRequest;
import com.shop.sudal.domain.member.member.repository.MemberRepository;
import com.shop.sudal.global.common.ValidationService;
import com.shop.sudal.global.exception.MemberException;
import com.shop.sudal.global.exception.RoleException;
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
        member.initMemberRoles();
        member.addRole(role);

        memberRepository.save(member);
    }

    // TODO: 데이터 베이스 재생성 안할 시 삭제_240919
    public void initSignup(CreateMemberRequest createMemberRequest) {

        Member member = createMemberRequest.toEntityMember();
        member.encodePassword(passwordEncoder.encode(createMemberRequest.getPassword()));

        Role role = validationService.validateRoleByRoleType(RoleType.MEMBER);
        Role role2 = validationService.validateRoleByRoleType(RoleType.ADMIN);
        member.initMemberRoles();
        member.addRole(role);
        member.addRole(role2);

        memberRepository.save(member);
    }

    public void updateMemberRole(UpdateMemberRoleRequest updateMemberRoleRequest) {
        Long memberId = validationService.validateMemberIdByAuth();
        Member member = validationService.validateMemberById(memberId);
        RoleType roleType = updateMemberRoleRequest.getRoleType();

        if (roleType == RoleType.ADMIN) {
            assignAdminRole(member);
        } else if (roleType == RoleType.MEMBER) {
            assignMemberRole(member);
        }
    }

    private void assignAdminRole(Member member) {
        if (member.hasRole(RoleType.ADMIN)) {
            throw new RoleException(ResponseCode.ADMIN_ROLE_ALREADY_EXIST);
        }
        Role adminRole = validationService.validateRoleByRoleType(RoleType.ADMIN);
        member.addRole(adminRole);
    }

    private void assignMemberRole(Member member) {
        if (member.hasRole(RoleType.MEMBER) && !member.hasRole(RoleType.ADMIN)) {
            throw new RoleException(ResponseCode.MEMBER_ROLE_ALREADY_EXIST);
        }
        member.deleteRole(validationService.validateRoleByRoleType(RoleType.ADMIN));

        if (member.getRoles().isEmpty()) {
            Role memberRole = validationService.validateRoleByRoleType(RoleType.MEMBER);
            member.addRole(memberRole);
        }
    }

    @Transactional(readOnly = true)
    public List<MemberDto> getMemberList() {
        return memberRepository.getMemberList();
    }

    public void deleteMember() {
        Long memberId = validationService.validateMemberIdByAuth();
        Member member = validationService.validateMemberById(memberId);
        member.deleteMember();
    }

}
