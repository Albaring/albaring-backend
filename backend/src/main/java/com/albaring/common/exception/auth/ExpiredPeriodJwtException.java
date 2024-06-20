package com.albaring.common.exception.auth;

import com.albaring.common.exception.common.ErrorType;
import lombok.Getter;

@Getter
public class ExpiredPeriodJwtException extends UnauthorizedException {

  public ExpiredPeriodJwtException(ErrorType errorType) {
    super(errorType);
  }
}
