package com.albaring.core.authentication.application.jwt;

import static com.albaring.common.exception.common.ErrorType.EXPIRED_PERIOD_ACCESS_TOKEN;
import static com.albaring.common.exception.common.ErrorType.EXPIRED_PERIOD_REFRESH_TOKEN;
import static com.albaring.common.exception.common.ErrorType.INVALID_ACCESS_TOKEN;
import static com.albaring.common.exception.common.ErrorType.INVALID_REFRESH_TOKEN;

import com.albaring.common.exception.auth.ExpiredPeriodJwtException;
import com.albaring.common.exception.auth.InvalidJwtException;
import com.albaring.core.authentication.domain.MemberTokens;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenProvider {

    public static final String EMPTY_SUBJECT = "";

    @Value("${security.jwt.token.secret-key}")
    private String secretKey;

    @Value("${security.jwt.token.access-token-expire-length}")
    private Long accessExpirationTime;

    @Value("${security.jwt.token.refresh-token-expire-length}")
    private Long refreshExpirationTime;

    public MemberTokens generateLoginToken(String subject) {
        final String refreshToken = createToken(EMPTY_SUBJECT, refreshExpirationTime);
        final String accessToken = createToken(subject, accessExpirationTime);
        return new MemberTokens(refreshToken, accessToken);
    }

    private String createToken(String subject, Long validityInMilliseconds) {
        final Date now = new Date();
        final Date validity = new Date(now.getTime() + validityInMilliseconds);

        return Jwts.builder()
            .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
            .setSubject(subject)
            .setIssuedAt(now)
            .setExpiration(validity)
            .signWith(SignatureAlgorithm.HS256, secretKey)
            .compact();
    }

    private void validateRefreshToken(final String refreshToken) {
        try {
            parseToken(refreshToken);
        } catch (final ExpiredJwtException e) {
            throw new ExpiredPeriodJwtException(EXPIRED_PERIOD_REFRESH_TOKEN);
        } catch (final JwtException | IllegalArgumentException e) {
            throw new InvalidJwtException(INVALID_REFRESH_TOKEN);
        }
    }

    private void validateAccessToken(String accessToken) {
        try {
            parseToken(accessToken);
        } catch (final ExpiredJwtException e) {
            throw new ExpiredPeriodJwtException(EXPIRED_PERIOD_ACCESS_TOKEN);
        } catch (final JwtException | IllegalArgumentException e) {
            throw new InvalidJwtException(INVALID_ACCESS_TOKEN);
        }
    }

    private Jws<Claims> parseToken(String token) {
        return Jwts.parser()
            .setSigningKey(secretKey)
            .parseClaimsJws(token);
    }

    public boolean isValidRefreshAndInvalidAccess(String refreshToken, String accessToken) {
        validateRefreshToken(refreshToken);
        try {
            validateAccessToken(accessToken);
        } catch (final ExpiredPeriodJwtException e) {
            return true;
        }
        return false;
    }

    public String regenerateAccessToken(String subject) {
        return createToken(subject, accessExpirationTime);
    }

    public boolean isValidRefreshAndValidAccess(String refreshToken, String accessToken) {
        try {
            validateRefreshToken(refreshToken);
            validateAccessToken(accessToken);
            return true;
        } catch (final JwtException e) {
            return false;
        }
    }
}