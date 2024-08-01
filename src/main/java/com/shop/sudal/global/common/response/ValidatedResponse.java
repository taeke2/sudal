package com.shop.sudal.global.common.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ValidatedResponse<T> {
    private Boolean isValidated;
    private T data;
}
