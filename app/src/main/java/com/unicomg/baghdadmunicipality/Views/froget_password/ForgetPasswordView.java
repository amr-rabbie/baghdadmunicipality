package com.unicomg.baghdadmunicipality.Views.froget_password;

import android.content.ClipData;

import com.unicomg.baghdadmunicipality.baseClass.BaseView;


public interface ForgetPasswordView extends BaseView {
    void showLoading();

    void hideLoading();

    void showMessage(String message, int mColor);

}