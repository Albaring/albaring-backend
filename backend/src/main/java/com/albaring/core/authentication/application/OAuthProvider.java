package com.albaring.core.authentication.application;

import com.albaring.core.member.domain.OAuthProviderType;

public interface OAuthProvider {

    OAuthProviderType getOAuthProviderType();

    boolean is(String providerType);

    OauthUserProfile getUserProfile(String code);

}
