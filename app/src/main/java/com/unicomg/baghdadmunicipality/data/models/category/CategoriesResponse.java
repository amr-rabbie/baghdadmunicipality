package com.unicomg.baghdadmunicipality.data.models.category;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CategoriesResponse {

    @SerializedName("status")
    String status;
    @SerializedName("message")
    String message;
    @SerializedName("data")
    List<Category> categories;


    public CategoriesResponse(String status, String message, List<Category> categories) {
        this.status = status;
        this.message = message;
        this.categories = categories;
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

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    @Override
    public String toString() {
        return "CategoriesResponse{" +
                "status='" + status + '\'' +
                ", message='" + message + '\'' +
                ", categories=" + categories +
                '}';
    }
}
