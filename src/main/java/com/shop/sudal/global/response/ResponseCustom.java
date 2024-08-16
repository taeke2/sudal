package com.shop.sudal.global.response;

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

    public static <T> ResponseCustom<T> response(T data, ResponseCode responseCode) {
        return new ResponseCustom<>(new ResponseHeader(responseCode), data);
    }

    public static <T> ResponseCustom<T> responseNoData(ResponseCode responseCode) {
        return new ResponseCustom<>(new ResponseHeader(responseCode), null);
    }
}
