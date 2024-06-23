package com.albaring.core.authentication.exception;

import com.albaring.common.exception.common.ErrorType;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
public class AuthenticationException extends RuntimeException {

    private final int code;

    public AuthenticationException(ErrorType errorType) {
        super(errorType.getMessage());
        this.code = errorType.getCode();
    }

}
