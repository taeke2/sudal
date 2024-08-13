package com.shop.sudal.domain.member.member.controller;

import com.shop.sudal.domain.member.member.model.CreateMemberRequest;
import com.shop.sudal.domain.member.member.service.MemberService;
import com.shop.sudal.global.common.response.ResponseCode;
import com.shop.sudal.global.common.response.ResponseCustom;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
        return ResponseCustom.response(ResponseCode.MEMBER_CREATE_SUCCESS, memberService.join(createMemberRequest));
    }

}
