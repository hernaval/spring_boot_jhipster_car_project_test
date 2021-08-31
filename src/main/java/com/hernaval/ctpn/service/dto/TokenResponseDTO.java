package com.hernaval.ctpn.service.dto;

import java.io.Serializable;

public class TokenResponseDTO implements Serializable {

    private String accessToken;
    private long expiredIn;
    private String owner;

    public TokenResponseDTO() {}

    public TokenResponseDTO(String accessToken, long expiredIn, String owner) {
        this.accessToken = accessToken;
        this.expiredIn = expiredIn;
        this.owner = owner;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public long getExpiredIn() {
        return expiredIn;
    }

    public void setExpiredIn(long expiredIn) {
        this.expiredIn = expiredIn;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
}
