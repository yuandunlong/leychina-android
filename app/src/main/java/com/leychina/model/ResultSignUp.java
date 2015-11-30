package com.leychina.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by yuandunlong on 11/18/15.
 */
public class ResultSignUp extends Result {
    @Expose
    @SerializedName("access_token")
    private String accessToken;
    @Expose
    private int expires;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public int getExpires() {
        return expires;
    }

    public void setExpires(int expires) {
        this.expires = expires;
    }
}
