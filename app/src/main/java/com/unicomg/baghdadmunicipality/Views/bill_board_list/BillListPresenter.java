package com.unicomg.baghdadmunicipality.Views.bill_board_list;

import android.content.Context;
import android.util.Log;

import com.unicomg.baghdadmunicipality.baseClass.BasePresenter;
import com.unicomg.baghdadmunicipality.data.ApisClient.ApiInterface;
import com.unicomg.baghdadmunicipality.data.LocalSqlite.ItemDbHelper;
import com.unicomg.baghdadmunicipality.data.models.billboard.BillboardModel;
import com.unicomg.baghdadmunicipality.data.models.billboard.BillboardModel2;
import com.unicomg.baghdadmunicipality.data.models.billboard.BillboardResponse;
import com.unicomg.baghdadmunicipality.di.DaggerApplication;
import com.unicomg.baghdadmunicipality.helper.Constants;
import com.unicomg.baghdadmunicipality.helper.Utilities;

import java.util.ArrayList;

import javax.inject.Inject;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class BillListPresenter implements BasePresenter<BillListView> {

    BillListView mView;
    @Inject
    ApiInterface mApiInterface;
    @Inject
    Context mContext;
    @Inject
    ItemDbHelper mItemDbHelper;
    @Override
    public void onAttach(BillListView view) {
        mView = view;
        mView.onAttache();
    }
    @Override
    public void onDetach() {
        mView = null;
    }
    //create Constructor to get reference of api interface object
    public BillListPresenter(Context context){
        ((DaggerApplication)context).getAppComponent().inject(this);
    }

    public void getBillBoards() {
        mView.showLoading();
        ArrayList<BillboardModel2> billboardModels = mItemDbHelper.getBillboards(Constants.getUserIdByElamary(mContext));
        mView.showBillBoards(billboardModels);
        mView.hideLoading();
    }

    public void sendOneBillboard(final BillboardModel billboardModel, final String billboardId) {

        if(!Utilities.checkConnection(mContext)){

            mView.showMessage("لا يوجد اتصال بالانترنت من فضلك حاول مرة اخرى في وقت لاحق");
            mView.hideLoading();
            return;
        }

        mView.showLoading();
        mApiInterface.saveBillboard("Bearer " + Constants.getuserToken(mContext), billboardModel)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<BillboardResponse>() {
                    @Override
                    public final void onCompleted() {

                        getBillBoards();
                    }

                    @Override
                    public final void onError(Throwable e) {
                        mView.showMessage("من فضلك حاول مره اخري .");
                        mView.hideLoading();
                        Log.e("Sr_info_ big onError:", e.getMessage());

                    }

                    @Override
                    public final void onNext(BillboardResponse response) {

                        if (response.getStatus().equals("success")) {
                            Log.e("Sr_info_ big onNext:", "message : " + response.getMessage() + "status : " + response.getStatus());
                            mView.showMessage("تم اضافة بيانات اللوحة الاعلانية");
                            mItemDbHelper.updateBillboard(billboardModel , billboardId);
                            mView.hideLoading();
                        } else {
                            Log.e("Sr_info_ big onNext:", "message : " + response.getMessage() + "status : " + response.getStatus());
                            mView.hideLoading();
                            mView.showMessage(response.getMessage());
                        }

                    }
                });

    }

}

