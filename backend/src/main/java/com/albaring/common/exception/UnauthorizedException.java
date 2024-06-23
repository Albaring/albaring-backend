package com.albaring.common.exception;

import com.albaring.common.exception.common.ErrorCode;
import lombok.Getter;

@Getter
public class UnauthorizedException extends RuntimeException {

    private final ErrorCode errorCode;

    public UnauthorizedException(final ErrorCode errorCode) {
        this.errorCode = errorCode;
    }
}
