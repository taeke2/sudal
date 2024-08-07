package com.shop.sudal.global.common.exception;

import com.shop.sudal.global.common.ResponseCode;

public class TokenException extends BaseException {
    public TokenException(ResponseCode responseCode) {
        super(responseCode);
    }
}
