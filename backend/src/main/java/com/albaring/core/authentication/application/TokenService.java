package com.albaring.core.authentication.application;

import com.albaring.core.authentication.application.dto.KakaoProfileResponse;
import com.albaring.core.authentication.application.jwt.JwtTokenProvider;
import com.albaring.core.authentication.application.kakao.KakaoClient;
import com.albaring.core.authentication.domain.MemberTokens;
import com.albaring.core.authentication.domain.RefreshToken;
import com.albaring.core.authentication.domain.RefreshTokenRepository;
import com.albaring.core.authentication.presentation.dto.KakaoCodeRequest;
import com.albaring.core.member.application.MemberService;
import com.albaring.core.member.domain.Member;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TokenService {

    private final RefreshTokenRepository refreshTokenRepository;
    private final MemberService memberService;
    private final JwtTokenProvider jwtTokenProvider;
    private final KakaoClient kakaoClient;

    public MemberTokens generateToken(KakaoCodeRequest request) {
        KakaoProfileResponse kakaoProfileResponse = kakaoClient.requestKakaoProfile(
            request.getCode());
        Member member = memberService.findOrCreateMemberByKakaoId(kakaoProfileResponse.getId());

        MemberTokens memberTokens = jwtTokenProvider.generateLoginToken(member.getId().toString());
        RefreshToken savedRefreshToken = new RefreshToken(memberTokens.getRefreshToken(),
            member.getId());
        refreshTokenRepository.save(savedRefreshToken);
        return memberTokens;
    }
}
