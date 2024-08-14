package com.shop.sudal.domain.member.auth.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginMemberRequest {
    private String email;
    private String password;
}
