package com.shop.sudal.domain.user.model;

import com.shop.sudal.domain.entity.User;
import lombok.Data;

import java.time.LocalDate;

@Data
public class LoginDto {
    private Long id;
    private String username;
    private String email;
    private LocalDate birth;
    private String phone;
    private Integer gender;
    private Boolean isSuspended;
    private Boolean isDeleted;

    public LoginDto(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.birth = user.getBirth();
        this.phone = user.getPhone();
        this.gender = user.getGender();
        this.isSuspended = user.getIsSuspended();
        this.isDeleted = user.getIsDeleted();
    }
}
