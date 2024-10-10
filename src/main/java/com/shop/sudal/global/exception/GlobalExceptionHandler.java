package com.shop.sudal.global.exception;

import com.shop.sudal.global.response.ResponseCustom;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MemberException.class)
    public ResponseCustom<Void> handleMemberException(MemberException e) {
        return ResponseCustom.exception(e.getResponseCode());
    }

    @ExceptionHandler(AuthException.class)
    public ResponseCustom<Void> handleAuthException(AuthException e) {
        return ResponseCustom.exception(e.getResponseCode());
    }

    @ExceptionHandler(RoleException.class)
    public ResponseCustom<Void> handleRoleException(RoleException e) {
        return ResponseCustom.exception(e.getResponseCode());
    }

    @ExceptionHandler(AddressException.class)
    public ResponseCustom<Void> handleAddressException(AddressException e) {
        return ResponseCustom.exception(e.getResponseCode());
    }

    @ExceptionHandler(CategoryException.class)
    public ResponseCustom<Void> handleCategoryException(AddressException e) {
        return ResponseCustom.exception(e.getResponseCode());
    }
}
