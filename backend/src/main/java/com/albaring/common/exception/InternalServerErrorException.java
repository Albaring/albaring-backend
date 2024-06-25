package com.albaring.common.exception;

import com.albaring.common.exception.common.ErrorCode;
import lombok.Getter;

@Getter
public class InternalServerErrorException extends RuntimeException {

    private final ErrorCode errorCode;

    public InternalServerErrorException(final ErrorCode errorCode) {
        this.errorCode = errorCode;
    }
}
