package com.albaring.common.exception.badRequest;

import com.albaring.common.exception.common.ErrorType;
import lombok.Getter;

@Getter
public class BadRequestException extends RuntimeException {

    private final int code;

    public BadRequestException(final ErrorType errorType) {
        super(errorType.getMessage());
        this.code = errorType.getCode();
    }
}
