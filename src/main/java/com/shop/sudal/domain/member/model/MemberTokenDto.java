package com.shop.sudal.domain.member.model;

import com.shop.sudal.domain.entity.Member;
import com.shop.sudal.domain.entity.MemberToken;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class MemberTokenDto {

    private Member member;
    private String refreshToken;

    public MemberToken toEntityMemberToken() {
        return MemberToken.builder()
                .member(member)
                .refreshToken(refreshToken)
                .build();
    }
}
