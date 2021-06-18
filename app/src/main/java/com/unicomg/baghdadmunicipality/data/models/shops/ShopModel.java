package com.unicomg.baghdadmunicipality.data.models.shops;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class ShopModel {

    @SerializedName("id")
    private  String shop_id ;
    @SerializedName("owner_name")
    private  String owner_name ;
    @SerializedName("type")
    private  String type ;
    @SerializedName("shop_activity_id")
    private  String shop_activity_id ;
    @SerializedName("width")
    private  String width ;
    @SerializedName("length")
    private  String length ;
    @SerializedName("employee_number")
    private  String employee_number ;
    @SerializedName("floor_number")
    private  String floor_number ;
    @SerializedName("area")
    private  String area ;
    @SerializedName("street")
    private  String street ;
    @SerializedName("alley")
    private  String alley ;
    @SerializedName("locality")
    private  String locality ;
    @SerializedName("locality_number")
    private  String locality_number ;
    @SerializedName("license_number")
    private  String license_number ;
    @SerializedName("license_type")
    private String license_type ;
    @SerializedName("license_date")
    private String license_date ;
    @SerializedName("license_end_date")
    private String license_end_date ;
    @SerializedName("billboard_name")
    private String billboard_name ;
    @SerializedName("billboard_type")
    private String billboard_type ;
    @SerializedName("billboard_width")
    private  String billboard_width ;
    @SerializedName("billboard_length")
    private  String billboard_length ;
    @SerializedName("billboard_height")
    private  String billboard_height ;
    @SerializedName("billboard_font_type")
    private  String billboard_font_type ;
    @SerializedName("longitude")
    private  String longitude ;
    @SerializedName("latitude")
    private  String latitude ;
    @SerializedName("send")
    private  String send ;
    @SerializedName("notes")
    private  String shop_notes ;


    public ShopModel() {
    }

    public ShopModel(String shop_id, String owner_name, String type, String shop_activity_id, String width, String length, String employee_number, String floor_number, String area, String street, String alley, String locality, String locality_number, String license_number, String license_type, String license_date, String license_end_date, String billboard_name, String billboard_type, String billboard_width, String billboard_length,
                     String billboard_height, String billboard_font_type, String longitude, String latitude, String send, String shop_notes) {
        this.shop_id = shop_id;
        this.owner_name = owner_name;
        this.type = type;
        this.shop_activity_id = shop_activity_id;
        this.width = width;
        this.length = length;
        this.employee_number = employee_number;
        this.floor_number = floor_number;
        this.area = area;
        this.street = street;
        this.alley = alley;
        this.locality = locality;
        this.locality_number = locality_number;
        this.license_number = license_number;
        this.license_type = license_type;
        this.license_date = license_date;
        this.license_end_date = license_end_date;
        this.billboard_name = billboard_name;
        this.billboard_type = billboard_type;
        this.billboard_width = billboard_width;
        this.billboard_length = billboard_length;
        this.billboard_height = billboard_height;
        this.billboard_font_type = billboard_font_type;
        this.longitude = longitude;
        this.latitude = latitude;
        this.send = send;
        this.shop_notes = shop_notes;
    }

    public String getShop_id() {
        return shop_id;
    }

    public void setShop_id(String shop_id) {
        this.shop_id = shop_id;
    }

    public String getOwner_name() {
        return owner_name;
    }

    public void setOwner_name(String owner_name) {
        this.owner_name = owner_name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getShop_activity_id() {
        return shop_activity_id;
    }

    public void setShop_activity_id(String shop_activity_id) {
        this.shop_activity_id = shop_activity_id;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getEmployee_number() {
        return employee_number;
    }

    public void setEmployee_number(String employee_number) {
        this.employee_number = employee_number;
    }

    public String getFloor_number() {
        return floor_number;
    }

    public void setFloor_number(String floor_number) {
        this.floor_number = floor_number;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getAlley() {
        return alley;
    }

    public void setAlley(String alley) {
        this.alley = alley;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getLocality_number() {
        return locality_number;
    }

    public void setLocality_number(String locality_number) {
        this.locality_number = locality_number;
    }

    public String getLicense_number() {
        return license_number;
    }

    public void setLicense_number(String license_number) {
        this.license_number = license_number;
    }

    public String getLicense_type() {
        return license_type;
    }

    public void setLicense_type(String license_type) {
        this.license_type = license_type;
    }

    public String getLicense_date() {
        return license_date;
    }

    public void setLicense_date(String license_date) {
        this.license_date = license_date;
    }

    public String getLicense_end_date() {
        return license_end_date;
    }

    public void setLicense_end_date(String license_end_date) {
        this.license_end_date = license_end_date;
    }

    public String getBillboard_name() {
        return billboard_name;
    }

    public void setBillboard_name(String billboard_name) {
        this.billboard_name = billboard_name;
    }

    public String getBillboard_type() {
        return billboard_type;
    }

    public void setBillboard_type(String billboard_type) {
        this.billboard_type = billboard_type;
    }

    public String getBillboard_width() {
        return billboard_width;
    }

    public void setBillboard_width(String billboard_width) {
        this.billboard_width = billboard_width;
    }

    public String getBillboard_length() {
        return billboard_length;
    }

    public void setBillboard_length(String billboard_length) {
        this.billboard_length = billboard_length;
    }

    public String getBillboard_height() {
        return billboard_height;
    }

    public void setBillboard_height(String billboard_height) {
        this.billboard_height = billboard_height;
    }

    public String getBillboard_font_type() {
        return billboard_font_type;
    }

    public void setBillboard_font_type(String billboard_font_type) {
        this.billboard_font_type = billboard_font_type;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getSend() {
        return send;
    }

    public void setSend(String send) {
        this.send = send;
    }

    public String getShop_notes() {
        return shop_notes;
    }

    public void setShop_notes(String shop_notes) {
        this.shop_notes = shop_notes;
    }

    @Override
    public String toString() {
        return "ShopModel{" +
                "shop_id='" + shop_id + '\'' +
                ", owner_name='" + owner_name + '\'' +
                ", type='" + type + '\'' +
                ", shop_activity_id='" + shop_activity_id + '\'' +
                ", width='" + width + '\'' +
                ", length='" + length + '\'' +
                ", employee_number='" + employee_number + '\'' +
                ", floor_number='" + floor_number + '\'' +
                ", area='" + area + '\'' +
                ", street='" + street + '\'' +
                ", alley='" + alley + '\'' +
                ", locality='" + locality + '\'' +
                ", locality_number='" + locality_number + '\'' +
                ", license_number='" + license_number + '\'' +
                ", license_type='" + license_type + '\'' +
                ", license_date='" + license_date + '\'' +
                ", license_end_date='" + license_end_date + '\'' +
                ", billboard_name='" + billboard_name + '\'' +
                ", billboard_type='" + billboard_type + '\'' +
                ", billboard_width='" + billboard_width + '\'' +
                ", billboard_length='" + billboard_length + '\'' +
                ", billboard_height='" + billboard_height + '\'' +
                ", billboard_font_type='" + billboard_font_type + '\'' +
                ", longitude='" + longitude + '\'' +
                ", latitude='" + latitude + '\'' +
                ", send='" + send + '\'' +
                ", shop_notes='" + shop_notes + '\'' +
                '}';
    }
}
