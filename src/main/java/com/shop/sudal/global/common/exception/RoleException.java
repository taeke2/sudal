package com.shop.sudal.global.common.exception;

import com.shop.sudal.global.common.response.ResponseCode;

public class RoleException extends BaseException {
    public RoleException(ResponseCode responseCode) {
        super(responseCode);
    }
}
