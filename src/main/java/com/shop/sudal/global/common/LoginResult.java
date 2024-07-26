package com.shop.sudal.global.common;

import com.shop.sudal.domain.user.dto.JwtResponse;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResult<T> {
    private Boolean validated;
    private T data;
    private JwtResponse jwt;
}
