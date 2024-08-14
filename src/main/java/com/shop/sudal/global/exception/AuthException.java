package com.shop.sudal.global.exception;

import com.shop.sudal.global.common.response.ResponseCode;

public class AuthException extends BaseException {
    public AuthException(ResponseCode responseCode) {
        super(responseCode);
    }
}
