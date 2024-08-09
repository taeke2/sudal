package com.shop.sudal.global.common.exception;

import com.shop.sudal.global.common.response.ResponseCustom;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MemberException.class)
    public ResponseCustom<Void> handleMemberException(MemberException e) {
        return ResponseCustom.fail(e.getResponseCode(), null);
    }

    @ExceptionHandler(TokenException.class)
    public ResponseCustom<Void> handleTokenException(TokenException e) {
        return ResponseCustom.fail(e.getResponseCode(), null);
    }
}
