package com.shop.sudal.domain.member.auth.controller;

import com.shop.sudal.domain.member.auth.model.TokenDto;
import com.shop.sudal.domain.member.auth.service.AuthService;
import com.shop.sudal.domain.member.member.model.LoginMemberRequest;
import com.shop.sudal.domain.member.member.model.LoginMemberResponse;
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
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseCustom<LoginMemberResponse> login(@RequestBody LoginMemberRequest loginMemberRequest) {
        return ResponseCustom.response(ResponseCode.MEMBER_LOGIN_SUCCESS, authService.login(loginMemberRequest));
    }

    @PostMapping("/refresh")
    public ResponseCustom<TokenDto> refreshAccessToken(@RequestBody TokenDto tokenDto) {
        return ResponseCustom.response(ResponseCode.TOKEN_CREATE_SUCCESS, authService.refreshAccessToken(tokenDto));
    }
}
