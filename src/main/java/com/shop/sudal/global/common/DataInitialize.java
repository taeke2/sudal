package com.shop.sudal.global.common;

import com.shop.sudal.domain.entity.RoleType;
import com.shop.sudal.domain.member.auth.model.LoginMemberRequest;
import com.shop.sudal.domain.member.auth.model.LoginMemberResponse;
import com.shop.sudal.domain.member.auth.service.AuthService;
import com.shop.sudal.domain.member.member.model.CreateMemberRequest;
import com.shop.sudal.domain.member.member.model.MemberDto;
import com.shop.sudal.domain.member.member.service.MemberService;
import com.shop.sudal.domain.member.role.model.CreateRoleRequest;
import com.shop.sudal.domain.member.role.service.RoleService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
@Transactional
public class DataInitialize {

    private final RoleService roleService;
    private final MemberService memberService;
    private final AuthService authService;

    @PostConstruct
    public void init() {
        createRoles();
        createMember();
        addMemberRole_ADMIN();
    }

    private void addMemberRole_ADMIN() {
        LoginMemberRequest loginMemberRequest = new LoginMemberRequest("test@example.com", "1234");
        LoginMemberResponse response = authService.login(loginMemberRequest);
        MemberDto memberDto = response.getMember();
        memberService.testAddMemberRole(memberDto.getId(), RoleType.ADMIN);
    }

    private void createMember() {
        CreateMemberRequest memberRequest = new CreateMemberRequest(
                "test",
                "test@example.com",
                "1234",
                LocalDate.now(),
                "010-1234-5678",
                1
        );

        memberService.signup(memberRequest);
    }

    private void createRoles() {
        List<CreateRoleRequest> createRoles = Arrays.asList(
                new CreateRoleRequest("ADMIN"),
                new CreateRoleRequest("MEMBER"),
                new CreateRoleRequest("GUEST"));

        for (CreateRoleRequest createRole : createRoles) {
            roleService.createRole(createRole);
        }
    }

}
