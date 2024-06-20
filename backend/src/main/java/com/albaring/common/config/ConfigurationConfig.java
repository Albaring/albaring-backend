package com.albaring.common.config;

import com.albaring.core.authentication.application.kakao.KakaoAuthProperties;
import com.albaring.core.authentication.application.kakao.KakaoUserProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(value = {KakaoAuthProperties.class, KakaoUserProperties.class})
public class ConfigurationConfig {

}
