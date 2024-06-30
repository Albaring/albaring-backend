package com.albaring.core.auth.application;

import static com.albaring.core.auth.fixture.KakaoMemberFixture.어피치;
import static com.albaring.core.auth.fixture.NaverMemberFixture.도레미;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.albaring.common.util.ApplicationMockTest;
import com.albaring.core.authentication.application.OauthProviders;
import com.albaring.core.authentication.application.TokenService;
import com.albaring.core.authentication.application.jwt.JwtTokenProvider;
import com.albaring.core.authentication.application.kakao.KakaoOauthProvider;
import com.albaring.core.authentication.application.kakao.dto.KakaoProfileResponse;
import com.albaring.core.authentication.application.naver.NaverOauthProvider;
import com.albaring.core.authentication.application.naver.dto.NaverProfileResponse;
import com.albaring.core.authentication.domain.MemberTokens;
import com.albaring.core.authentication.domain.RefreshToken;
import com.albaring.core.authentication.domain.RefreshTokenRepository;
import com.albaring.core.authentication.presentation.dto.OauthProviderCodeRequest;
import com.albaring.core.member.application.MemberService;
import com.albaring.core.member.domain.Member;
import com.albaring.core.member.domain.MemberStatus;
import com.albaring.core.member.domain.OAuthProviderType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.util.ReflectionTestUtils;

@DisplayName("Toke 서비스 Mock 테스트")
public class TokenServiceMockTest extends ApplicationMockTest {

    @InjectMocks
    TokenService tokenService;

    @Mock
    OauthProviders oauthProviders;

    @Mock
    JwtTokenProvider jwtTokenProvider;

    @Mock
    MemberService memberService;

    @Mock
    KakaoOauthProvider kakaoOauthProvider;

    @Mock
    NaverOauthProvider naverOauthProvider;

    @Mock
    RefreshTokenRepository refreshTokenRepository;

    @Nested
    class 카카오를_통해_토큰_발급 {

        @Test
        void 토큰_발급_성공() {
            // given
            when(oauthProviders.map("kakao")).thenReturn(kakaoOauthProvider);
            when(kakaoOauthProvider.getOAuthProviderType()).thenReturn(OAuthProviderType.KAKAO);
            when(kakaoOauthProvider.getUserProfile(어피치.인가_코드)).thenReturn(
                new KakaoProfileResponse(어피치.카카오_회원_번호));

            Member 어피치_회원_정보 = new Member(어피치.카카오_회원_번호.toString(), OAuthProviderType.KAKAO,
                MemberStatus.ACTIVE);
            ReflectionTestUtils.setField(어피치_회원_정보, "id", 1L);
            when(
                memberService.findOrCreateMember(어피치.카카오_회원_번호.toString(), OAuthProviderType.KAKAO))
                .thenReturn(어피치_회원_정보);

            when(jwtTokenProvider.generateLoginToken(어피치_회원_정보.getId().toString())).thenReturn(
                new MemberTokens("Access Token", "Refresh Token"));

            // when
            OauthProviderCodeRequest 카카오_인가_코드_요청_정보 = new OauthProviderCodeRequest(어피치.인가_코드);
            tokenService.generateToken("kakao", 카카오_인가_코드_요청_정보);

            // then
            verify(kakaoOauthProvider, times(1)).getUserProfile(카카오_인가_코드_요청_정보.getCode());
            verify(memberService, times(1)).findOrCreateMember(어피치.카카오_회원_번호.toString(),
                OAuthProviderType.KAKAO);
            verify(refreshTokenRepository, times(1)).save(any(RefreshToken.class));
            verify(jwtTokenProvider, times(1)).generateLoginToken(any());
        }
    }

    @Nested
    class 네이버를_통해_토큰_발급 {

        @Test
        void 토큰_발급_성공() {
            // given
            when(oauthProviders.map("naver")).thenReturn(naverOauthProvider);
            when(naverOauthProvider.getOAuthProviderType()).thenReturn(OAuthProviderType.NAVER);
            when(naverOauthProvider.getUserProfile(도레미.인가_코드)).thenReturn(
                new NaverProfileResponse(도레미.네이버_회원_번호));

            Member 도레미_회원_정보 = new Member(도레미.네이버_회원_번호.toString(), OAuthProviderType.NAVER,
                MemberStatus.ACTIVE);
            ReflectionTestUtils.setField(도레미_회원_정보, "id", 1L);
            when(
                memberService.findOrCreateMember(도레미.네이버_회원_번호.toString(), OAuthProviderType.NAVER))
                .thenReturn(도레미_회원_정보);

            when(jwtTokenProvider.generateLoginToken(도레미_회원_정보.getId().toString())).thenReturn(
                new MemberTokens("Access Token", "Refresh Token"));

            // when
            OauthProviderCodeRequest 네이버_인가_코드_요청_정보 = new OauthProviderCodeRequest(도레미.인가_코드);
            tokenService.generateToken("naver", 네이버_인가_코드_요청_정보);

            // then
            verify(naverOauthProvider, times(1)).getUserProfile(네이버_인가_코드_요청_정보.getCode());
            verify(memberService, times(1)).findOrCreateMember(도레미.네이버_회원_번호.toString(),
                OAuthProviderType.NAVER);
            verify(refreshTokenRepository, times(1)).save(any(RefreshToken.class));
            verify(jwtTokenProvider, times(1)).generateLoginToken(any());
        }
    }
}
