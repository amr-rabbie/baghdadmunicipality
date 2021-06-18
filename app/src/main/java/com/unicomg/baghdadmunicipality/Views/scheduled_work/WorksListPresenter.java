package com.unicomg.baghdadmunicipality.Views.scheduled_work;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.unicomg.baghdadmunicipality.baseClass.BasePresenter;
import com.unicomg.baghdadmunicipality.data.ApisClient.ApiInterface;
import com.unicomg.baghdadmunicipality.data.LocalSqlite.ItemDbHelper;
import com.unicomg.baghdadmunicipality.data.models.scheduled_works.ScheduledWorkModel;
import com.unicomg.baghdadmunicipality.data.models.shops.AddShopResponse;
import com.unicomg.baghdadmunicipality.data.models.shops.ShopModel;
import com.unicomg.baghdadmunicipality.di.DaggerApplication;
import com.unicomg.baghdadmunicipality.helper.Constants;
import com.unicomg.baghdadmunicipality.helper.Utilities;

import java.util.ArrayList;

import javax.inject.Inject;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class WorksListPresenter implements BasePresenter<WorkListView> {

    WorkListView mView;
    @Inject
    ApiInterface mApiInterface;
    @Inject
    Context mContext;
    @Inject
    ItemDbHelper mItemDbHelper;

    @Override
    public void onAttach(WorkListView view) {
        mView = view;
        mView.onAttache();
    }

    @Override
    public void onDetach() {
        mView = null;
    }
    //create Constructor to get reference of api interface object
    public WorksListPresenter(Context context){
        ((DaggerApplication)context).getAppComponent().inject(this);
    }

    public void getShops() {
        mView.showLoading();
        ArrayList<ScheduledWorkModel> scheduledWorkModels = mItemDbHelper.getScheduledWork(Constants.getUserIdByElamary(mContext));
        mView.showShops(scheduledWorkModels);
        mView.hideLoading();
    }



}
