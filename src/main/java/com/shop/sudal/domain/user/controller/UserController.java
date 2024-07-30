package com.shop.sudal.domain.user.controller;

import com.shop.sudal.domain.user.model.JoinRequest;
import com.shop.sudal.domain.user.model.LoginRequest;
import com.shop.sudal.domain.user.model.LoginDto;
import com.shop.sudal.domain.user.service.UserService;
import com.shop.sudal.global.common.response.LoginResponse;
import com.shop.sudal.global.common.response.ValidatedResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/login")
    public LoginResponse<LoginDto> login(@RequestBody LoginRequest loginRequest) {
        return userService.loginUser(loginRequest);
    }

    @PostMapping("/join")
    public ValidatedResponse<String> join(@RequestBody JoinRequest joinRequest) {
        return userService.joinUser(joinRequest);
    }
}
