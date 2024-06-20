package com.albaring.core.auth.util;


import com.albaring.core.auth.fixture.KakaoMemberFixture;
import com.albaring.core.authentication.application.dto.KakaoAccessTokenResponse;
import com.albaring.core.authentication.application.dto.KakaoProfileResponse;
import com.albaring.core.authentication.application.jwt.BearerAuthorizationExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Profile("test")
@RestController
public class FakeKakaoController {

    @Autowired
    BearerAuthorizationExtractor bearerAuthorizationExtractor;

    @PostMapping(value = "/oauth/token", consumes = "application/x-www-form-urlencoded")
    public ResponseEntity<KakaoAccessTokenResponse> createToken(
            @RequestParam("code") String code,
            @RequestParam("client_id") String clientId,
            @RequestParam("redirect_uri") String redirectUri,
            @RequestParam("grant_type") String grantType) {
        KakaoAccessTokenResponse response = new KakaoAccessTokenResponse(
                "bearer",
                KakaoMemberFixture.findTokenByCode(code)
        );
        return ResponseEntity.ok().body(response);
    }

    @GetMapping(value = "/v2/user/me")
    public ResponseEntity<KakaoProfileResponse> findProfile(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION) String accessToken,
            @RequestHeader(value = HttpHeaders.CONTENT_TYPE,
                    defaultValue = "application/x-www-form-urlencoded;charset=utf-8") String contentType) {
        KakaoProfileResponse profileResponse = new KakaoProfileResponse(
                KakaoMemberFixture.findIdByToken(bearerAuthorizationExtractor.extractAccessToken(accessToken))
        );
        return ResponseEntity.ok(profileResponse);
    }
}
