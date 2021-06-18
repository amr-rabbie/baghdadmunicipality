package com.unicomg.baghdadmunicipality.data.models.billboard;

import com.google.gson.annotations.SerializedName;

public class BillboardModel {

    @SerializedName("id")
    private  String id ;
    @SerializedName("owner_name")
    private  String owner_name ;
    @SerializedName("billboard_name")
    private  String billboard_name ;
    @SerializedName("billboard_type")
    private  String billboard_type ;
    @SerializedName("width")
    private  String width ;
    @SerializedName("length")
    private  String length ;
    @SerializedName("height")
    private  String height ;
    @SerializedName("font_language")
    private  String font_language ;
    @SerializedName("area")
    private  String area ;
    @SerializedName("locality")
    private  String locality ;
    @SerializedName("alley")
    private  String ailley ;
    @SerializedName("street")
    private  String street ;
    @SerializedName("building_number")
    private  String bulding_number ;
    @SerializedName("billboard_license")
    private  String billboard_license ;
    @SerializedName("billboard_license_number")
    private  String billboard_license_number ;
    @SerializedName("license_date")
    private  String license_date ;
    @SerializedName("license_end_date")
    private  String license_end_date ;
    @SerializedName("latitude ")
    private  String latitude  ;
    @SerializedName("longitude")
    private  String longitude ;
    @SerializedName("send")
    private String send ;




    public BillboardModel() { }

    public BillboardModel(String id, String owner_name, String billboard_name, String billboard_type, String width, String length, String height, String font_language, String area, String locality, String ailley, String street,
                          String bulding_number, String billboard_license, String billboard_license_number, String license_date, String license_end_date, String latitude, String longitude, String send) {
        this.id = id;
        this.owner_name = owner_name;
        this.billboard_name = billboard_name;
        this.billboard_type = billboard_type;
        this.width = width;
        this.length = length;
        this.height = height;
        this.font_language = font_language;
        this.area = area;
        this.locality = locality;
        this.ailley = ailley;
        this.street = street;
        this.bulding_number = bulding_number;
        this.billboard_license = billboard_license;
        this.billboard_license_number = billboard_license_number;
        this.license_date = license_date;
        this.license_end_date = license_end_date;
        this.latitude = latitude;
        this.longitude = longitude;
        this.send = send;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOwner_name() {
        return owner_name;
    }

    public void setOwner_name(String owner_name) {
        this.owner_name = owner_name;
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

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getFont_language() {
        return font_language;
    }

    public void setFont_language(String font_language) {
        this.font_language = font_language;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getAilley() {
        return ailley;
    }

    public void setAilley(String ailley) {
        this.ailley = ailley;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getBulding_number() {
        return bulding_number;
    }

    public void setBulding_number(String bulding_number) {
        this.bulding_number = bulding_number;
    }

    public String getBillboard_license() {
        return billboard_license;
    }

    public void setBillboard_license(String billboard_license) {
        this.billboard_license = billboard_license;
    }

    public String getBillboard_license_number() {
        return billboard_license_number;
    }

    public void setBillboard_license_number(String billboard_license_number) {
        this.billboard_license_number = billboard_license_number;
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

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getSend() {
        return send;
    }

    public void setSend(String send) {
        this.send = send;
    }

    @Override
    public String toString() {
        return "BillboardModel{" +
                "id='" + id + '\'' +
                ", owner_name='" + owner_name + '\'' +
                ", billboard_name='" + billboard_name + '\'' +
                ", billboard_type='" + billboard_type + '\'' +
                ", width='" + width + '\'' +
                ", length='" + length + '\'' +
                ", height='" + height + '\'' +
                ", font_language='" + font_language + '\'' +
                ", area='" + area + '\'' +
                ", locality='" + locality + '\'' +
                ", ailley='" + ailley + '\'' +
                ", street='" + street + '\'' +
                ", bulding_number='" + bulding_number + '\'' +
                ", billboard_license='" + billboard_license + '\'' +
                ", billboard_license_number='" + billboard_license_number + '\'' +
                ", license_date='" + license_date + '\'' +
                ", license_end_date='" + license_end_date + '\'' +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                ", send='" + send + '\'' +
                '}';
    }
}
