package com.example.nailshopbackend.common.exception;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public class GlobalException extends RuntimeException{
    private final HttpStatus httpStatus;
    private final String errorMsg;

    public GlobalException(ErrorResult errorResult){
        this.httpStatus = errorResult.getHttpStatus();
        this.errorMsg = errorResult.getMessage();
    }
}
