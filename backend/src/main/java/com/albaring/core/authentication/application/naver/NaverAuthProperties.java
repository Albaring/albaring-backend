package com.albaring.core.authentication.application.naver;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties("naver.auth")
public class NaverAuthProperties {

    private String tokenUri;
    private String clientId;
    private String clientSecret;
    private String redirectUri;
}
