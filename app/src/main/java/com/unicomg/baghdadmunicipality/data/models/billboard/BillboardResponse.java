package com.unicomg.baghdadmunicipality.data.models.billboard;

import com.google.gson.annotations.SerializedName;

public class BillboardResponse {

    @SerializedName("status")
    String status ;
    @SerializedName("message")
    String message ;

    public BillboardResponse(String status, String message) {
        this.status = status;
        this.message = message;
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

    @Override
    public String toString() {
        return "BillboardResponse{" +
                "status='" + status + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
