package com.albaring.core.authentication.application.naver;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties("naver.user")
public class NaverUserProperties {

    private String profileUri;
}
