package com.unicomg.baghdadmunicipality.data.models.shops_activities;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ShopsActivitiesResponse {

    @SerializedName("status")
    String status;

    @SerializedName("message")
    String message;

    @SerializedName("data")
    List<ShopsActivitiesDetailsResponse> data;

    public ShopsActivitiesResponse() {
    }

    public ShopsActivitiesResponse(String status, String message, List<ShopsActivitiesDetailsResponse> data) {
        this.status = status;
        this.message = message;
        this.data = data;
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

    public List<ShopsActivitiesDetailsResponse> getData() {
        return data;
    }

    public void setData(List<ShopsActivitiesDetailsResponse> data) {
        this.data = data;
    }
}
