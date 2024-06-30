package com.albaring.core.authentication.application;

import com.albaring.common.exception.BadRequestException;
import com.albaring.common.exception.UnauthorizedException;
import com.albaring.common.exception.common.ErrorCode;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class OauthProviders {

    private final List<OAuthProvider> providers;

    public OauthProviders(final List<OAuthProvider> providers) {
        this.providers = providers;
    }

    public OAuthProvider map(final String providerName) {
        return providers.stream()
            .filter(provider -> provider.is(providerName))
            .findFirst()
            .orElseThrow(() -> new BadRequestException(ErrorCode.NOT_SUPPORTED_OAUTH_SERVICE));
    }
}
