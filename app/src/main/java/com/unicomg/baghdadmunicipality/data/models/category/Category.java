package com.unicomg.baghdadmunicipality.data.models.category;

import com.google.gson.annotations.SerializedName;

public class Category {

    @SerializedName("id")
    String id ;
    @SerializedName("name")
    String name;
    @SerializedName("code")
    String code;

    public Category(String id, String name, String code) {
        this.id = id;
        this.name = name;
        this.code = code;
    }

    public Category() {

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
        return "Category{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
