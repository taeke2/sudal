package com.shop.sudal.global.common.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ResponseHeader {
    private Integer code;
    private String message;
}
