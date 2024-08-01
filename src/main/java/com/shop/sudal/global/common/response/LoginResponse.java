package com.shop.sudal.global.common.response;

import com.shop.sudal.domain.member.model.JwtDto;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponse<T> {
    private Boolean isValidated;
    private T data;
    private JwtDto jwt;
}
