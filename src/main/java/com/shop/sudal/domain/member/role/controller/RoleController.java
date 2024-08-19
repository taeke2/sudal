package com.shop.sudal.domain.member.role.controller;

import com.shop.sudal.domain.member.role.model.CreateRoleRequest;
import com.shop.sudal.domain.member.role.service.RoleService;
import com.shop.sudal.global.response.ResponseCode;
import com.shop.sudal.global.response.ResponseCustom;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class RoleController {

    private final RoleService roleService;

    @PostMapping
    @Secured({"ROLE_ADMIN"})
    public ResponseCustom<Void> createRole(@RequestBody CreateRoleRequest createRoleRequest) {
        roleService.createRole(createRoleRequest);
        return ResponseCustom.responseNoData(ResponseCode.ROLE_CREATE_SUCCESS);
    }
}
