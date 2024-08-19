package com.shop.sudal.domain.member.role.model;

import com.shop.sudal.domain.entity.Role;
import com.shop.sudal.domain.entity.RoleType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateRoleRequest {
    @NonNull
    private RoleType role;

    public Role toEntityRole() {
        return Role.builder()
                .roleType(role)
                .build();
    }
}
