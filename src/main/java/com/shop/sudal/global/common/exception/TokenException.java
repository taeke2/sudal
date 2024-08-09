package com.shop.sudal.global.common.exception;

import com.shop.sudal.global.common.response.ResponseCode;

public class TokenException extends BaseException {
    public TokenException(ResponseCode responseCode) {
        super(responseCode);
    }
}
