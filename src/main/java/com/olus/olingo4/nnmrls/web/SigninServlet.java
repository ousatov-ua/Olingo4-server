package com.olus.olingo4.nnmrls.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.annotations.VisibleForTesting;
import com.olus.olingo4.nnmrls.exception.AuthorizationException;
import com.olus.olingo4.nnmrls.oauth.OauthCache;
import com.olus.olingo4.nnmrls.vo.Const;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Map;

import static com.olus.olingo4.nnmrls.util.Util.isEmpty;

/**
 * Servlet to get access token
 *
 * @author Oleksii Usatov
 */
@Slf4j
public class SigninServlet extends HttpServlet {
    public static final String APPLICATION_JSON_CT = "application/json";
    public static final String EXPIRES_IN_ATTR = "expires_in";
    @VisibleForTesting
    static final String CLIENT_ID_HEADER = "client_id";
    @VisibleForTesting
    static final String CLIENT_SECRET_HEADER = "client_secret";
    @VisibleForTesting
    static final String ACCESS_TOKEN_ATTR = "access_token";
    private static final HttpClient HTTP_CLIENT = new DefaultHttpClient();
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        var clientId = req.getHeader(CLIENT_ID_HEADER);
        var clientSecret = req.getHeader(CLIENT_SECRET_HEADER);
        if (isEmpty(clientId) || isEmpty(clientSecret)) {
            log.error("Client_id or client_secret is empty, clientId={}", clientId);
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        var clientCredentials = clientId + ":" + clientSecret;
        var applicationToken = "Basic " + new String(Base64.encodeBase64(clientCredentials.getBytes()));
        String result;
        try {
            var config = Const.getInstance().getConfig();
            result = post(config.getTokenEndpoint(),
                    Map.of("grant_type", config.getGrantType(),
                            "scope", config.getScope()),
                    Map.of(
                            "Authorization", applicationToken
                    ));
        } catch (Exception e) {
            log.error("Exception occurred during authorization", e);
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        var jwt = OBJECT_MAPPER.readValue(result, Map.class);
        var accessToken = (String) jwt.get(ACCESS_TOKEN_ATTR);
        var expiresIn = (Integer) jwt.get(EXPIRES_IN_ATTR);
        if (expiresIn == null) {
            log.error("Expires In is not specified");
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        OauthCache.getInstance().putAccessKeyToCache(accessToken, expiresIn);
        resp.setContentType(APPLICATION_JSON_CT);
        resp.setCharacterEncoding(StandardCharsets.UTF_8.toString());
        try (var out = resp.getOutputStream()) {
            out.println(result);
        }
    }

    /**
     * Execute POST request
     *
     * @param url            url
     * @param formParameters form parameters
     * @param headers        headers
     * @return body of response
     * @throws IOException exception
     */
    @VisibleForTesting
    String post(String url, Map<String, String> formParameters, Map<String, String> headers) throws IOException {
        var request = new HttpPost(url);
        var nvpList = new ArrayList<NameValuePair>();
        formParameters.forEach((key, value) -> nvpList.add(new BasicNameValuePair(key, value)));
        headers.forEach(request::addHeader);
        request.setEntity(new UrlEncodedFormEntity(nvpList));
        return execute(request);
    }

    /**
     * Execute {@link HttpRequestBase}
     *
     * @param request {@link HttpRequestBase}
     * @return body of response
     * @throws IOException exception
     */
    @VisibleForTesting
    String execute(HttpRequestBase request) throws IOException {
        var response = HTTP_CLIENT.execute(request);
        var entity = response.getEntity();
        var body = EntityUtils.toString(entity);
        if (response.getStatusLine().getStatusCode() != HttpServletResponse.SC_OK) {
            var statusCode = response.getStatusLine().getStatusCode();
            log.error("Authorization error. Expected 200 but got {}, with body {}", statusCode, body);
            throw new AuthorizationException("Expected 200 but got " + statusCode + ", with body " + body);
        }
        return body;
    }
}