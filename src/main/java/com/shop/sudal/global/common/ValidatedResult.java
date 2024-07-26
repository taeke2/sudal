package com.shop.sudal.global.common;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ValidatedResult<T> {
    private Boolean validated;
    private T data;
}
