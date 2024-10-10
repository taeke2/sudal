package com.shop.sudal.global.exception;

import com.shop.sudal.global.response.ResponseCode;

public class CategoryException extends BaseException {
    public CategoryException(ResponseCode responseCode) {
        super(responseCode);
    }
}
