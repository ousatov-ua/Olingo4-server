package com.olus.olingo4.nnmrls.oauth;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.olus.olingo4.nnmrls.vo.Const;

import java.util.concurrent.TimeUnit;

/**
 * Cache for access keys
 *
 * @author Oleksii Usatov
 */
public class OauthCache {
    private static final OauthCache OAUTH_CACHE = new OauthCache(Const.getInstance().getConfig().getAccessKeyExpiration());
    public final Cache<String, String> cache;

    public OauthCache(long expiration) {
        cache = CacheBuilder.newBuilder()
                .expireAfterAccess(expiration, TimeUnit.SECONDS)
                .build();
    }

    public static OauthCache getInstance() {
        return OAUTH_CACHE;
    }

    public void putAccessKeyToCache(String accessKey) {
        cache.put(accessKey, "");
    }

    public boolean exists(String accessKey) {
        return cache.getIfPresent(accessKey) != null;
    }
}
