package com.dxc.user.common;

import org.springframework.http.HttpStatus;

public enum StorageError {
    
    UNEXPECTED(0, HttpStatus.INTERNAL_SERVER_ERROR),
    USER_NOT_FOUND(1001, HttpStatus.NOT_FOUND),
    USERNAME_NOT_VALIDATION(1003, HttpStatus.BAD_REQUEST),
    ID_NOT_VALIDATION(1004, HttpStatus.BAD_REQUEST),
    EMAIL_NOT_VALIDATION(1002, HttpStatus.BAD_REQUEST);


    
    private final int code;
    private final HttpStatus httpStatus;

    public int getCode() {
        return code;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    StorageError(int code, HttpStatus httpStatus) {
        this.code = code;
        this.httpStatus = httpStatus;
    }
}
