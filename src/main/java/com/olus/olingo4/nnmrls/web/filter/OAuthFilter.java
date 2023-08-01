package com.olus.olingo4.nnmrls.web.filter;

import com.olus.olingo4.nnmrls.util.Const;

import java.util.Arrays;
import java.util.List;

/**
 * OAuth Filter
 */
public class OAuthFilter extends org.riversun.oauth2.google.OAuthFilter {

    @Override
    protected String getAuthRedirectUrl() {
        return Const.getConfig().getOauthCallbackUrl();
    }

    @Override
    protected boolean isAuthenticateEverytime() {

        /* If true, execute OAuth2-flow every time
        If false, once access_token have been retrieved from OAuth2-flow
        that you will not need to get it unless you revoke it. */
        return false;
    }

    // Return OAuth2 scope you want to be granted to by users
    @Override
    protected List<String> getScopes() {

        final String OAUTH2_SCOPE_MAIL = "email";
        final String OAUTH2_SCOPE_USERINFO_PROFILE = "profile";

        return Arrays.asList(OAUTH2_SCOPE_MAIL, OAUTH2_SCOPE_USERINFO_PROFILE);
    }

}
