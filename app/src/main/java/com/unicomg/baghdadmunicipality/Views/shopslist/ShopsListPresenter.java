package com.unicomg.baghdadmunicipality.Views.shopslist;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.unicomg.baghdadmunicipality.baseClass.BasePresenter;
import com.unicomg.baghdadmunicipality.data.ApisClient.ApiInterface;
import com.unicomg.baghdadmunicipality.data.LocalSqlite.ItemDbHelper;
import com.unicomg.baghdadmunicipality.data.models.shops.ShopModel;
import com.unicomg.baghdadmunicipality.data.models.shops.AddShopResponse;
import com.unicomg.baghdadmunicipality.di.DaggerApplication;
import com.unicomg.baghdadmunicipality.helper.Constants;
import com.unicomg.baghdadmunicipality.helper.Utilities;

import java.util.ArrayList;

import javax.inject.Inject;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ShopsListPresenter implements BasePresenter<ShopListView> {

    ShopListView mView;
    @Inject
    ApiInterface mApiInterface;
    @Inject
    Context mContext;
    @Inject
    ItemDbHelper mItemDbHelper;

    @Override
    public void onAttach(ShopListView view) {
        mView = view;
        mView.onAttache();
    }



    @Override
    public void onDetach() {
        mView = null;
    }
    //create Constructor to get reference of api interface object
    public ShopsListPresenter(Context context){
        ((DaggerApplication)context).getAppComponent().inject(this);
    }

    public void getShops() {
        mView.showLoading();
        ArrayList<ShopModel> shopModels = mItemDbHelper.getShopData(Constants.getUserIdByElamary(mContext));
        mView.showShops(shopModels);
        mView.hideLoading();
    }


    void sendOnShopData(ShopModel shopModel ) {
        if(!Utilities.checkConnection(mContext)){

            mView.showMessage("لا يوجد اتصال بالانترنت من فضلك حاول مرة اخرى في وقت لاحق",0);
            mView.hideLoading();
            return;
        }



        mView.showLoading();
        String accesstoken = "Bearer " + Constants.getuserToken(mContext);
        mApiInterface.addShopData2(accesstoken, shopModel
        )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<AddShopResponse>() {
                    @Override
                    public final void onCompleted() {
                        getShops();
                    }

                    @Override
                    public final void onError(Throwable e) {

                        Log.e("add_shop_data", e.getMessage().toString());
                        Toast.makeText(mContext, "من فضلك حاول مره اخري !", Toast.LENGTH_SHORT).show();
                        mView.hideLoading();
                    }

                    @Override
                    public final void onNext(AddShopResponse response) {

                        Log.e("Add Shop response", response.getMessage());
                        if (response.getStatus().equals("success")) {
                            Toast.makeText(mContext, "تم حفظ بيانات المحل بنجاح ", Toast.LENGTH_SHORT).show();
                            mItemDbHelper.updateShopMakeItSensed(shopModel);
                            mView.hideLoading();
                        } else {
                            Toast.makeText(mContext, response.getMessage(), Toast.LENGTH_SHORT).show();
                            mView.hideLoading();
                        }

                    }
                });
    }
}
