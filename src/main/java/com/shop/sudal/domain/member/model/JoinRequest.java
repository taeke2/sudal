package com.shop.sudal.domain.member.model;

import com.shop.sudal.domain.entity.Member;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class JoinRequest {
    private String name;
    private String email;
    private String password;
    private LocalDate birth;
    private String phone;
    private Integer gender;

    public void encodePassword(String encodedPassword) {
        this.password = encodedPassword;
    }

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
