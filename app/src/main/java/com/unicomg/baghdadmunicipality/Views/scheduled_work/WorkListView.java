package com.unicomg.baghdadmunicipality.Views.scheduled_work;

import com.unicomg.baghdadmunicipality.baseClass.BaseView;
import com.unicomg.baghdadmunicipality.data.models.scheduled_works.ScheduledWorkModel;
import com.unicomg.baghdadmunicipality.data.models.shops.ShopModel;

import java.util.ArrayList;

public interface WorkListView extends BaseView {
    void showMessage(String message, int mColor);
    void showLoading();
    void hideLoading();
    void showShops(ArrayList<ScheduledWorkModel> shopModels);
}

