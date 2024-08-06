package com.shop.sudal.domain.member.controller;

import com.shop.sudal.domain.member.model.JoinRequest;
import com.shop.sudal.domain.member.model.JwtDto;
import com.shop.sudal.domain.member.model.LoginRequest;
import com.shop.sudal.domain.member.model.LoginDto;
import com.shop.sudal.domain.member.service.MemberService;
import com.shop.sudal.global.common.response.LoginResponse;
import com.shop.sudal.global.common.response.ValidatedResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public LoginResponse<LoginDto> login(@RequestBody LoginRequest loginRequest) {
        return memberService.login(loginRequest);
    }

    @PostMapping("/join")
    public ValidatedResponse<String> join(@RequestBody JoinRequest joinRequest) {
        return memberService.join(joinRequest);
    }

    @PostMapping("/refresh")
    public ResponseEntity<JwtDto> refreshAccessToken(@RequestBody JwtDto jwtDto) {
        JwtDto newToken = memberService.refreshAccessToken(jwtDto.getRefreshToken());
        return ResponseEntity.ok(newToken);
    }
}
