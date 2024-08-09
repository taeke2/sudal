package com.shop.sudal.domain.member.controller;

import com.shop.sudal.domain.member.model.*;
import com.shop.sudal.domain.member.service.MemberService;
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

    @PostMapping("/login")
    public ResponseCustom<LoginMemberResponse> login(@RequestBody LoginMemberRequest loginMemberRequest) {
        return ResponseCustom.success(memberService.login(loginMemberRequest), ResponseCode.MEMBER_LOGIN_SUCCESS.getMessage());
    }

    @PostMapping("/join")
    public ResponseCustom<Void> join(@RequestBody CreateMemberRequest createMemberRequest) {
        return ResponseCustom.success(memberService.join(createMemberRequest), ResponseCode.MEMBER_CREATE_SUCCESS.getMessage());
    }

    @PostMapping("/refresh")
    public ResponseCustom<TokenDto> refreshAccessToken(@RequestBody TokenDto tokenDto) {
        return ResponseCustom.success(memberService.refreshAccessToken(tokenDto), ResponseCode.TOKEN_CREATE_SUCCESS.getMessage());
    }
}
