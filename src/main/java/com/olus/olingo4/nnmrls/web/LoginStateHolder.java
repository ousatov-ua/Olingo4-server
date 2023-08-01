package com.olus.olingo4.nnmrls.web;

import com.olus.olingo4.nnmrls.util.Const;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Set/Get login state from HttpSession
 */
public class LoginStateHolder {

    public final static LoginStateHolder INSTANCE = new LoginStateHolder();

    private LoginStateHolder() {

    }

    /**
     * Returns login state of
     *
     * @param req {@link HttpServletRequest}
     * @return {@link LoginState}
     */
    public LoginState getLoginState(HttpServletRequest req) {

        final HttpSession session = req.getSession();

        LoginState loginState = (LoginState) session.getAttribute(Const.SESSION_KEY_APP_LOGIN_STATE);
        if (loginState == null) {
            loginState = LoginState.NOT_LOGGED_IN;
        }

        return loginState;
    }

    public void setLoginState(HttpServletRequest req, LoginState loginState) {
        final HttpSession session = req.getSession();
        session.setAttribute(Const.SESSION_KEY_APP_LOGIN_STATE, loginState);
    }
}
