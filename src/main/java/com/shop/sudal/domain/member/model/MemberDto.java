package com.shop.sudal.domain.member.model;

import com.shop.sudal.domain.entity.Member;
import lombok.Data;

import java.time.LocalDate;

@Data
public class MemberDto {
    private Long id;
    private String name;
    private String email;
    private LocalDate birth;
    private String phone;
    private Integer gender;
    private Boolean isSuspended;
    private Boolean isDeleted;

    public MemberDto(Member member) {
        this.id = member.getId();
        this.name = member.getName();
        this.email = member.getEmail();
        this.birth = member.getBirth();
        this.phone = member.getPhone();
        this.gender = member.getGender();
        this.isSuspended = member.getIsSuspended();
        this.isDeleted = member.getIsDeleted();
    }
}
