package com.shop.sudal.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String username;

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

    // build
    @Builder
    public User(String username, String email, String password, LocalDate birth, String phone,
                int gender, Boolean isSuspended, Boolean isDeleted) {

        if (username == null || username.isEmpty())
            throw new IllegalArgumentException("Username cannot be null or empty");
        if (email == null || email.isEmpty()) throw new IllegalArgumentException("Email cannot be null or empty");
        if (password == null || password.isEmpty())
            throw new IllegalArgumentException("Password cannot be null or empty");
        if (birth == null) throw new IllegalArgumentException("Birth cannot be null");
        if (phone == null || phone.isEmpty()) throw new IllegalArgumentException("Phone cannot be null or empty");
        if (gender < 1 || gender > 4) throw new IllegalArgumentException("Gender must be between 1 and 4");

        this.username = username;
        this.email = email;
        this.password = password;
        this.birth = birth;
        this.phone = phone;
        this.gender = gender;
        this.isSuspended = isSuspended;
        this.isDeleted = isDeleted;
    }
}
