package com.olus.olingo4.nnmrls.web;

import com.olus.olingo4.nnmrls.util.Const;
import lombok.extern.slf4j.Slf4j;
import org.riversun.oauth2.google.OAuthSession;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Logout servlet
 */
@Slf4j
public class LogoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        final LoginState loginState = LoginStateHolder.INSTANCE.getLoginState(req);
        log.debug("loginState={}", loginState);

        switch (loginState) {
            case NOT_LOGGED_IN:
                // - When the user is not logged in to "MyApp" yet,
                // but user intentionally call "/logout".
                log.debug("Not logged int. User intentionally call 'logout'");
                break;
            case LOGGED_IN:
                // - If the user has already been logged-in

                log.debug("Already logged in. Do change loginState to {}", LoginState.NOT_LOGGED_IN);

                // Set login state to not-logged-in
                LoginStateHolder.INSTANCE.setLoginState(req, LoginState.NOT_LOGGED_IN);

                // Clear the OAuth2 state(oauth-passed state)
                // so that the user will need to do OAuth-flow again the next time the user request "MyApp"
                OAuthSession.getInstance().clearOAuth2State(req);
                break;
        }
        final String redirectUrl = "https://www.google.com/accounts/Logout?continue=https://appengine.google.com/_ah/logout?continue=" + Const.getConfig().getTopApp();
        log.debug("sendRedirect to google logout {}", redirectUrl);
        HttpSession session = req.getSession();
        session.invalidate();
        resp.sendRedirect(redirectUrl);
    }
}
