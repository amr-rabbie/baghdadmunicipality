package com.unicomg.baghdadmunicipality.Views.add_violation;

import com.unicomg.baghdadmunicipality.baseClass.BaseView;
import com.unicomg.baghdadmunicipality.data.models.category.Category;
import com.unicomg.baghdadmunicipality.data.models.serverViolations.ServerViolation;
import com.unicomg.baghdadmunicipality.data.models.shops.ShopModel;
import com.unicomg.baghdadmunicipality.data.models.violation.ViolationImage;

import java.util.ArrayList;
import java.util.List;

public interface AddViolationView extends BaseView {

    void showMessage(String message, int mColor);
    void setShops(ArrayList<ShopModel> shops);
    void setGroups(ArrayList<Category> groups);
    void setViolations(ArrayList<ServerViolation> serverViolations);

    void afterAdd(List<ViolationImage> violationImages);
    void afterDelete(List<ViolationImage> violationImages);

    void replaceView() ;
    void shopLoading();
    void hideLoading2();
}

