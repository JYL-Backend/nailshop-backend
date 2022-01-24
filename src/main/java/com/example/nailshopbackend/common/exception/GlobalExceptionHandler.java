package com.example.nailshopbackend.common.exception;


import com.example.nailshopbackend.common.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private ResponseEntity<ErrorResponse> makeErrorResponseEntity(int errorCode, String errorMsg){
        return ResponseEntity.status(errorCode)
                .body(new ErrorResponse(errorCode, errorMsg));
    }
    private ResponseEntity<ErrorResponse> makeErrorResponseEntity(ErrorResult errorResult){
        return ResponseEntity.status(errorResult.getHttpStatus())
                .body(new ErrorResponse(errorResult.getHttpStatus().value(), errorResult.getMessage()));
    }

        /* valid 에러 */
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<ErrorResponse> handleValidApiException(final MethodArgumentNotValidException exception){
        System.out.println("Error : " + exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), exception.getBindingResult().getAllErrors().get(0).getDefaultMessage()));
    }


        /* 사용자 정의 에러 */
    @ExceptionHandler({GlobalException.class})
    public ResponseEntity<ErrorResponse> handleRestApiException(final GlobalException exception){
        System.out.println("Error : " + exception.getMessage());
        return this.makeErrorResponseEntity(exception.getHttpStatus().value(), exception.getErrorMsg());
    }

        /* 공통 에러 */
    @ExceptionHandler({Exception.class})
    public ResponseEntity<ErrorResponse> handleException(final Exception exception){
        System.out.println("Error : " + exception.getMessage());
        return this.makeErrorResponseEntity(500, ErrorResult.UNKNOWN_EXCEPTION.getMessage());
    }

        /* 로그인 에러 */
    @ExceptionHandler({UsernameNotFoundException.class})
    public ResponseEntity<ErrorResponse> UsernameNotFoundException(final UsernameNotFoundException exception){
        System.out.println("Error22 : " + exception.getMessage());
        return this.makeErrorResponseEntity(401, exception.getMessage());
    }

        /* 인증 에러*/
    @ExceptionHandler({InternalAuthenticationServiceException.class})
    public ResponseEntity<ErrorResponse> InternalAuthenticationServiceException(final InternalAuthenticationServiceException exception){
        return this.makeErrorResponseEntity(401, ErrorResult.NOT_FOUND_EMAIL.getMessage());
    }
    /* 암호 에러*/
    @ExceptionHandler({BadCredentialsException.class})
    public ResponseEntity<ErrorResponse> BadCredentialsException(final BadCredentialsException exception){
        return this.makeErrorResponseEntity(401, ErrorResult.BAD_PASSWORD.getMessage());
    }
}
