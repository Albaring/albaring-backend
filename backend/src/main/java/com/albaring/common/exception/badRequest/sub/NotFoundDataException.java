package com.albaring.common.exception.badRequest.sub;


import com.albaring.common.exception.badRequest.BadRequestException;
import com.albaring.common.exception.common.ErrorType;

public class NotFoundDataException extends BadRequestException {

    public NotFoundDataException(ErrorType errorType) {
        super(errorType);
    }
}
