package com.shop.sudal.global.common;

import com.shop.sudal.global.common.exception.MemberException;
import com.shop.sudal.global.common.exception.RoleException;
import com.shop.sudal.global.common.exception.AuthException;
import com.shop.sudal.global.common.response.ResponseCustom;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MemberException.class)
    public ResponseCustom<Void> handleMemberException(MemberException e) {
        return ResponseCustom.response(e.getResponseCode(), null);
    }

    @ExceptionHandler(AuthException.class)
    public ResponseCustom<Void> handleAuthException(AuthException e) {
        return ResponseCustom.response(e.getResponseCode(), null);
    }

    @ExceptionHandler(RoleException.class)
    public ResponseCustom<Void> handleRoleException(RoleException e) {
        return ResponseCustom.response(e.getResponseCode(), null);
    }
}
