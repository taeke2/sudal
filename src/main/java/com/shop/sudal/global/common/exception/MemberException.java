package com.shop.sudal.global.common.exception;

import com.shop.sudal.global.common.ResponseCode;

public class MemberException extends BaseException {
    public MemberException(ResponseCode responseCode) {
        super(responseCode);
    }
}
