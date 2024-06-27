package com.albaring.common.config;

import com.albaring.core.authentication.application.kakao.KakaoAuthProperties;
import com.albaring.core.authentication.application.kakao.KakaoUserProperties;
import com.albaring.core.authentication.application.naver.NaverAuthProperties;
import com.albaring.core.authentication.application.naver.NaverUserProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(value =
    {
        KakaoAuthProperties.class, KakaoUserProperties.class,
        NaverAuthProperties.class, NaverUserProperties.class

    })
public class ConfigurationConfig {

}
