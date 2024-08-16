package com.shop.sudal.domain.member.role.model;

import com.shop.sudal.domain.entity.Role;
import com.shop.sudal.domain.entity.RoleType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateRoleRequest {
    private String role;

    public Role toEntityRole() {
        return Role.builder()
                .roleType(RoleType.valueOf(role))
                .build();
    }
}
