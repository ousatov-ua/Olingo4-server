package com.olus.olingo4.nnmrls.web;

import com.olus.olingo4.nnmrls.util.Const;

/**
 * OAuth2 callabck servlet<br>
 * <br>
 * The servlet that receives the OAuth 2 callback. <br>
 * It returns the URL of OAuth 2 and implements persistence of refreshToken.
 */
public class OAuthCallbackServlet extends org.riversun.oauth2.google.OAuthCallbackServlet {

    @Override
    protected String getAuthRedirectUrl() {
        return Const.getConfig().getOauthCallbackUrl();
    }

    @Override
    protected void saveRefreshTokenFor(String userId, String refreshToken) {

        // Write logic to save(persist) refresh_token for each user.
        // If not overridden it will be stored in memory
        super.saveRefreshTokenFor(userId, refreshToken);
    }

    @Override
    protected String loadRefreshTokenFor(String userId) {

        // Write logic to load refresh_token for each user
        // If not overridden it will be stored in memory
        return super.loadRefreshTokenFor(userId);
    }
}
