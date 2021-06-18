package com.unicomg.baghdadmunicipality.data.models.shops;

import com.google.gson.annotations.SerializedName;
import com.unicomg.baghdadmunicipality.data.models.serverViolations.ServerViolation;

import java.util.List;

public class ShopsResponse {

    @SerializedName("status")
    String status;
    @SerializedName("message")
    String message;
    @SerializedName("data")
    List<ShopModel> shopModels;


    public ShopsResponse() {
    }

    public ShopsResponse(String status, String message, List<ShopModel> shopModels) {
        this.status = status;
        this.message = message;
        this.shopModels = shopModels;
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

    public List<ShopModel> getShopModels() {
        return shopModels;
    }

    public void setShopModels(List<ShopModel> shopModels) {
        this.shopModels = shopModels;
    }

    @Override
    public String toString() {
        return "ShopsResponse{" +
                "status='" + status + '\'' +
                ", message='" + message + '\'' +
                ", shopModels=" + shopModels +
                '}';
    }
}
