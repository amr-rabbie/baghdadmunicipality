package com.unicomg.baghdadmunicipality.Views.shopslist;

import com.unicomg.baghdadmunicipality.baseClass.BaseView;
import com.unicomg.baghdadmunicipality.data.models.billboard.BillboardModel;
import com.unicomg.baghdadmunicipality.data.models.shops.ShopModel;


import java.util.ArrayList;

public interface ShopListView extends BaseView {
    void showMessage(String message, int mColor);
    void showLoading();
    void hideLoading();
    void showShops(ArrayList<ShopModel> shopModels);
}

