package com.albaring.core.member.domain;

import java.util.Arrays;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum OAuthProviderType {
    NORMAL,
    KAKAO,
    NAVER;

    public static OAuthProviderType of(String providerType) {
        return Arrays.stream(values())
            .filter(value -> value.name().equalsIgnoreCase(providerType))
            .findFirst()
            .orElse(null);
    }
}
