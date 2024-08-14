package com.shop.sudal.domain.member.auth.model;

import com.shop.sudal.domain.member.member.model.MemberDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LoginMemberResponse {
    private MemberDto member;
    private TokenDto token;
}
