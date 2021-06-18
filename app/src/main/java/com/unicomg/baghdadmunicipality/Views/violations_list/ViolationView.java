package com.unicomg.baghdadmunicipality.Views.violations_list;

import com.unicomg.baghdadmunicipality.baseClass.BaseView;
import com.unicomg.baghdadmunicipality.data.models.shops.ShopModel;
import com.unicomg.baghdadmunicipality.data.models.violation.ViolationModel;

import java.util.ArrayList;

public interface ViolationView   extends BaseView {

    void showMessage(String message, int mColor);
    void showLoading();
    void hideLoading();
    void showViolations(ArrayList<ViolationModel> violations);

}

