package com.shop.sudal.global.common;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BasicResponse<T> {
    private T result;
}
