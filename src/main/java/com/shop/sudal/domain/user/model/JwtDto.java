package com.shop.sudal.domain.user.model;

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
