package com.shop.sudal.domain.member.member.model;

import com.shop.sudal.domain.entity.Member;
import com.shop.sudal.domain.entity.RoleType;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
@AllArgsConstructor
public class MemberDto {
    private Long id;
    private String name;
    private String email;
    private LocalDate birth;
    private String phone;
    private Integer gender;
    private Boolean isSuspended;
    private Boolean isDeleted;
    private List<RoleType> roles;

    public MemberDto(Member member) {
        this.id = member.getId();
        this.name = member.getName();
        this.email = member.getEmail();
        this.birth = member.getBirth();
        this.phone = member.getPhone();
        this.gender = member.getGender();
        this.isSuspended = member.getIsSuspended();
        this.isDeleted = member.getIsDeleted();
        this.roles = member.getRoleTypes();
    }
}
