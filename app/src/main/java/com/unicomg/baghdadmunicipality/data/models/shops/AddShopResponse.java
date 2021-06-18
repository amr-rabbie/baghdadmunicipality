package com.unicomg.baghdadmunicipality.data.models.shops;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AddShopResponse {

    @SerializedName("status")
    String status ;
    @SerializedName("message")
    String message ;

    public AddShopResponse() {


    }


    public AddShopResponse(String status, String message) {
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
        return "AddShopResponse{" +
                "status='" + status + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
