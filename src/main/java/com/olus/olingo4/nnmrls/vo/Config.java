package com.olus.olingo4.nnmrls.vo;

import lombok.Data;

@Data
public class Config {
    private String tokenEndpoint;
    private String grantType;
    private String scope;
    private Integer accessKeyExpiration;
}
