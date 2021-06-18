package com.unicomg.baghdadmunicipality.Views.splash;
import com.unicomg.baghdadmunicipality.baseClass.BaseView;

import java.util.ArrayList;

public interface SplashView extends BaseView {
    void showMessage(String message, int mColor);

    void shopActivitiesSetProgress();
    void allCategoriesSetProgress();
    void allViolationsSetProgress();
    void allShopsSetProgress();

    void startLoginActivity();
}
