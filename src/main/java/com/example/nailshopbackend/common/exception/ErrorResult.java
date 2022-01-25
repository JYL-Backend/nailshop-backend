package com.example.nailshopbackend.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorResult {
    UNKNOWN_EXCEPTION(HttpStatus.INTERNAL_SERVER_ERROR, "알수없는 에러가 발생하였습니다"),
    NOT_FOUND_EMAIL(HttpStatus.BAD_REQUEST,"존재하지 않는 이메일입니다"),
    IS_SET_EMAIL(HttpStatus.BAD_REQUEST,"이미 존재하는 이메일입니다"),
    BAD_PASSWORD(HttpStatus.BAD_REQUEST,"암호가 맞지 않습니다"),
    ;
    private final HttpStatus httpStatus;
    private final String message;
}
