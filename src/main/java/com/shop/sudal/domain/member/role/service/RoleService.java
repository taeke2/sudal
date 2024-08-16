package com.shop.sudal.domain.member.role.service;

import com.shop.sudal.domain.entity.RoleType;
import com.shop.sudal.domain.member.role.model.CreateRoleRequest;
import com.shop.sudal.domain.member.role.repository.RoleRepository;
import com.shop.sudal.global.exception.RoleException;
import com.shop.sudal.global.response.ResponseCode;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@Transactional
public class RoleService {
    private final RoleRepository roleRepository;

    public Void createRole(CreateRoleRequest createRoleRequest) {
        if(roleRepository.existsByRoleType(RoleType.valueOf(createRoleRequest.getRole()))){
            throw new RoleException(ResponseCode.MEMBER_ROLE_ALREADY_EXIST);
        }

        roleRepository.save(createRoleRequest.toEntityRole());

        return null;
    }
}
