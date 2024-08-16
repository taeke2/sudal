package com.shop.sudal.domain.member.member.controller;

import com.shop.sudal.domain.member.member.model.AddMemberRoleRequest;
import com.shop.sudal.domain.member.member.model.CreateMemberRequest;
import com.shop.sudal.domain.member.member.service.MemberService;
import com.shop.sudal.global.response.ResponseCode;
import com.shop.sudal.global.response.ResponseCustom;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/join")
    public ResponseCustom<Void> join(@RequestBody CreateMemberRequest createMemberRequest) {
        memberService.join(createMemberRequest);
        return ResponseCustom.responseNoData(ResponseCode.MEMBER_CREATE_SUCCESS);
    }

    @PostMapping("/role")
    @Secured("ROLE_ADMIN")
    public ResponseCustom<Void> addMemberRole(@RequestBody AddMemberRoleRequest addMemberRoleRequest) {
        memberService.addMemberRole(addMemberRoleRequest);
        return ResponseCustom.responseNoData(ResponseCode.ROLE_ADD_SUCCESS);
    }
}
