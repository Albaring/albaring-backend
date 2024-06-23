package com.albaring.common.exception;

import com.albaring.common.exception.common.ErrorCode;
import lombok.Getter;

@Getter
public class BadRequestException extends RuntimeException {

    private final ErrorCode errorCode;

    public BadRequestException(final ErrorCode errorCode) {
        this.errorCode = errorCode;
    }
}
