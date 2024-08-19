package com.shop.sudal.global.exception;

import com.shop.sudal.global.response.ResponseCode;

public class AddressException extends BaseException {
    public AddressException(ResponseCode responseCode) {
        super(responseCode);
    }
}
