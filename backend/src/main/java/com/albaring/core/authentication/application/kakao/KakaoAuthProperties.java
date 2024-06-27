package com.albaring.core.authentication.application.kakao;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties("kakao.auth")
public class KakaoAuthProperties {

    private String tokenUri;
    private String clientId;
    private String clientSecret;
    private String redirectUri;
}
