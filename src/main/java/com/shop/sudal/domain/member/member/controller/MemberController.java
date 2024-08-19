package com.shop.sudal.domain.member.member.controller;

import com.shop.sudal.domain.member.member.model.AddMemberRoleRequest;
import com.shop.sudal.domain.member.member.model.CreateMemberRequest;
import com.shop.sudal.domain.member.member.model.MemberDto;
import com.shop.sudal.domain.member.member.service.MemberService;
import com.shop.sudal.global.response.ResponseCode;
import com.shop.sudal.global.response.ResponseCustom;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/signup")
    public ResponseCustom<Void> signup(@RequestBody CreateMemberRequest createMemberRequest) {
        memberService.signup(createMemberRequest);
        return ResponseCustom.responseNoData(ResponseCode.MEMBER_CREATE_SUCCESS);
    }

    @PostMapping("/member-roles")
    @Secured({"ROLE_ADMIN"})
    public ResponseCustom<Void> addMemberRole(@RequestBody AddMemberRoleRequest addMemberRoleRequest) {
        memberService.addMemberRole(addMemberRoleRequest);
        return ResponseCustom.responseNoData(ResponseCode.ROLE_ADD_SUCCESS);
    }

    @GetMapping
    @Secured({"ROLE_ADMIN"})
    public ResponseCustom<List<MemberDto>> getMemberList() {
        return ResponseCustom.response(memberService.getMemberList(), ResponseCode.MEMBER_LIST_READ_SUCCESS);
    }
}
