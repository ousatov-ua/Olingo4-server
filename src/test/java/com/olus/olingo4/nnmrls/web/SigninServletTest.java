package com.olus.olingo4.nnmrls.web;

import com.olus.olingo4.nnmrls.exception.AuthorizationException;
import com.olus.olingo4.nnmrls.oauth.OauthCache;
import com.olus.olingo4.nnmrls.util.FileUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static com.olus.olingo4.nnmrls.web.SigninServlet.APPLICATION_JSON_CT;
import static com.olus.olingo4.nnmrls.web.SigninServlet.CLIENT_ID_HEADER;
import static com.olus.olingo4.nnmrls.web.SigninServlet.CLIENT_SECRET_HEADER;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Unit test for {@link SigninServlet}
 *
 * @author Oleksii Usatov
 */
@ExtendWith(MockitoExtension.class)
class SigninServletTest {

    @Mock
    private HttpServletRequest httpServletRequest;
    @Mock
    private HttpServletResponse httpServletResponse;
    @Mock
    private ServletOutputStream outputStream;
    @Spy
    private SigninServlet signinServlet;

    @BeforeEach
    void beforeEach() {
        reset(outputStream, httpServletRequest, httpServletResponse, signinServlet);
        OauthCache.getInstance().cache.invalidateAll();
    }

    @Test
    void testNoClientCredentials() throws IOException {

        // Execute
        signinServlet.doPost(httpServletRequest, httpServletResponse);

        // Verify
        verify(httpServletResponse).sendError(HttpServletResponse.SC_BAD_REQUEST);
        verify(httpServletResponse, never()).setContentType(APPLICATION_JSON_CT);
        verify(httpServletResponse, never()).setCharacterEncoding(StandardCharsets.UTF_8.toString());
        assertFalse(OauthCache.getInstance().exists("token"));
    }

    @Test
    void testNoClientSecret() throws IOException {

        // Setup
        when(httpServletRequest.getHeader(CLIENT_ID_HEADER)).thenReturn("client_id");
        when(httpServletRequest.getHeader(CLIENT_SECRET_HEADER)).thenReturn("");

        // Execute
        signinServlet.doPost(httpServletRequest, httpServletResponse);

        // Verify
        verify(httpServletResponse).sendError(HttpServletResponse.SC_BAD_REQUEST);
        verify(httpServletResponse, never()).setContentType(APPLICATION_JSON_CT);
        verify(httpServletResponse, never()).setCharacterEncoding(StandardCharsets.UTF_8.toString());
        assertFalse(OauthCache.getInstance().exists("token"));
    }

    @Test
    void testNoClientId() throws IOException {

        // Setup
        when(httpServletRequest.getHeader(CLIENT_ID_HEADER)).thenReturn("");
        when(httpServletRequest.getHeader(CLIENT_SECRET_HEADER)).thenReturn("client_secret");

        // Execute
        signinServlet.doPost(httpServletRequest, httpServletResponse);

        // Verify
        verify(httpServletResponse).sendError(HttpServletResponse.SC_BAD_REQUEST);
        verify(httpServletResponse, never()).setContentType(APPLICATION_JSON_CT);
        verify(httpServletResponse, never()).setCharacterEncoding(StandardCharsets.UTF_8.toString());
        assertFalse(OauthCache.getInstance().exists("token"));
    }

    @Test
    void testAuthorizationException() throws IOException {

        // Setup
        when(httpServletRequest.getHeader(CLIENT_ID_HEADER)).thenReturn("client_id");
        when(httpServletRequest.getHeader(CLIENT_SECRET_HEADER)).thenReturn("client_secret");
        doThrow(new AuthorizationException("")).when(signinServlet).post(any(), any(), any());

        // Execute
        signinServlet.doPost(httpServletRequest, httpServletResponse);

        // Verify
        verify(httpServletResponse).sendError(HttpServletResponse.SC_BAD_REQUEST);
        verify(httpServletResponse, never()).setContentType(APPLICATION_JSON_CT);
        verify(httpServletResponse, never()).setCharacterEncoding(StandardCharsets.UTF_8.toString());
        assertFalse(OauthCache.getInstance().exists("token"));
    }

    @Test
    void testHappyPath() throws IOException {

        // Setup
        var response = FileUtil.getFileContent("json/jwt-token.json");
        when(httpServletRequest.getHeader(CLIENT_ID_HEADER)).thenReturn("client_id");
        when(httpServletRequest.getHeader(CLIENT_SECRET_HEADER)).thenReturn("client_secret");
        doReturn(FileUtil.getFileContent("json/jwt-token.json")).when(signinServlet).post(any(), any(), any());

        when(httpServletResponse.getOutputStream()).thenReturn(outputStream);

        // Execute
        signinServlet.doPost(httpServletRequest, httpServletResponse);

        // Verify
        verify(httpServletResponse).setContentType(APPLICATION_JSON_CT);
        verify(httpServletResponse).setCharacterEncoding(StandardCharsets.UTF_8.toString());
        verify(outputStream).println(response);
        assertTrue(OauthCache.getInstance().exists("token"));
    }
}
