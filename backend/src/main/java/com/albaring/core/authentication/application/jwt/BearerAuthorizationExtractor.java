package com.albaring.core.authentication.application.jwt;

import static com.albaring.common.exception.common.ErrorType.INVALID_ACCESS_TOKEN;

import com.albaring.common.exception.auth.InvalidJwtException;
import org.springframework.stereotype.Component;

@Component
public class BearerAuthorizationExtractor {

  private static final String BEARER_TYPE = "Bearer ";

  public String extractAccessToken(String header) {
    if (header != null && header.startsWith(BEARER_TYPE)) {
      return header.substring(BEARER_TYPE.length()).trim();
    }
    throw new InvalidJwtException(INVALID_ACCESS_TOKEN);
  }
}
