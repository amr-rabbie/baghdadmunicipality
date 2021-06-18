package com.unicomg.baghdadmunicipality.data.models.scheduled_works;

import com.google.gson.annotations.SerializedName;
import com.unicomg.baghdadmunicipality.data.models.shops.ShopModel;

import java.util.List;

public class ScheduledWorkModel {

    @SerializedName("id")
    String id  ;
    @SerializedName("user_id")
    String user_id ;
    @SerializedName("shop_data_id")
    String shop_data_id  ;
    @SerializedName("visit_date")
    String visit_date ;

    @SerializedName("shop_name")
    String shop_name ;

    @SerializedName("shop_adress")
    String shop_adress ;


    @SerializedName("shop_date")
    ShopModel shopModels ;

    public ScheduledWorkModel() {
    }

    public ScheduledWorkModel(String id, String user_id, String shop_data_id, String visit_date, String shop_name, String shop_adress, ShopModel shopModels) {
        this.id = id;
        this.user_id = user_id;
        this.shop_data_id = shop_data_id;
        this.visit_date = visit_date;
        this.shop_name = shop_name;
        this.shop_adress = shop_adress;
        this.shopModels = shopModels;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getShop_data_id() {
        return shop_data_id;
    }

    public void setShop_data_id(String shop_data_id) {
        this.shop_data_id = shop_data_id;
    }

    public String getVisit_date() {
        return visit_date;
    }

    public void setVisit_date(String visit_date) {
        this.visit_date = visit_date;
    }

    public String getShop_name() {
        return shop_name;
    }

    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
    }

    public String getShop_adress() {
        return shop_adress;
    }

    public void setShop_adress(String shop_adress) {
        this.shop_adress = shop_adress;
    }

    public ShopModel getShopModels() {
        return shopModels;
    }

    public void setShopModels(ShopModel shopModels) {
        this.shopModels = shopModels;
    }
}
