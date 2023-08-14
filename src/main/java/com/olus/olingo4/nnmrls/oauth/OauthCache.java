package com.olus.olingo4.nnmrls.oauth;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.Expiry;
import com.olus.olingo4.nnmrls.vo.Const;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * Cache for access keys
 *
 * @author Oleksii Usatov
 */
public class OauthCache {
    private static final OauthCache OAUTH_CACHE = new OauthCache(Const.getInstance().getConfig().getAccessKeyExpiration());
    public final Cache<String, Integer> cache;

    public OauthCache(long expiration) {
        cache = Caffeine.newBuilder().expireAfter(new Expiry<String, Integer>() {
            @Override
            public long expireAfterCreate(String key, Integer value, long currentTime) {
                return TimeUnit.SECONDS.toNanos(Objects.requireNonNullElse(Long.valueOf(value), expiration));
            }

            @Override
            public long expireAfterUpdate(String key, Integer value, long currentTime,
                                          long currentDuration) {
                return currentDuration;
            }

            @Override
            public long expireAfterRead(String key, Integer value, long currentTime,
                                        long currentDuration) {
                return currentDuration;
            }
        }).build();
    }

    public static OauthCache getInstance() {
        return OAUTH_CACHE;
    }

    public void putAccessKeyToCache(String accessKey, Integer expiresIn) {
        cache.put(accessKey, expiresIn);
    }

    public boolean exists(String accessKey) {
        return cache.getIfPresent(accessKey) != null;
    }
}
