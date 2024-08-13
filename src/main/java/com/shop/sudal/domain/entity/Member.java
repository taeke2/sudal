package com.shop.sudal.domain.entity;

import com.shop.sudal.domain.member.role.model.MemberRoleDto;
import com.shop.sudal.global.common.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private LocalDate birth;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private Integer gender;

    @Column(nullable = false)
    private Boolean isSuspended;

    @Column(nullable = false)
    private Boolean isDeleted;

    public void encodePassword(String encodedPassword) {
        this.password = encodedPassword;
    }

    // relationship
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MemberRole> memberRoles = new ArrayList<>();

    public void addRole(Role role) {
        if (memberRoles == null || memberRoles.isEmpty()) {
            memberRoles = new ArrayList<>();
        }
        MemberRole memberRole = new MemberRoleDto(this, role).toEntityMemberRole();
        memberRoles.add(memberRole);
    }

    public List<Role> getRoles() {
        return memberRoles.stream()
                .map(MemberRole::getRole)
                .collect(Collectors.toList());
    }

    public List<RoleType> getRoleTypes() {
        return this.getRoles().stream()
                .map(Role::getRoleType)
                .collect(Collectors.toList());
    }

    // build
    @Builder
    public Member(Long id, String name, String email, String password, LocalDate birth, String phone,
                  int gender, Boolean isSuspended, Boolean isDeleted, List<MemberRole> memberRoles) {

        if (name == null || name.isEmpty())
            throw new IllegalArgumentException("Member name cannot be null or empty");
        if (email == null || email.isEmpty()) throw new IllegalArgumentException("Email cannot be null or empty");
        if (password == null || password.isEmpty())
            throw new IllegalArgumentException("Password cannot be null or empty");
        if (birth == null) throw new IllegalArgumentException("Birth cannot be null");
        if (phone == null || phone.isEmpty()) throw new IllegalArgumentException("Phone cannot be null or empty");
        if (gender < 1 || gender > 4) throw new IllegalArgumentException("Gender must be between 1 and 4");

        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.birth = birth;
        this.phone = phone;
        this.gender = gender;
        this.isSuspended = isSuspended;
        this.isDeleted = isDeleted;
        this.memberRoles = memberRoles;
    }

}
