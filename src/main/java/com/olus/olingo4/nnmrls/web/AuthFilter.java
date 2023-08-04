package com.olus.olingo4.nnmrls.web;

import com.olus.olingo4.nnmrls.oauth.OauthCache;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Filter that checks that user authenticated
 *
 * @author Oleksii Usatov
 */
public class AuthFilter implements Filter {

    private static final String BEARER = "Bearer";
    private static final String AUTHORIZATION = "Authorization";

    @Override
    public void init(FilterConfig filterConfig) {

        // Empty
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        var request = (HttpServletRequest) servletRequest;
        var response = (HttpServletResponse) servletResponse;
        var auth = request.getHeader(AUTHORIZATION);
        if (auth == null || !auth.contains(BEARER)) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
            return;
        }
        var accessToken = auth.substring(BEARER.length() + 1);
        if (!OauthCache.getInstance().exists(accessToken)) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
            return;
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

        // Empty
    }
}
