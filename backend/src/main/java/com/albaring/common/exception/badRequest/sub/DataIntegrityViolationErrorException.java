package com.albaring.common.exception.badRequest.sub;


import com.albaring.common.exception.common.ErrorType;
import com.albaring.common.exception.server.InternalServerErrorException;

public class DataIntegrityViolationErrorException extends InternalServerErrorException {

    private ErrorType errorType;

    public DataIntegrityViolationErrorException(ErrorType errorType) {
        super(errorType);
    }
}
