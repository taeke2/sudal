package com.shop.sudal.global.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum ResponseCode {

    // 200 OK (조회)
    MEMBER_LIST_READ_SUCCESS(HttpStatus.OK, true, "사용자 목록 조회 성공"),
    MEMBER_INFO_READ_SUCCESS(HttpStatus.OK, true, "사용자 정보 조회 성공"),
    MEMBER_LOGIN_SUCCESS(HttpStatus.OK, true, "사용자 로그인 성공"),
    ADDRESS_LIST_READ_SUCCESS(HttpStatus.OK, true, "사용자 주소 목록 조회 성공"),

    // 201 Created (생성[추가])
    MEMBER_CREATE_SUCCESS(HttpStatus.CREATED, true, "사용자 생성 성공"),
    TOKEN_CREATE_SUCCESS(HttpStatus.CREATED, true, "토큰 재발급 성공"),
    ADDRESS_CREATE_SUCCESS(HttpStatus.CREATED, true, "주소 생성 성공"),
    ROLE_CREATE_SUCCESS(HttpStatus.CREATED, true, "권한 생성 성공"),
    ROLE_UPDATE_SUCCESS(HttpStatus.CREATED, true, "권한 변경 성공"),

    // 204 No Content (수정)
    MEMBER_UPDATE_SUCCESS(HttpStatus.NO_CONTENT, true, "사용자 정보 수정 성공"),
    MEMBER_LOGOUT_SUCCESS(HttpStatus.NO_CONTENT, true, "사용자 로그아웃 성공"),
    MEMBER_DELETE_SUCCESS(HttpStatus.NO_CONTENT, true, "사용자 회원탈퇴 성공"),
    ADDRESS_UPDATE_SUCCESS(HttpStatus.NO_CONTENT, true, "사용자 주소 수정 성공"),
    ADDRESS_DEFAULT_UPDATE_SUCCESS(HttpStatus.NO_CONTENT, true, "기본 주소 변경 성공"),

    // 400 Bad Request (잘못된 요청)
    BAD_REQUEST(HttpStatus.BAD_REQUEST, false, "잘못된 요청입니다."),
    INVALID_INPUT_VALUE(HttpStatus.BAD_REQUEST, false, "입력값이 유효하지 않습니다."),
    DUPLICATE_INPUT_VALUE(HttpStatus.BAD_REQUEST, false, "중복된 입력값입니다."),

    // 401 Unauthorized (인증 불가)
    MEMBER_PASSWORD_INVALID(HttpStatus.UNAUTHORIZED, false, "비밀번호가 일치하지 않습니다."),
    TOKEN_INVALID(HttpStatus.UNAUTHORIZED, false, "유효하지 않은 토큰입니다."),
    TOKEN_EXPIRED(HttpStatus.UNAUTHORIZED, false, "토큰이 만료되었습니다."),
    UNAUTHORIZED_ACCESS(HttpStatus.UNAUTHORIZED, false, "인증되지 않은 사용자입니다."),
    AUTHENTICATION_INVALID(HttpStatus.UNAUTHORIZED, false, "잘못된 인증정보입니다."),

    // 403 Forbidden (권한 없음)
    ACCESS_FORBIDDEN(HttpStatus.FORBIDDEN, false, "접근 권한이 없습니다."),

    // 404 Not Found (존재하지 않음)
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, false, "사용자를 찾을 수 없습니다."),
    TOKEN_NOT_FOUND(HttpStatus.NOT_FOUND, false, "사용자의 토큰을 찾을 수 없습니다."),
    ROLE_NOT_FOUND(HttpStatus.NOT_FOUND, false, "해당 권한을 찾을 수 없습니다."),
    ADDRESS_NOT_FOUND(HttpStatus.NOT_FOUND, false, "해당 주소를 찾을 수 없습니다."),

    // 405 Method Not Allowed (허용되지 않음)
    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED, false, "허용되지 않은 메소드입니다."),

    // 409 Conflict (중복)
    MEMBER_ALREADY_EXIST(HttpStatus.CONFLICT, false, "이미 가입한 사용자입니다."),
    MEMBER_NAME_ALREADY_EXIST(HttpStatus.CONFLICT, false, "이미 존재하는 닉네임입니다."),
    MEMBER_ROLE_ALREADY_EXIST(HttpStatus.CONFLICT, false, "현재 사용자 권한입니다."),
    ADMIN_ROLE_ALREADY_EXIST(HttpStatus.CONFLICT, false, "현재 관리자 권한입니다."),
    ROLE_ALREADY_EXIST(HttpStatus.CONFLICT, false, "이미 존재하는 권한입니다."),
    ADDRESS_ALREADY_EXIST(HttpStatus.CONFLICT, false, "동일한 주소가 존재합니다."),

    // 500 Internal Server Error (서버 오류)
    ROLE_TYPE_INVALID(HttpStatus.INTERNAL_SERVER_ERROR, false, "권한(ROLE) 타입 오류"),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, false, "서버에 오류가 발생하였습니다.");

    private final HttpStatus httpStatus;
    private final Boolean isSuccess;
    private final String message;

    public int getHttpStatusCode() {
        return httpStatus.value();
    }

}
