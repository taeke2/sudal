package com.shop.sudal.domain.member.member.model;

import com.shop.sudal.domain.entity.RoleType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AddMemberRoleRequest {
    private RoleType role;
}
