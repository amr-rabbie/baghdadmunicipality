package com.unicomg.baghdadmunicipality.data.models.Login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AccessTokenModel {

    @SerializedName("access_token")
    String access_token;
    @SerializedName("token_type")
    String token_type;
    @SerializedName("expires_at")
    String expires_at;
    @SerializedName("status")
    String status;
    @SerializedName("message")
    String message;


    public AccessTokenModel() {
    }


    public AccessTokenModel(String access_token, String token_type, String expires_at, String status, String message) {
        this.access_token = access_token;
        this.token_type = token_type;
        this.expires_at = expires_at;
        this.status = status;
        this.message = message;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getToken_type() {
        return token_type;
    }

    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }

    public String getExpires_at() {
        return expires_at;
    }

    public void setExpires_at(String expires_at) {
        this.expires_at = expires_at;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
