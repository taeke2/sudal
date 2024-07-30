package com.shop.sudal.global.common;

import com.shop.sudal.domain.user.dto.JwtDto;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponse<T> {
    private Boolean validated;
    private T data;
    private JwtDto jwt;
}
