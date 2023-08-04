package com.olus.olingo4.nnmrls.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.olus.olingo4.nnmrls.oauth.OauthCache;
import com.olus.olingo4.nnmrls.vo.Const;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Servlet to get access token
 *
 * @author Oleksii Usatov
 */
@Slf4j
public class SigninServlet extends HttpServlet {
    private static final String CLIENT_ID = "client_id";
    private static final String CLIENT_SECRET = "client_secret";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        var clientId = req.getHeader(CLIENT_ID);
        var clientSecret = req.getHeader(CLIENT_SECRET);
        if (clientId == null || clientSecret == null) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        String applicationToken = clientId + ":" + clientSecret;
        applicationToken = "Basic " + new String(Base64.encodeBase64(applicationToken.getBytes()));
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
            log.error("Exception occurred", e);
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        var jwt = new ObjectMapper().readValue(result, Map.class);

        String accessToken = (String) jwt.get("access_token");
        OauthCache.getInstance().putAccessKeyToCache(accessToken);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getOutputStream().println(result);
        resp.getOutputStream().close();
    }

    private static String post(String url, Map<String, String> formParameters, Map<String, String> headers) throws IOException {
        HttpPost request = new HttpPost(url);
        List<NameValuePair> nvps = new ArrayList<>();
        formParameters.forEach((key, value) -> nvps.add(new BasicNameValuePair(key, value)));
        headers.forEach(request::addHeader);
        request.setEntity(new UrlEncodedFormEntity(nvps));
        return execute(request);
    }

    private static String execute(HttpRequestBase request) throws IOException {
        HttpClient httpClient = new DefaultHttpClient();
        HttpResponse response = httpClient.execute(request);
        HttpEntity entity = response.getEntity();
        String body = EntityUtils.toString(entity);
        if (response.getStatusLine().getStatusCode() != HttpServletResponse.SC_OK) {
            throw new RuntimeException("Expected 200 but got " + response.getStatusLine().getStatusCode() + ", with body " + body);
        }
        return body;
    }
}