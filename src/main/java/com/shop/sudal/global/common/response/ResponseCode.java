package com.shop.sudal.global.common.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum ResponseCode {

    // 200 OK
    MEMBER_READ_SUCCESS(HttpStatus.OK, true, "사용자 정보 조회 성공"),
    MEMBER_LOGIN_SUCCESS(HttpStatus.OK, true, "사용자 로그인 성공"),

    // 201 Created
    MEMBER_CREATE_SUCCESS(HttpStatus.CREATED, true, "사용자 생성 성공"),
    TOKEN_CREATE_SUCCESS(HttpStatus.CREATED, true, "토큰 재발급 성공"),
    ADDRESS_CREATE_SUCCESS(HttpStatus.CREATED, true, "주소 생성 성공"),

    // 204 No Content
    MEMBER_UPDATE_SUCCESS(HttpStatus.NO_CONTENT, true, "사용자 정보 수정 성공"),

    // 400 Bad Request
    BAD_REQUEST(HttpStatus.BAD_REQUEST, false, "잘못된 요청입니다."),

    // 401 Unauthorized
    MEMBER_PASSWORD_INVALID(HttpStatus.UNAUTHORIZED, false, "비밀번호가 일치하지 않습니다."),
    TOKEN_INVALID(HttpStatus.UNAUTHORIZED, false, "유효하지 않은 토큰입니다."),
    TOKEN_EXPIRED(HttpStatus.UNAUTHORIZED, false, "토큰이 만료되었습니다."),

    // 403 Forbidden
    FORBIDDEN(HttpStatus.FORBIDDEN, false, "권한이 없습니다."),

    // 404 Not Found
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, false, "사용자를 찾을 수 없습니다."),
    TOKEN_NOT_FOUND(HttpStatus.NOT_FOUND, false, "해당 토큰이 존재하지 않습니다."),
    ROLE_NOT_FOUND(HttpStatus.NOT_FOUND, false, "해당 권한이 존재하지 않습니다."),

    // 405 Method Not Allowed
    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED, false, "허용되지 않은 메소드입니다."),

    // 409 Conflict
    MEMBER_ALREADY_EXIST(HttpStatus.CONFLICT, false, "이미 가입한 사용자입니다."),
    MEMBER_NAME_ALREADY_EXIST(HttpStatus.CONFLICT, false, "이미 존재하는 닉네임입니다."),

    // 500 Internal Server Error
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, false, "서버에 오류가 발생하였습니다.");

    private final HttpStatus httpStatus;
    private final Boolean isSuccess;
    private final String message;

    public int getHttpStatusCode() {
        return httpStatus.value();
    }
}
