package com.olus.olingo4.nnmrls.util;

import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.olus.olingo4.nnmrls.vo.Config;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Constants
 */
@Slf4j
public class Const {

    static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

    private static Config config;
    public static final String SESSION_KEY_APP_LOGIN_STATE = "SESSION_KEY_APP_LOGIN_STATE";
    public static final String SESSION_KEY_REDIRECT_URL_AFTER_LOGIN = "SESSION_KEY_REDIRECT_URL_AFTER_LOGIN";

    public static Config getConfig() {
        if(config == null){
            final InputStream is = Const.class.getResourceAsStream("/config.json");
            try {
                config = JSON_FACTORY.fromReader(new InputStreamReader(is), Config.class);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            log.info("Config is loaded={}", config);
        }
        return config;
    }
}
