package com.shop.sudal.global.exception;

import com.shop.sudal.global.response.ResponseCode;

public class RoleException extends BaseException {
    public RoleException(ResponseCode responseCode) {
        super(responseCode);
    }
}
