package com.albaring.common.exception;

import com.albaring.common.exception.common.ErrorCode;
import lombok.Getter;

@Getter
public class ForbiddenException extends RuntimeException {

    private final ErrorCode errorCode;

    public ForbiddenException(final ErrorCode errorCode) {
        this.errorCode = errorCode;
    }
}
