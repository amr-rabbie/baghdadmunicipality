package com.unicomg.baghdadmunicipality.Views.login;

import android.content.ClipData;


import com.unicomg.baghdadmunicipality.baseClass.BaseView;

import java.util.ArrayList;

public interface LoginView  extends BaseView {
    void showLoading();

    void hideLoading();

    void showMessage(String message, int mColor);

    void updateList(ArrayList<ClipData.Item> items);

    void openProjectsActivity();

    //void getLogin(String email,String pw);
}