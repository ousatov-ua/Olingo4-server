package com.olus.olingo4.nnmrls.web;

import com.olus.olingo4.nnmrls.util.Const;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


/**
 * Servlet for OAuth-front<br>
 * (Actual OAuth-flow is done by OAuthFilter)
 */
@Slf4j
public class AuthServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        final LoginState loginState = LoginStateHolder.INSTANCE.getLoginState(req);
        log.debug("loginState={}", loginState);
        final HttpSession session = req.getSession();
        switch (loginState) {
            case NOT_LOGGED_IN:
                log.debug("Not logged in. Do change loginState to {}" +
                        " because this servlet is called after successfully passing OAuthFilter.", LoginState.LOGGED_IN);

                LoginStateHolder.INSTANCE.setLoginState(req, LoginState.LOGGED_IN);

                String redirectUrlAfterLogin = (String) session.getAttribute(Const.SESSION_KEY_REDIRECT_URL_AFTER_LOGIN);

                log.debug("Just logged in. Load REDIRECT_URL_AFTER_LOGIN from session URL={}", redirectUrlAfterLogin);

                if (redirectUrlAfterLogin == null) {

                    // If user already logged in to "Google" and
                    // intentionally requested "/auth" directly.

                    redirectUrlAfterLogin = Const.getConfig().getPathAppTop();

                    log.debug("Just logged in. Set redirectUrlAfterLogin to default path {}"
                                    + "Maybe user already logged in to 'Google' and intentionally requested '/auth' directly.",
                            redirectUrlAfterLogin);

                }

                // Clear REDIRECT_URL_AFTER_LOGIN from the session
                session.setAttribute(Const.SESSION_KEY_REDIRECT_URL_AFTER_LOGIN, null);

                log.debug("Just logged in. SendRedirect to {}", redirectUrlAfterLogin);

                resp.sendRedirect(redirectUrlAfterLogin);
                break;
            case LOGGED_IN:
                final String redirectUrl = Const.getConfig().getPathAppTop();
                log.debug("Already logged in. SendRedirect to {}", redirectUrl);
                resp.sendRedirect(redirectUrl);
                break;
        }
    }
}
