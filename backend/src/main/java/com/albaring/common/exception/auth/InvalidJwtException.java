package com.albaring.common.exception.auth;

import com.albaring.common.exception.common.ErrorType;
import lombok.Getter;

@Getter
public class InvalidJwtException extends UnauthorizedException {

  public InvalidJwtException(ErrorType errorType) {
    super(errorType);
  }
}
