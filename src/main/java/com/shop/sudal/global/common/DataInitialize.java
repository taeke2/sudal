package com.shop.sudal.global.common;

import com.shop.sudal.domain.entity.RoleType;
import com.shop.sudal.domain.member.role.model.RoleDto;
import com.shop.sudal.domain.member.role.repository.RoleRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitialize {

    private final RoleRepository roleRepository;

    @PostConstruct
    public void init() {
        if (roleRepository.findByRoleType(RoleType.MEMBER).isEmpty()) {
            roleRepository.save(new RoleDto(RoleType.MEMBER).toEntityRole());
        }
        if (roleRepository.findByRoleType(RoleType.ADMIN).isEmpty()) {
            roleRepository.save(new RoleDto(RoleType.ADMIN).toEntityRole());
        }
        if (roleRepository.findByRoleType(RoleType.GUEST).isEmpty()) {
            roleRepository.save(new RoleDto(RoleType.GUEST).toEntityRole());
        }
    }
}
