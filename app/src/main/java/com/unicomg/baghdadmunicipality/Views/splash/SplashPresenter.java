package com.unicomg.baghdadmunicipality.Views.splash;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.unicomg.baghdadmunicipality.baseClass.BasePresenter;
import com.unicomg.baghdadmunicipality.data.ApisClient.ApiInterface;
import com.unicomg.baghdadmunicipality.data.LocalSqlite.ItemDbHelper;
import com.unicomg.baghdadmunicipality.data.models.category.CategoriesResponse;
import com.unicomg.baghdadmunicipality.data.models.category.Category;
import com.unicomg.baghdadmunicipality.data.models.serverViolations.ServerViolation;
import com.unicomg.baghdadmunicipality.data.models.serverViolations.ServerViolationsResponse;
import com.unicomg.baghdadmunicipality.data.models.shops.ShopModel;
import com.unicomg.baghdadmunicipality.data.models.shops.ShopsResponse;
import com.unicomg.baghdadmunicipality.data.models.shops_activities.ShopsActivitiesDetailsResponse;
import com.unicomg.baghdadmunicipality.data.models.shops_activities.ShopsActivitiesResponse;
import com.unicomg.baghdadmunicipality.di.DaggerApplication;
import com.unicomg.baghdadmunicipality.helper.Constants;
import com.unicomg.baghdadmunicipality.helper.Utilities;

