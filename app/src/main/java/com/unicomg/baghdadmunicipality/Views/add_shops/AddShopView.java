package com.unicomg.baghdadmunicipality.Views.add_shops;

import android.content.ClipData;

import com.unicomg.baghdadmunicipality.baseClass.BaseView;

import java.util.ArrayList;

public interface AddShopView extends BaseView {

    void showLoading();
    void hideLoading();
    void showMessage(String message, int mColor);
    void showMessageForSave(String message);
    void replaceView();
}


