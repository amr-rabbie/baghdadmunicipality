package com.unicomg.baghdadmunicipality.Views.add_bill_board;

import com.unicomg.baghdadmunicipality.baseClass.BaseView;

public interface AddBillView extends BaseView {
    void showMessage(String message);
    void showLoading();
    void hideLoading();

    void replaceView();
}


