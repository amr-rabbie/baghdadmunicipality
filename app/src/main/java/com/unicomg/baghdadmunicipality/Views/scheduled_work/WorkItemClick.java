package com.unicomg.baghdadmunicipality.Views.scheduled_work;

import android.view.View;

import com.unicomg.baghdadmunicipality.data.models.scheduled_works.ScheduledWorkModel;
import com.unicomg.baghdadmunicipality.data.models.shops.ShopModel;


public interface WorkItemClick {

    void sendOneShop(ScheduledWorkModel shopModel, View v, int position);
    void updateJob(View v, int position);
}

