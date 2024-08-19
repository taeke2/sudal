package com.shop.sudal.global.common;

import com.shop.sudal.domain.address.model.CreateAddressRequest;
import com.shop.sudal.domain.address.service.AddressService;
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
    private final AddressService addressService;

    @PostConstruct
    public void init() {
        createRoles();
        createMember();
        addMemberRole_ADMIN();
        createAddress();
    }

    private void createAddress() {
        LoginMemberRequest rootMember = new LoginMemberRequest("root@sudal.com", "root1016");
        LoginMemberResponse rootResponse = authService.login(rootMember);
        MemberDto root = rootResponse.getMember();
        Long memberId = root.getId();

        CreateAddressRequest address1 = new CreateAddressRequest(
                "123 Main St",
                "Apt 4B",
                "John Doe",
                "123-456-7890",
                "12345",
                "Leave at front door"
        );

        CreateAddressRequest address2 = new CreateAddressRequest(
                "456 Main St",
                "Apt 4B2",
                "John Doe2",
                "123-456-7890",
                "12345",
                "Leave at front door"
        );

        CreateAddressRequest address3 = new CreateAddressRequest(
                "789 Main St",
                "Apt 4B3",
                "John Doe3",
                "123-456-7890",
                "12345",
                "Leave at front door"
        );

        addressService.testCreateAddress(memberId, address1);
        addressService.testCreateAddress(memberId, address2);
        addressService.testCreateAddress(memberId, address3);
    }

    private void addMemberRole_ADMIN() {
        LoginMemberRequest rootMember = new LoginMemberRequest("root@sudal.com", "root1016");
        LoginMemberResponse rootResponse = authService.login(rootMember);
        MemberDto root = rootResponse.getMember();
        memberService.testAddMemberRole(root.getId(), RoleType.ADMIN);
        memberService.testAddMemberRole(root.getId(), RoleType.GUEST);
    }

    private void createMember() {
        CreateMemberRequest root = new CreateMemberRequest(
                "최고관리자",
                "root@sudal.com",
                "root1016",
                LocalDate.now(),
                "010-9547-2578",
                1
        );

        CreateMemberRequest member = new CreateMemberRequest(
                "test",
                "test@example.com",
                "1234",
                LocalDate.now(),
                "010-1234-5678",
                1
        );

        memberService.signup(root);
        memberService.signup(member);
    }

    private void createRoles() {
        List<CreateRoleRequest> createRoles = Arrays.asList(
                new CreateRoleRequest(RoleType.ADMIN),
                new CreateRoleRequest(RoleType.MEMBER),
                new CreateRoleRequest(RoleType.GUEST));

        for (CreateRoleRequest createRole : createRoles) {
            roleService.createRole(createRole);
        }
    }

}
