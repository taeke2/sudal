package com.shop.sudal.domain.member.role.model;

import com.shop.sudal.domain.entity.Member;
import com.shop.sudal.domain.entity.MemberRole;
import com.shop.sudal.domain.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MemberRoleDto {
    private Member member;
    private Role role;

    public MemberRole toEntityMemberRole() {
        return MemberRole.builder()
                .member(member)
                .role(role)
                .build();
    }
}
