package com.unicomg.baghdadmunicipality.Views.add_shops;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.unicomg.baghdadmunicipality.baseClass.BasePresenter;
import com.unicomg.baghdadmunicipality.data.ApisClient.ApiInterface;
import com.unicomg.baghdadmunicipality.data.LocalSqlite.ItemDbHelper;
import com.unicomg.baghdadmunicipality.data.models.shops.AddShopResponse;
import com.unicomg.baghdadmunicipality.data.models.shops.ShopModel;
import com.unicomg.baghdadmunicipality.data.models.shops_activities.ShopsActivitiesDetailsResponse;
import com.unicomg.baghdadmunicipality.di.DaggerApplication;
import com.unicomg.baghdadmunicipality.helper.Constants;
import com.unicomg.baghdadmunicipality.helper.Utilities;

import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class AddShopPresenter implements BasePresenter<AddShopView> {


    AddShopView mView;
    boolean isLoaded = false;

    @Inject
    ApiInterface mApiInterface;
    @Inject
    Context mContext;
    @Inject
    ItemDbHelper mItemDbHelper;
    public AddShopPresenter(Context context){
        ((DaggerApplication)context).getAppComponent().inject(this);
    }

    public void loadItems() {
        if(!Utilities.checkConnection(mContext)){
            checkConnection(false);
            return;
        }
        mView.showLoading();
    }



    @Override
    public void onAttach(AddShopView view) {
        mView = view;
        mView.onAttache();
    }

    @Override
    public void onDetach() {
        mView = null;
    }

    public long saveShopDataoffline(ShopModel shopModel)
    {
    return mItemDbHelper.add_Shop_Data(Constants.getUserIdByElamary(mContext) , shopModel.getShop_id(), shopModel.getOwner_name(), shopModel.getType(), shopModel.getShop_activity_id()
                , shopModel.getWidth(), shopModel.getLength(), shopModel.getEmployee_number(), shopModel.getFloor_number(),
                shopModel.getArea(), shopModel.getStreet(), shopModel.getAlley(), shopModel.getLocality(), shopModel.getLocality_number(), shopModel.getLicense_number()
                , shopModel.getLicense_type(),
                shopModel.getLicense_date(), shopModel.getLicense_end_date(), shopModel.getBillboard_name(),
                shopModel.getBillboard_type(), shopModel.getBillboard_width(),
                shopModel.getBillboard_length(), shopModel.getBillboard_height(), shopModel.getBillboard_font_type(),shopModel.getLongitude(),shopModel.getLatitude()  , shopModel.getShop_notes());

    }


    public List<ShopsActivitiesDetailsResponse> getShopactivitiesoffline(){

        return mItemDbHelper.getShActivities();
    }

    void checkConnection(boolean isConnected) {
        //check internet and  data not loaded
        if (isConnected && !isLoaded) {
            loadItems();
            isLoaded = false;
            //   mView.showMessage(mContext.getString(R.string.connect_to_internet),Color.GREEN);
        }
        if (!isConnected && isLoaded) {
            //offline check and  data loaded
            //  mView.showMessage(mContext.getString(R.string.offline),Color.WHITE);

        } else if (!isConnected && !isLoaded) {
            //get offline  data using realm
            //mView.showMessage(mContext.getString(R.string.get_data_from_local),Color.WHITE);
        } else if (isConnected && isLoaded) {
            //mView.showMessage(mContext.getString(R.string.connect_to_internet),Color.GREEN);
        }
    }


   public void sendOnShopData(ShopModel shopModel) {
        if (!Utilities.checkConnection(mContext)) {

            mView.showMessage("لا يوجد اتصال بالانترنت من فضلك حاول مرة اخرى في وقت لاحق", 0);
            mView.hideLoading();
            return;
        }


        String accesstoken = "Bearer " + Constants.getuserToken(mContext);
        mApiInterface.addShopData2(accesstoken, shopModel)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<AddShopResponse>() {
                    @Override
                    public final void onCompleted() {
                        mView.replaceView();
                    }

                    @Override
                    public final void onError(Throwable e) {

                        Log.e("add_shop_data", e.getMessage().toString());
                        Toast.makeText(mContext, "من فضلك حاول مره اخري ", Toast.LENGTH_LONG).show();
                        mItemDbHelper.deleteShop(shopModel.getShop_id());
                        mView.hideLoading();
                       // mView.replaceView();
                    }

                    @Override
                    public final void onNext(AddShopResponse response) {

                        Log.e("Add Shop response", response.getMessage());
                        if (response.getStatus().equals("success")) {
                            Toast.makeText(mContext, "تم حفظ بيانات المحل بنجاح ", Toast.LENGTH_SHORT).show();
                            mItemDbHelper.updateShopMakeItSensed(shopModel);
                        } else {
                            Toast.makeText(mContext, "تم حفظ بيانات المحل بدون مزامنة بسبب " + response.getMessage(), Toast.LENGTH_LONG).show();
                            Log.e("Errorororro", response.getMessage());
                        }

                    }
                });
    }
    public void sendOneUpdatedShopData(ShopModel shopModel) {
        if (!Utilities.checkConnection(mContext)) {

            mView.showMessage("لا يوجد اتصال بالانترنت من فضلك حاول مرة اخرى في وقت لاحق", 0);
            mView.hideLoading();
            return;
        }


        String accesstoken = "Bearer " + Constants.getuserToken(mContext);
        mApiInterface.addShopData2(accesstoken, shopModel)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<AddShopResponse>() {
                    @Override
                    public final void onCompleted() {
                        mView.replaceView();
                    }

                    @Override
                    public final void onError(Throwable e) {

                        Log.e("add_shop_data", e.getMessage().toString());
                        Toast.makeText(mContext, "من فضلك حاول مره اخري ", Toast.LENGTH_LONG).show();
                        //mItemDbHelper.deleteShop(shopModel.getShop_id());
                        mView.hideLoading();
                        // mView.replaceView();
                    }

                    @Override
                    public final void onNext(AddShopResponse response) {

                        Log.e("Add Shop response", response.getMessage());
                        if (response.getStatus().equals("success")) {
                            Toast.makeText(mContext, "تم تعديل بيانات المحل بنجاح ", Toast.LENGTH_SHORT).show();
                            mItemDbHelper.updateShopMakeItSensed(shopModel);
                        } else {
                            Toast.makeText(mContext, "تم تعديل بيانات المحل بدون مزامنة بسبب " + response.getMessage(), Toast.LENGTH_LONG).show();
                            Log.e("Errorororro", response.getMessage());
                        }

                    }
                });
    }


    //////////////////////////////
    public List<ShopModel> getShopDataByIdd(String id){
        return  mItemDbHelper.getShopDataById(id);
    }
    public long updateShopDataoffline(ShopModel shopModel){
        return mItemDbHelper.updateShopData(shopModel.getShop_id(), shopModel.getOwner_name(), shopModel.getType(), shopModel.getShop_activity_id()
                , shopModel.getWidth(), shopModel.getLength(), shopModel.getEmployee_number(), shopModel.getFloor_number(),
                shopModel.getArea(), shopModel.getStreet(), shopModel.getAlley(), shopModel.getLocality(), shopModel.getLocality_number(), shopModel.getLicense_number()
                , shopModel.getLicense_type(),
                shopModel.getLicense_date(), shopModel.getLicense_end_date(), shopModel.getBillboard_name(),
                shopModel.getBillboard_type(), shopModel.getBillboard_width(),
                shopModel.getBillboard_length(), shopModel.getBillboard_height(), shopModel.getBillboard_font_type(),shopModel.getLongitude(),shopModel.getLatitude() , shopModel.getShop_notes());

    }
    ///////////////////////////////

}
