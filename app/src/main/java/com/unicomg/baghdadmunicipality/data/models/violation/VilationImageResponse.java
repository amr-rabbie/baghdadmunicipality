package com.unicomg.baghdadmunicipality.data.models.violation;

import com.google.gson.annotations.SerializedName;

public class VilationImageResponse {
    @SerializedName("status")
    String status ;
    @SerializedName("message")
    String message ;

    public VilationImageResponse(String status, String message) {
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
        return "ViolationResponse{" +
                "status='" + status + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
