package com.shop.sudal.domain.member.member.service;

import com.shop.sudal.domain.entity.Member;
import com.shop.sudal.domain.entity.Role;
import com.shop.sudal.domain.entity.RoleType;
import com.shop.sudal.domain.member.member.model.AddMemberRoleRequest;
import com.shop.sudal.domain.member.member.model.CreateMemberRequest;
import com.shop.sudal.domain.member.member.repository.MemberRepository;
import com.shop.sudal.domain.member.role.repository.RoleRepository;
import com.shop.sudal.global.common.ValidationService;
import com.shop.sudal.global.exception.MemberException;
import com.shop.sudal.global.exception.RoleException;
import com.shop.sudal.global.response.ResponseCode;
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
    private final ValidationService validationService;

    public Void join(CreateMemberRequest createMemberRequest) {
        if (memberRepository.existsMemberByEmail(createMemberRequest.getEmail())) {
            throw new MemberException(ResponseCode.MEMBER_ALREADY_EXIST);
        }

        Member member = createMemberRequest.toEntityMember();
        member.encodePassword(passwordEncoder.encode(createMemberRequest.getPassword()));

        Role role = roleRepository.findByRoleType(RoleType.MEMBER)
                .orElseThrow(() -> new RoleException(ResponseCode.ROLE_NOT_FOUND));

        member.addRole(role);

        memberRepository.save(member);

        return null;
    }

    public Void addMemberRole(AddMemberRoleRequest addMemberRoleRequest) {
        Long memberId = validationService.validateMemberIdByAuth();
        Member member = validationService.validateMemberById(memberId);
        RoleType roleType = RoleType.valueOf(addMemberRoleRequest.getRole());
        if (!member.getRoleTypes().contains(roleType)) {
            Role role = roleRepository.findByRoleType(roleType)
                    .orElseThrow(() -> new RoleException(ResponseCode.ROLE_NOT_FOUND));
            member.addRole(role);
        } else {
            throw new MemberException(ResponseCode.MEMBER_ROLE_ALREADY_EXIST);
        }
        return null;
    }

    // TODO: DB 적용 후 삭제
    public void testAddMemberRole(Long memberId, RoleType roleType) {
        Member member = validationService.validateMemberById(memberId);
        if (!member.getRoleTypes().contains(roleType)) {
            Role role = roleRepository.findByRoleType(roleType)
                    .orElseThrow(() -> new RoleException(ResponseCode.ROLE_NOT_FOUND));
            member.addRole(role);
        } else {
            throw new MemberException(ResponseCode.MEMBER_ROLE_ALREADY_EXIST);
        }
    }

}
