package com.unicomg.baghdadmunicipality.data.models.violation;

import com.google.gson.annotations.SerializedName;

public class ViolationResponse {

    @SerializedName("status")
    String status;
    @SerializedName("message")
    String message;
    @SerializedName("id")
    String id;

    public ViolationResponse(String status, String message, String id) {
        this.status = status;
        this.message = message;
        this.id = id;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
