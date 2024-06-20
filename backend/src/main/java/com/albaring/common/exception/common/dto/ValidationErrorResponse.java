package com.albaring.common.exception.common.dto;

import java.util.Map;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class ValidationErrorResponse {

    private int errorCode;
    private Map<String, String> message;

    public ValidationErrorResponse(final int errorCode, final Map<String, String> message) {
        this.errorCode = errorCode;
        this.message = message;
    }
}
