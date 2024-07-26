package com.shop.sudal.domain.user.dto;

import com.shop.sudal.domain.entity.User;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class JoinRequest {
    private String username;
    private String email;
    private String password;
    private LocalDate birth;
    private String phone;
    private Integer gender;

    public void encodePassword(String encodedPassword) {
        this.password = encodedPassword;
    }

    public User toJoinUser() {
        return User.builder()
                .username(username)
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