import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class SplashPresenter implements BasePresenter<SplashView> {
    SplashView mView;

    boolean isLoaded = false;
    //inject api interface object
    @Inject
    ApiInterface mApiInterface;
    @Inject
    Context mContext;
    // create sqllit reference
    @Inject
    ItemDbHelper mItemDbHelper;
    @Override
    public void onAttach(SplashView view) {
        mView = view;
        mView.onAttache();
    }

    @Override
    public void onDetach() {
        mView = null;
    }

    //create Constructor to get reference of api interface object
    public SplashPresenter(Context context){
        ((DaggerApplication)context).getAppComponent().inject(this);
    }


    public void getShopsActivities() {
        if(!Utilities.checkConnection(mContext)){
            mView.showMessage("من فضلك قم بالاتصال بالانترنت",-1);
            return;
        }

        mApiInterface.getShopsActivities()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ShopsActivitiesResponse>() {
                    @Override
                    public final void onCompleted() {

                        mView.shopActivitiesSetProgress();
                        getAllCategories();
                    }

                    @Override
                    public final void onError(Throwable e) {
                        Log.e("shop_activities",e.getMessage().toString());
                        Toast.makeText(mContext, "من فضلك حاول مره اخري .", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNext(ShopsActivitiesResponse shopactivities) {

                        if (!mItemDbHelper.getShopsActivities().isEmpty()) {
                            mItemDbHelper.deleteShopsActivities();
                        }

                        List<ShopsActivitiesDetailsResponse> shopactivitie = shopactivities.getData();
                        for(int i=0;i<shopactivitie.size();i++) {
                            ShopsActivitiesDetailsResponse shopactivity= shopactivitie.get(i);
                            Log.e("shop_activities",shopactivity.toString());
                            mItemDbHelper.addShopsActivities(shopactivity);
                        }
                    }
                });
    }
    public void getAllCategories() {
        mApiInterface.getCategories()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<CategoriesResponse>() {
                    @Override
                    public final void onCompleted() {
                        mView.allCategoriesSetProgress();
                        getAllViolations();
                    }

                    @Override
                    public final void onError(Throwable e) {
                        Toast.makeText(mContext, "من فضلك حاول مره اخري .", Toast.LENGTH_SHORT).show();
                        Log.e("categories",e.getMessage().toString());
                    }

                    @Override
                    public void onNext(CategoriesResponse category) {

                        List<Category> categories = category.getCategories();

                        if (!mItemDbHelper.getCategories().isEmpty())
                            mItemDbHelper.deleteCategories();

                        for(int i=0;i<categories.size();i++) {
                            Category category1 = categories.get(i);
                            Log.e("category",category1.toString());
                            mItemDbHelper.addCategories(category1);
                        }
                    }
                });
    }
    public void getAllViolations() {
        mApiInterface.getViolations()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ServerViolationsResponse>() {
                    @Override
                    public final void onCompleted() {
                        mView.allViolationsSetProgress();

                        mView.startLoginActivity();
                    }

                    @Override
                    public final void onError(Throwable e) {
                        Toast.makeText(mContext, "من فضلك حاول مره اخري .", Toast.LENGTH_SHORT).show();
                        Log.e("onErrorServerViolations", e.getMessage().toString());
                    }

                    @Override
                    public void onNext(ServerViolationsResponse response) {

                        List<ServerViolation> serverViolations = response.getServerViolations();

                        if (!mItemDbHelper.getAllServerViolations().isEmpty())
                            mItemDbHelper.deleteAllServerViolations();

                        for (int i = 0; i < serverViolations.size(); i++) {
                            ServerViolation serverViolation = serverViolations.get(i);
                            Log.e("ServerViolations", serverViolation.toString());
                            mItemDbHelper.addServerViolations(serverViolation);
                        }
                    }
                });
    }
    public void getShopsData() {

        if(!Utilities.checkConnection(mContext)){
            mView.showMessage("من فضلك قم بالاتصال بالانترنت",-1);
            return;
        }

        mApiInterface.getAllShopsData("Bearer " + Constants.getuserToken(mContext))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ShopsResponse>() {
                    @Override
                    public final void onCompleted() {
                       getShopsActivities();
                    }

                    @Override
                    public final void onError(Throwable e) {
                        Log.e("Server_Shops", e.getMessage().toString());
                        Toast.makeText(mContext, "من فضلك حاول مره اخري .", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNext(ShopsResponse shopsResponse) {

                        if (!mItemDbHelper.getSERVERShopData().isEmpty()) {
                            mItemDbHelper.deleteAllServerShopData();
                        }

                        List<ShopModel> shopModels = shopsResponse.getShopModels();
                        for (int i = 0; i < shopModels.size(); i++) {
                            ShopModel shopModel = shopModels.get(i);

                            Log.e("Shop>>>>>" , shopModel.toString()) ;
                            mItemDbHelper.add_Server_Shop_Data(shopModel.getShop_id(), shopModel.getOwner_name(), shopModel.getType(), shopModel.getShop_activity_id(), shopModel.getWidth(),
                                    shopModel.getLength(), shopModel.getEmployee_number(), shopModel.getFloor_number(), shopModel.getArea(), shopModel.getStreet(), shopModel.getAlley(), shopModel.getLocality()
                                    , shopModel.getLocality_number(), shopModel.getLicense_number(), shopModel.getLicense_type(), shopModel.getLicense_date(), shopModel.getLicense_end_date(),
                                    shopModel.getBillboard_name(), shopModel.getBillboard_type(), shopModel.getBillboard_width(), shopModel.getBillboard_length()
                                    , shopModel.getBillboard_height(), shopModel.getBillboard_font_type() , shopModel.getShop_notes());


                        }
                    }
                });
    }


    void checkConnection(boolean isConnected) {
        //check internet and  data not loaded
        if(isConnected  && !isLoaded){
            //loadGovernor();
            isLoaded = false;
         //   mView.showMessage(mContext.getString(R.string.connect_to_internet),Color.GREEN);
        }

        if(!isConnected){
            new android.os.Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
               //
                    //mView.startLoginActivity();
                }
            } , 1000);

        }
        else if(!isConnected && isLoaded){
            //offline check and  data loaded
           //  mView.showMessage(mContext.getString(R.string.offline),Color.WHITE);

        }
        else if(!isConnected && !isLoaded){
            //get offline  data using realm
            //mView.showMessage(mContext.getString(R.string.get_data_from_local),Color.WHITE);
            // mView.updateList(mItemDbHelper.getAllItems());

        }else if(isConnected && isLoaded){
            //mView.showMessage(mContext.getString(R.string.connect_to_internet),Color.GREEN);
        }
    }

}
