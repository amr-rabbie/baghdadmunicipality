package com.unicomg.baghdadmunicipality.data.models.serverViolations;

import com.google.gson.annotations.SerializedName;

public class ServerViolation {
    @SerializedName("id")
    String id;
    @SerializedName("category_id")
    String category_id;
    @SerializedName("name")
    String name;
    @SerializedName("type")
    String type;
    @SerializedName("financial_value")
    String financial_value;

    public ServerViolation(String id, String category_id, String name, String type, String financial_value) {
        this.id = id;
        this.category_id = category_id;
        this.name = name;
        this.type = type;
        this.financial_value = financial_value;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFinancial_value() {
        return financial_value;
    }

    public void setFinancial_value(String financial_value) {
        this.financial_value = financial_value;
    }

    public ServerViolation() {
    }

    @Override
    public String toString() {
        return "ServerViolation{" +
                "id='" + id + '\'' +
                ", category_id='" + category_id + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", financial_value='" + financial_value + '\'' +
                '}';
    }
}
