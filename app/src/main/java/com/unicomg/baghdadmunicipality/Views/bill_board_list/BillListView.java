package com.unicomg.baghdadmunicipality.Views.bill_board_list;

import com.unicomg.baghdadmunicipality.baseClass.BaseView;
import com.unicomg.baghdadmunicipality.data.models.billboard.BillboardModel;
import com.unicomg.baghdadmunicipality.data.models.billboard.BillboardModel2;

import java.util.ArrayList;
public interface BillListView extends BaseView {
    void showMessage(String message);
    void showLoading();
    void hideLoading();


    void showBillBoards(ArrayList<BillboardModel2> billboardModels);
}


