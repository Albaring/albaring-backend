package com.albaring.core.authentication.application;

import com.albaring.core.authentication.application.jwt.JwtTokenProvider;
import com.albaring.core.authentication.domain.MemberTokens;
import com.albaring.core.authentication.domain.RefreshToken;
import com.albaring.core.authentication.domain.RefreshTokenRepository;
import com.albaring.core.authentication.presentation.dto.OauthProviderCodeRequest;
import com.albaring.core.member.application.MemberService;
import com.albaring.core.member.domain.Member;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class TokenService {

    private final RefreshTokenRepository refreshTokenRepository;
    private final MemberService memberService;
    private final JwtTokenProvider jwtTokenProvider;
    private final OauthProviders oauthProviders;

    @Transactional
    public MemberTokens generateToken(String providerName, OauthProviderCodeRequest request) {
        OAuthProvider oAuthProvider = oauthProviders.map(providerName);
        OauthUserProfile userProfile = oAuthProvider.getUserProfile(
            request.getCode());
        Member member = memberService.findOrCreateMember(userProfile.getSocialId(),
            oAuthProvider.getOAuthProviderType());

        MemberTokens memberTokens = jwtTokenProvider.generateLoginToken(member.getId().toString());
        RefreshToken savedRefreshToken = new RefreshToken(memberTokens.getRefreshToken(),
            member.getId());
        refreshTokenRepository.save(savedRefreshToken);
        return memberTokens;
    }

    public void removeRefreshToken(String refreshToken) {
        refreshTokenRepository.deleteById(refreshToken);
    }
}
