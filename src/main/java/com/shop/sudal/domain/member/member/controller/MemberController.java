package com.shop.sudal.domain.member.member.controller;

import com.shop.sudal.domain.member.member.model.AddMemberRoleRequest;
import com.shop.sudal.domain.member.member.model.CreateMemberRequest;
import com.shop.sudal.domain.member.member.service.MemberService;
import com.shop.sudal.global.response.ResponseCode;
import com.shop.sudal.global.response.ResponseCustom;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/join")
    public ResponseCustom<Void> join(@RequestBody CreateMemberRequest createMemberRequest) {
        return ResponseCustom.response(memberService.join(createMemberRequest), ResponseCode.MEMBER_CREATE_SUCCESS);
    }

    @PostMapping("/role")
    public ResponseCustom<Void> addMemberRole(@RequestBody AddMemberRoleRequest addMemberRoleRequest) {
        return ResponseCustom.response(memberService.addMemberRole(addMemberRoleRequest), ResponseCode.ROLE_ADD_SUCCESS);
    }
}
