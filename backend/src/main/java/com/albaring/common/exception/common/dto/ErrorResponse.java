package com.albaring.common.exception.common.dto;

import com.albaring.common.exception.common.ErrorCode;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@ToString
@AllArgsConstructor
public class ErrorResponse {

    @Schema(description = "에러 코드", example = "9999")
    private int errorCode;

    @Schema(description = "에러 메시지", example = "요청 파라미터 누락")
    private String message;

    @Schema(description = "HTTP 상태", example = "INTERNAL_SERVER_ERROR")
    private HttpStatus httpStatus;

    public ErrorResponse(ErrorCode errorCode) {
        this.errorCode = errorCode.getCode();
        this.message = errorCode.getMessage();
        this.httpStatus = errorCode.getHttpStatus();
    }

    public static ErrorResponse of(ErrorCode e) {
        return new ErrorResponse(e);
    }
}
