package com.unicomg.baghdadmunicipality.Views.violations_list;

import android.view.View;

import com.unicomg.baghdadmunicipality.data.models.shops.ShopModel;
import com.unicomg.baghdadmunicipality.data.models.violation.ViolationModel;


public interface ViolationItemClick {

    void sendOneViolation(ViolationModel violation, View v, int position);
    void updateJob(View v, int position);
}

