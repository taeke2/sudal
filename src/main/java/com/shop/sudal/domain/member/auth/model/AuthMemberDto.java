package com.shop.sudal.domain.member.auth.model;

import com.shop.sudal.domain.entity.Member;
import com.shop.sudal.domain.entity.RoleType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AuthMemberDto {
    private Long id;
    private String email;
    private String password;
    private String name;
    private List<RoleType> roles;

    public AuthMemberDto(Member member) {
        this.id = member.getId();
        this.email = member.getEmail();
        this.password = member.getPassword();
        this.name = member.getName();
        this.roles = member.getRoleTypes();
    }
}
