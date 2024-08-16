package com.shop.sudal.domain.member.auth.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginMemberRequest {
    private String email;
    private String password;
}
