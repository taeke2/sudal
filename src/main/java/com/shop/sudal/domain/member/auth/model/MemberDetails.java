package com.shop.sudal.domain.member.auth.model;

import com.shop.sudal.domain.entity.RoleType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@RequiredArgsConstructor
public class MemberDetails implements UserDetails {

    private final AuthMemberDto authMemberDto;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<RoleType> roles = authMemberDto.getRoles();

        return roles.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.name()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return authMemberDto.getPassword();
    }

    @Override
    public String getUsername() {
        return authMemberDto.getId().toString();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // 계정 만료 여부 로직이 필요하다면 수정
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // 계정 잠금 여부 로직이 필요하다면 수정
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // 자격 증명 만료 여부 로직이 필요하다면 수정
    }

    @Override
    public boolean isEnabled() {
        return true; // 계정 활성화 여부 로직이 필요하다면 수정
    }
}
