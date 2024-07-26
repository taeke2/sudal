package com.shop.sudal.global.common;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BasicResult<T> {
    private T result;
}
