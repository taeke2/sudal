package com.shop.sudal.global.exception;

import com.shop.sudal.global.response.ResponseCode;

public class ItemException extends BaseException {
    public ItemException(ResponseCode responseCode) {
        super(responseCode);
    }
}
