package com.albaring.common.exception.auth;

import com.albaring.common.exception.common.ErrorType;
import lombok.Getter;

@Getter
public class UnauthorizedException extends RuntimeException {

  private final int code;

  public UnauthorizedException(ErrorType errorType) {
    super(errorType.getMessage());
    this.code = errorType.getCode();
  }
}
