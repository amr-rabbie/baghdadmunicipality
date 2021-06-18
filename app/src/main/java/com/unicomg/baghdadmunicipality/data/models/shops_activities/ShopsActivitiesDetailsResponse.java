package com.unicomg.baghdadmunicipality.data.models.shops_activities;

import com.google.gson.annotations.SerializedName;

public class ShopsActivitiesDetailsResponse {

    @SerializedName("id")
    private  String id ;
    @SerializedName("name")
    private  String name ;
    @SerializedName("code")
    private  String code ;


    public ShopsActivitiesDetailsResponse() {
    }

    public ShopsActivitiesDetailsResponse(String id, String name, String code) {
        this.id = id;
        this.name = name;
        this.code = code;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "ShopsActivitiesDetailsResponse{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
