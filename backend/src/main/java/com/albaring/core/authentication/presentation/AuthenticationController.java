package com.albaring.core.authentication.presentation;

import static org.springframework.http.HttpHeaders.SET_COOKIE;

import com.albaring.core.authentication.application.TokenService;
import com.albaring.core.authentication.domain.MemberTokens;
import com.albaring.core.authentication.presentation.dto.AccessTokenResponse;
import com.albaring.core.authentication.presentation.dto.KakaoCodeRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class AuthenticationController {

    public static final int COOKIE_AGE_SECONDS = 604800;

    private final TokenService tokenService;

    @PostMapping("/api/auth/kakao")
    public ResponseEntity<AccessTokenResponse> generateTokenFromKakao(
        @Valid @RequestBody KakaoCodeRequest request,
        final HttpServletResponse response) {
        MemberTokens memberTokens = tokenService.generateToken(request);

        final ResponseCookie cookie = ResponseCookie.from("refresh-token",
                memberTokens.getRefreshToken())
            .maxAge(COOKIE_AGE_SECONDS)
            .sameSite("None")
            .secure(true)
            .httpOnly(true)
            .path("/")
            .build();
        response.addHeader(SET_COOKIE, cookie.toString());
        return ResponseEntity.ok(AccessTokenResponse.of(memberTokens.getAccessToken()));
    }

}
