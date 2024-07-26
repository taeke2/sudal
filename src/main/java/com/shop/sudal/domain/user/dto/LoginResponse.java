package com.shop.sudal.domain.user.dto;

import com.shop.sudal.domain.entity.User;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class LoginResponse {
    private Long id;
    private String username;
    private String email;
    private LocalDate birth;
    private String phone;
    private String gender;
    private Boolean isSuspended;
    private Boolean isDeleted;

    public LoginResponse(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.birth = user.getBirth();
        this.phone = user.getPhone();
        this.gender = user.getGender() == 1 || user.getGender() == 3 ? "male" : "female";
        this.isSuspended = user.getIsSuspended();
        this.isDeleted = user.getIsDeleted();
    }
}
