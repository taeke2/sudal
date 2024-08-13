package com.shop.sudal.domain.member.member.model;

import com.shop.sudal.domain.member.auth.model.TokenDto;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginMemberResponse {
    private MemberDto member;
    private TokenDto token;
}
