//package com.shop.sudal.global.common;
//
//import com.shop.sudal.domain.entity.RoleType;
//import com.shop.sudal.domain.member.member.model.CreateMemberRequest;
//import com.shop.sudal.domain.member.member.service.MemberService;
//import com.shop.sudal.domain.member.role.model.CreateRoleRequest;
//import com.shop.sudal.domain.member.role.service.RoleService;
//import jakarta.annotation.PostConstruct;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.time.LocalDate;
//import java.util.Arrays;
//import java.util.List;
//
//@Component
//@RequiredArgsConstructor
//@Transactional
//public class DataInitialize {
//
//    private final RoleService roleService;
//    private final MemberService memberService;
//
//    @PostConstruct
//    public void init() {
//        createRoles();
//        createMember();
//    }
//
//    // TODO: 데이터 베이스 재생성 안할 시 삭제_240919
//    private void createMember() {
//        // 회원 생성
//        CreateMemberRequest createRootRequest = new CreateMemberRequest(
//                "최고관리자",
//                "root@sudal.com",
//                "root1016",
//                LocalDate.now(),
//                "010-9547-2578",
//                1
//        );
//
//        memberService.initSignup(createRootRequest);
//    }
//
//    private void createRoles() {
//        List<CreateRoleRequest> createRoles = Arrays.asList(
//                new CreateRoleRequest(RoleType.ADMIN),
//                new CreateRoleRequest(RoleType.MEMBER),
//                new CreateRoleRequest(RoleType.GUEST));
//
//        for (CreateRoleRequest createRole : createRoles) {
//            roleService.createRole(createRole);
//        }
//    }
//
//}
