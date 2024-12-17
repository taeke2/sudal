package com.shop.sudal.domain.member.role.repository;

import com.shop.sudal.domain.entity.Role;
import com.shop.sudal.domain.entity.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByRoleType(RoleType type);

    Boolean existsByRoleType(RoleType type);
}
