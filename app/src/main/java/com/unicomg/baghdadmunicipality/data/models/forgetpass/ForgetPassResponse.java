package com.unicomg.baghdadmunicipality.data.models.forgetpass;

import com.google.gson.annotations.SerializedName;

public class ForgetPassResponse {

    @SerializedName("status")
    String status ;
    @SerializedName("message")
    String message ;

    public ForgetPassResponse(String status, String message) {
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
