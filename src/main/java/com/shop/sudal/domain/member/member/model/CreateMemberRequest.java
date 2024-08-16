package com.shop.sudal.domain.member.member.model;

import com.shop.sudal.domain.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class CreateMemberRequest {
    private String name;
    private String email;
    private String password;
    private LocalDate birth;
    private String phone;
    private Integer gender;

    public Member toEntityMember() {
        return Member.builder()
                .name(name)
                .email(email)
                .password(password)
                .birth(birth)
                .phone(phone)
                .gender(gender)
                .isSuspended(false)
                .isDeleted(false)
                .build();
    }
}
