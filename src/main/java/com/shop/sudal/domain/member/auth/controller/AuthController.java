package com.shop.sudal.domain.member.auth.controller;

import com.shop.sudal.domain.member.auth.model.TokenDto;
import com.shop.sudal.domain.member.auth.service.AuthService;
import com.shop.sudal.domain.member.auth.model.LoginMemberRequest;
import com.shop.sudal.domain.member.auth.model.LoginMemberResponse;
import com.shop.sudal.global.response.ResponseCode;
import com.shop.sudal.global.response.ResponseCustom;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseCustom<LoginMemberResponse> login(@RequestBody LoginMemberRequest loginMemberRequest) {
        return ResponseCustom.response(authService.login(loginMemberRequest), ResponseCode.MEMBER_LOGIN_SUCCESS);
    }

    @PostMapping("/refresh")
    public ResponseCustom<TokenDto> refreshAccessToken(@RequestBody TokenDto tokenDto) {
        return ResponseCustom.response(authService.refreshAccessToken(tokenDto), ResponseCode.TOKEN_CREATE_SUCCESS);
    }

    @DeleteMapping("/logout")
    public ResponseCustom<Void> logout() {
        return ResponseCustom.response(authService.logout(), ResponseCode.MEMBER_LOGOUT_SUCCESS);
    }
}
