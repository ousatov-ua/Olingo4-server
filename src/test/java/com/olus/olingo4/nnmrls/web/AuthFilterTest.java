package com.olus.olingo4.nnmrls.web;

import com.olus.olingo4.nnmrls.oauth.OauthCache;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.olus.olingo4.nnmrls.web.AuthFilter.AUTHORIZATION;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Unit tests for {@link AuthFilter}
 *
 * @author Oleksii Usatov
 */
@ExtendWith(MockitoExtension.class)
class AuthFilterTest {

    @Mock
    private HttpServletRequest servletRequest;
    @Mock
    private HttpServletResponse servletResponse;
    @Mock
    private FilterChain filterChain;
    @Spy
    private AuthFilter authFilter;

    @BeforeEach
    void beforeEach() {
        reset(servletRequest, servletResponse, filterChain);
        OauthCache.getInstance().cache.invalidateAll();
    }

    @Test
    void testNoHeaderForbidden() throws ServletException, IOException {

        // Execute
        authFilter.doFilter(servletRequest, servletResponse, filterChain);

        // Verify
        verify(servletResponse).sendError(HttpServletResponse.SC_FORBIDDEN);
        verify(filterChain, never()).doFilter(servletRequest, servletResponse);
    }

    @Test
    void testNoBearerForbidden() throws ServletException, IOException {

        // Setup
        when(servletRequest.getHeader(AUTHORIZATION)).thenReturn("");

        // Execute
        authFilter.doFilter(servletRequest, servletResponse, filterChain);

        // Verify
        verify(servletResponse).sendError(HttpServletResponse.SC_FORBIDDEN);
        verify(filterChain, never()).doFilter(servletRequest, servletResponse);
    }

    @Test
    void testBearerEmptyForbidden() throws ServletException, IOException {

        // Setup
        when(servletRequest.getHeader(AUTHORIZATION)).thenReturn("Bearer");

        // Execute
        authFilter.doFilter(servletRequest, servletResponse, filterChain);

        // Verify
        verify(servletResponse).sendError(HttpServletResponse.SC_FORBIDDEN);
        verify(filterChain, never()).doFilter(servletRequest, servletResponse);
    }

    @Test
    void testBearerWrongForbidden() throws ServletException, IOException {

        // Setup
        when(servletRequest.getHeader(AUTHORIZATION)).thenReturn("Bearer r");

        // Execute
        authFilter.doFilter(servletRequest, servletResponse, filterChain);

        // Verify
        verify(servletResponse).sendError(HttpServletResponse.SC_FORBIDDEN);
        verify(filterChain, never()).doFilter(servletRequest, servletResponse);
    }

    @Test
    void testHappyPath() throws ServletException, IOException {

        // Setup
        OauthCache.getInstance().putAccessKeyToCache("token", 1000);
        when(servletRequest.getHeader(AUTHORIZATION)).thenReturn("Bearer token");

        // Execute
        authFilter.doFilter(servletRequest, servletResponse, filterChain);

        // Verify
        verify(servletResponse, never()).sendError(HttpServletResponse.SC_FORBIDDEN);
        verify(filterChain).doFilter(servletRequest, servletResponse);
    }
}
