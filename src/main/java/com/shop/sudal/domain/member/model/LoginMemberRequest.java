package com.shop.sudal.domain.member.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginMemberRequest {
    private String email;
    private String password;
}
