package com.unicomg.baghdadmunicipality.Views.shopslist;

import android.view.View;

import com.unicomg.baghdadmunicipality.data.models.shops.ShopModel;


public interface ShopItemClick {

    void sendOneShop(ShopModel shopModel , View v, int position);
    void updateJob(View v, int position);
}

