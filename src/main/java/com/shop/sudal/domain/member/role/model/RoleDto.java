package com.shop.sudal.domain.member.role.model;

import com.shop.sudal.domain.entity.Role;
import com.shop.sudal.domain.entity.RoleType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RoleDto {
    private RoleType roleType;

    public Role toEntityRole() {
        return Role.builder()
                .roleType(roleType)
                .build();
    }
}
