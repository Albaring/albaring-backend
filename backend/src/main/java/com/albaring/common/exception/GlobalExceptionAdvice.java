package com.albaring.common.exception;

import static com.albaring.common.exception.common.ErrorCode.INVALID_REQUEST_BODY;
import static com.albaring.common.exception.common.ErrorCode.NOT_FOUND_COOKIE;
import static com.albaring.common.exception.common.ErrorCode.UNHANDLED_EXCEPTION;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

import com.albaring.common.exception.common.ErrorCode;
import com.albaring.common.exception.common.dto.ErrorResponse;
import com.albaring.common.exception.common.dto.ValidationErrorResponse;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestCookieException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@Slf4j
@RestControllerAdvice
@ControllerAdvice
public class GlobalExceptionAdvice {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponse> badRequestExceptionHandler(final BadRequestException e) {
        log.warn("Bad Request Exception", e);

        ErrorResponse errorResponse = ErrorResponse.of(e.getErrorCode());
        return new ResponseEntity<>(errorResponse, errorResponse.getHttpStatus());
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ErrorResponse> unauthorizedExceptionHandler(
        final UnauthorizedException e) {
        log.warn("Unauthorized Exception", e);

        ErrorResponse errorResponse = ErrorResponse.of(e.getErrorCode());
        return new ResponseEntity<>(errorResponse, errorResponse.getHttpStatus());
    }

    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<ErrorResponse> forbiddenExceptionHandler(final ForbiddenException e) {
        log.warn("Forbidden Exception", e);

        ErrorResponse errorResponse = ErrorResponse.of(e.getErrorCode());
        return new ResponseEntity<>(errorResponse, errorResponse.getHttpStatus());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorResponse> handleValidationExceptions(
        MethodArgumentNotValidException e) {
        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach((error) -> {
            errors.put(((FieldError) error).getField(), error.getDefaultMessage());
        });
        return ResponseEntity.unprocessableEntity()
            .body(new ValidationErrorResponse(ErrorCode.UNPROCESSABLE_ENTITY.getCode(), errors));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleHttpMessageNotReadableException(
        HttpMessageNotReadableException e) {
        log.warn("HttpMessageNotReadableException", e);


        ErrorResponse errorResponse = ErrorResponse.of(INVALID_REQUEST_BODY);
        return new ResponseEntity<>(errorResponse, errorResponse.getHttpStatus());
    }

    @ExceptionHandler(MissingRequestCookieException.class)
    public ResponseEntity<ErrorResponse> handleMissingRequestCookieException(
        MissingRequestCookieException e) {
        log.warn("MissingRequestCookieException", e);

        ErrorResponse errorResponse = ErrorResponse.of(NOT_FOUND_COOKIE);
        return new ResponseEntity<>(errorResponse, errorResponse.getHttpStatus());
    }

    @ExceptionHandler(InternalServerErrorException.class) // TODO: 슬랙 알람 기능 추가
    public ResponseEntity<ErrorResponse> InternalServerExceptionHandle(
        final InternalServerErrorException e) {
        log.warn("InternalServerError Exception", e);

        ErrorResponse errorResponse = ErrorResponse.of(e.getErrorCode());
        return new ResponseEntity<>(errorResponse, errorResponse.getHttpStatus());
    }

    @ExceptionHandler(Exception.class) // TODO: 슬랙 알람 기능 추가
    public ResponseEntity<ErrorResponse> unHandledExceptionHandler(final Exception e) {
        log.error("Not Expected Exception is Occurred", e);

        final ErrorResponse response = ErrorResponse.of(UNHANDLED_EXCEPTION);

        return new ResponseEntity<>(response, INTERNAL_SERVER_ERROR);
    }

}


