package com.albaring.core.auth.application;

import com.albaring.common.util.ApplicationTest;
import com.albaring.core.authentication.application.TokenService;
import com.albaring.core.authentication.application.jwt.JwtTokenProvider;
import com.albaring.core.authentication.application.kakao.KakaoOauthProvider;
import com.albaring.core.authentication.domain.MemberTokens;
import com.albaring.core.authentication.domain.RefreshTokenRepository;
import com.albaring.core.authentication.presentation.dto.OauthProviderCodeRequest;
import com.albaring.core.member.application.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static com.albaring.core.auth.fixture.KakaoMemberFixture.라이언;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Token 서비스 테스트")
public class TokenServiceTest extends ApplicationTest {

    @Autowired
    TokenService tokenService;

    @Autowired
    RefreshTokenRepository refreshTokenRepository;

    @Autowired
    MemberService memberService;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Autowired
    KakaoOauthProvider kakaoOauthProvider;

    @Nested
    class 카카오를_통해_토큰_발급 {

        @Test
        void 토큰_발급_성공() {
            // given
            OauthProviderCodeRequest 카카오_인가_코드_정보 = new OauthProviderCodeRequest(라이언.인가_코드);

            // when
            MemberTokens 발급된_회원_토큰_정보 = tokenService.generateToken("kakao", 카카오_인가_코드_정보);

            // then
            assertThat(발급된_회원_토큰_정보.getAccessToken()).isNotBlank();
            assertThat(발급된_회원_토큰_정보.getRefreshToken()).isNotBlank();
        }
    }
}
