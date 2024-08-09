package com.shop.sudal.global.common.exception;

import com.shop.sudal.global.common.response.ResponseCode;

public class MemberException extends BaseException {
    public MemberException(ResponseCode responseCode) {
        super(responseCode);
    }
}
