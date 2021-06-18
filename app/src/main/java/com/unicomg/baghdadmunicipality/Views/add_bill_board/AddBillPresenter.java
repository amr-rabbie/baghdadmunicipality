package com.unicomg.baghdadmunicipality.Views.add_bill_board;

import android.content.Context;
import android.util.Log;

import com.unicomg.baghdadmunicipality.baseClass.BasePresenter;
import com.unicomg.baghdadmunicipality.data.ApisClient.ApiInterface;
import com.unicomg.baghdadmunicipality.data.LocalSqlite.ItemDbHelper;
import com.unicomg.baghdadmunicipality.data.LocalSqlite.billBoard.BillBoardContract;
import com.unicomg.baghdadmunicipality.data.models.billboard.BillboardModel;
import com.unicomg.baghdadmunicipality.data.models.billboard.BillboardModel2;
import com.unicomg.baghdadmunicipality.data.models.billboard.BillboardResponse;
import com.unicomg.baghdadmunicipality.data.models.shops.ShopModel;
import com.unicomg.baghdadmunicipality.di.DaggerApplication;
import com.unicomg.baghdadmunicipality.helper.Constants;
import com.unicomg.baghdadmunicipality.helper.Utilities;

import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class AddBillPresenter implements BasePresenter<AddBillView> {


    AddBillView mView;
    boolean isLoaded = false;
    @Inject
    ApiInterface mApiInterface;
    @Inject
    Context mContext;
    @Inject
    ItemDbHelper mItemDbHelper;
    @Override
    public void onAttach(AddBillView view) {
        mView = view;
        mView.onAttache();
    }

    @Override
    public void onDetach() {
        mView = null;
    }

    //create Constructor to get reference of api interface object
    public AddBillPresenter(Context context){
        ((DaggerApplication)context).getAppComponent().inject(this);
    }

    public long saveLocally(BillboardModel billboardModel) {
        return mItemDbHelper.addBillBoard(billboardModel , Constants.getUserIdByElamary(mContext));
    }


    public void saveToServer(final BillboardModel billboardModel) {
        if (!Utilities.checkConnection(mContext)) {
            mView.showMessage("لا يوجد اتصال بالانترنت من فضلك حاول مرة اخرى في وقت لاحق");
            mView.hideLoading();
            return;
        }

//        Log.e(">>>>>>" , billboardModel.getLatitude());
        mView.showLoading();
        mApiInterface.saveBillboard("Bearer " + Constants.getuserToken(mContext), billboardModel)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<BillboardResponse>() {
                    @Override
                    public final void onCompleted() {

                    }


                    @Override
                    public final void onError(Throwable e) {
                        mView.showMessage("من فضلك حاول مره اخري . ");
                        mView.hideLoading();
                        Log.e("Sr_info_ big onError:",  e.getMessage() );
                       // saveLocally(billboardModel);
                    }

                    @Override
                    public final void onNext(BillboardResponse response) {

                        if (response.getStatus().equals("success")) {
                            Log.e("Sr_info_ big onNext:", "message : " + response.getMessage() + "status : "  + response.getStatus());
                            mView.showMessage("تم اضافة بيانات اللوحة الاعلانية ");
                            BillboardModel billboardModel1222 = billboardModel ;
                            billboardModel1222.setSend("1");
                            saveLocally(billboardModel1222);
                            mView.hideLoading();
                            mView.replaceView();
                        } else {
                            Log.e("Sr_info_ big onNext:", "message : " + response.getMessage() + "status : "  + response.getStatus());
                            mView.hideLoading();
                            mView.showMessage(response.getMessage());
                         //   saveLocally(billboardModel);
                        }

                    }
                });

    }

    public void saveUpdatedBillBoardToServer(final BillboardModel billboardModel  ,  String id) {
        if (!Utilities.checkConnection(mContext)) {
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

                    }


                    @Override
                    public final void onError(Throwable e) {
                        mView.showMessage("من فضلك حاول مره اخري . ");
                        mView.hideLoading();
                        Log.e("Sr_info_ big onError:",  e.getMessage() );
                        // saveLocally(billboardModel);
                    }

                    @Override
                    public final void onNext(BillboardResponse response) {

                        if (response.getStatus().equals("success")) {
                            Log.e("Sr_info_ big onNext:", "message : " + response.getMessage() + "status : "  + response.getStatus());
                            mView.showMessage("تم تعديل بيانات اللوحة الاعلانية ");
                            BillboardModel billboardModel1222 = billboardModel ;
                            billboardModel1222.setSend("1");
                            updateLocally(billboardModel1222 , id);
                            mView.hideLoading();
                            mView.replaceView();
                        } else {
                            Log.e("Sr_info_ big onNext:", "message : " + response.getMessage() + "status : "  + response.getStatus());
                            mView.hideLoading();
                            mView.showMessage(response.getMessage());
                            //   saveLocally(billboardModel);
                        }

                    }
                });

    }


    ///////////////////////////////////////////
    public List<BillboardModel2> getBillBoardByIdd(String id){
        return  mItemDbHelper.getBillboardsById(id);
    }
    public long updateLocally(BillboardModel billboardModel,String id) {
        return mItemDbHelper.updateBillBoard(billboardModel,id);
    }

    ////////////////////////////////////////////////////////////////
}
