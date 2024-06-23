package com.albaring.common.exception.common.dto;

import com.albaring.common.exception.common.ErrorCode;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class ErrorResponse {

    private int errorCode;
    private String message;
    private HttpStatus httpStatus;

    public ErrorResponse(ErrorCode errorCode) {
        this.errorCode = errorCode.getCode();
        this.message = errorCode.getMessage();
        this.httpStatus = errorCode.getHttpStatus();
    }

    public static ErrorResponse of(ErrorCode e) {
        return new ErrorResponse(e);
    }

    @Override
    public String toString() {
        return "ErrorResponse{" +
            "errorCode=" + errorCode +
            ", message='" + message + '\'' +
            '}';
    }
}
