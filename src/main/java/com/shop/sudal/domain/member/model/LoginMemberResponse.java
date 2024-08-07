package com.shop.sudal.domain.member.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginMemberResponse {
    private MemberDto memberDto;
    private TokenDto tokenDto;
}
