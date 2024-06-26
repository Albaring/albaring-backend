package com.albaring.core.authentication.application.kakao.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class KakaoAccessTokenRequest {

    private String grantType;

    private String code;

    private String clientId;
    
    private String clientSecret;

    private String redirectUri;

}
