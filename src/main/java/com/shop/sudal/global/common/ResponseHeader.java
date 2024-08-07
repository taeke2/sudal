package com.shop.sudal.global.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ResponseHeader {
    private Integer code;
    private String message;
}
