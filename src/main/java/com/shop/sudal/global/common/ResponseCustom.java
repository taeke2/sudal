package com.shop.sudal.global.common;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ResponseCustom<T> {

    private ResponseHeader header;
    private T data;
    private String message;

    private static final int SUCCESS = 200;

    public static <T> ResponseCustom<T> success(T data, String message) {
        return new ResponseCustom<>(new ResponseHeader(SUCCESS, "SUCCESS"), data, message);
    }

    public static <T> ResponseCustom<T> fail(ResponseCode responseCode, T data) {
        return new ResponseCustom<>(
                new ResponseHeader(responseCode.getHttpStatusCode(), responseCode.getMessage()), data, responseCode.getMessage()
        );
    }
}
