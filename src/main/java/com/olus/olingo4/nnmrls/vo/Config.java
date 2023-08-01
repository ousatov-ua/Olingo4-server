package com.olus.olingo4.nnmrls.vo;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.Key;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class Config extends GenericJson {
    @Key("top_app")
    private String topApp;
    @Key("oauth2_callback_url")
    private String oauthCallbackUrl;
    @Key("path_app")
    private String pathApp;
    @Key("service")
    private String service;
    @Key("path_auth_login")
    private String pathAuthLogin;

    public String getPathAppTop(){
        return topApp + pathApp;
    }
}
