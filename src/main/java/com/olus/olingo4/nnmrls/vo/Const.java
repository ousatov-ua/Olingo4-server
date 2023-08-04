package com.olus.olingo4.nnmrls.vo;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;

@Slf4j
public class Const {

    private static final Const INSTANCE = new Const();
    @Getter
    private final Config config;

    public Const() {
        try (final InputStream is = Const.class.getResourceAsStream("/config.json");
        ) {
            config = new ObjectMapper().readValue(is, Config.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        log.info("Config is loaded={}", config);
    }

    public static Const getInstance() {
        return INSTANCE;
    }
}
