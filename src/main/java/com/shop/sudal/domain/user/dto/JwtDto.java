package com.shop.sudal.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class JwtDto {
    private String accessToken;
    private String refreshToken;
}
