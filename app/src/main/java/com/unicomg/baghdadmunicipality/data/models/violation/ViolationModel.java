package com.unicomg.baghdadmunicipality.data.models.violation;

import com.google.gson.annotations.SerializedName;

public class ViolationModel {

    @SerializedName("id")
    private  String id ;

    @SerializedName("shop_data_id")
    private  String shop_id ;


    @SerializedName("shop_data_name")
    private  String shop_data_name ;


    @SerializedName("category_id")
    private  String category_id ;

    @SerializedName("violation_id")
    private  String violation_id ;

    @SerializedName("action_id")
    private  String action_id ;

    @SerializedName("note")
    private  String note ;


    public ViolationModel() {
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getShop_id() {
        return shop_id;
    }

    public void setShop_id(String shop_id) {
        this.shop_id = shop_id;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public String getViolation_id() {
        return violation_id;
    }

    public void setViolation_id(String violation_id) {
        this.violation_id = violation_id;
    }

    public String getAction_id() {
        return action_id;
    }

    public void setAction_id(String action_id) {
        this.action_id = action_id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public ViolationModel(String id, String shop_id, String shop_data_name, String category_id, String violation_id, String action_id, String note) {
        this.id = id;
        this.shop_id = shop_id;
        this.shop_data_name = shop_data_name;
        this.category_id = category_id;
        this.violation_id = violation_id;
        this.action_id = action_id;
        this.note = note;
    }

    @Override
    public String toString() {
        return "ViolationModel{" +
                "id='" + id + '\'' +
                ", shop_id='" + shop_id + '\'' +
                ", shop_data_name='" + shop_data_name + '\'' +
                ", category_id='" + category_id + '\'' +
                ", violation_id='" + violation_id + '\'' +
                ", action_id='" + action_id + '\'' +
                ", note='" + note + '\'' +
                '}';
    }

    public String getShop_data_name() {
        return shop_data_name;
    }

    public void setShop_data_name(String shop_data_name) {
        this.shop_data_name = shop_data_name;
    }
}
