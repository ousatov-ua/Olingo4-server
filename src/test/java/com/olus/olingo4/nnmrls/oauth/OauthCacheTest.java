package com.olus.olingo4.nnmrls.oauth;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class OauthCacheTest {

    private static final OauthCache OAUTH_CACHE = new OauthCache(2L);

    @BeforeEach
    void beforeEach() {
        OAUTH_CACHE.cache.invalidateAll();
    }

    @Test
    void testExpiration() throws InterruptedException {
        OAUTH_CACHE.putAccessKeyToCache("key", 5);
        TimeUnit.SECONDS.sleep(3L);
        assertTrue(OAUTH_CACHE.exists("key"));
    }

    @Test
    void testExpirationLess() throws InterruptedException {
        OAUTH_CACHE.putAccessKeyToCache("key", 2);
        TimeUnit.SECONDS.sleep(3L);
        assertFalse(OAUTH_CACHE.exists("key"));
    }
}