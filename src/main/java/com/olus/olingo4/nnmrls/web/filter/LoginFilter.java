package com.olus.olingo4.nnmrls.web.filter;

import com.olus.olingo4.nnmrls.util.Const;
import com.olus.olingo4.nnmrls.web.LoginState;
import com.olus.olingo4.nnmrls.web.LoginStateHolder;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Filter to perform application level login check
 */
@Slf4j
public class LoginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        final HttpServletRequest req = (HttpServletRequest) request;
        final HttpServletResponse resp = (HttpServletResponse) response;
        final HttpSession session = req.getSession();

        final LoginState loginState = LoginStateHolder.INSTANCE.getLoginState(req);

        log.debug("loginState={}", loginState);

        switch (loginState) {
            case NOT_LOGGED_IN:
                // - If not logged in yet

                // A user is requesting a URL related with a servlet.
                // And this filter is set for that servlet.
                // So,the following "currentUrl" is the URL related with that servlet.
                final String currentUrl = getRequestingUrl(req);// The URL user is

                // After OAuth 2 processing is finished, you can explicitly specify the URL to be redirected
                log.debug("Not logged in. Store REDIRECT_URL_AFTER_LOGIN in the session URL={}", currentUrl);

                // Store the URL(to be redirected after the application login processing) in the session
                session.setAttribute(Const.SESSION_KEY_REDIRECT_URL_AFTER_LOGIN, currentUrl);

                final String redirectUrl = Const.getConfig().getPathAuthLogin();
                log.debug("Not logged in. sendRedirect to {}", redirectUrl);
                resp.sendRedirect(redirectUrl);
                break;

            case LOGGED_IN:
                // - If already logged in

                log.debug("Already logged in,do nothing.");
                // just continue as it is.
                chain.doFilter(request, response);
                break;
        }

    }

    /**
     * Returns the current requesting url
     *
     * @param req {@link HttpServletRequest}
     * @return String
     */
    private String getRequestingUrl(final HttpServletRequest req) {

        final String scheme = req.getScheme();
        final int currentPort = req.getServerPort();
        final StringBuilder sb = new StringBuilder();

        sb.append(scheme);
        sb.append("://");
        sb.append(req.getServerName());

        if (currentPort != 80 && currentPort != 443) {
            sb.append(":");
            sb.append(currentPort);
        }
        sb.append(req.getRequestURI());
        if (req.getQueryString() != null && !req.getQueryString().isEmpty()) {
            sb.append("?");
            sb.append(req.getQueryString());
        }

        return sb.toString();
    }

    @Override
    public void destroy() {

        // Nothing to do
    }
}
