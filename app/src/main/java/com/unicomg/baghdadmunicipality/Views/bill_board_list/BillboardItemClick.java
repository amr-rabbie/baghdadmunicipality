package com.unicomg.baghdadmunicipality.Views.bill_board_list;

import android.view.View;

import com.unicomg.baghdadmunicipality.data.models.billboard.BillboardModel;
import com.unicomg.baghdadmunicipality.data.models.billboard.BillboardModel2;

public interface BillboardItemClick {

    void sendBillboard(BillboardModel2 billboardModel , View v, int position);
    void updateJob(View v, int position);
}

