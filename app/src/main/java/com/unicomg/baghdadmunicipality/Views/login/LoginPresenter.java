package com.unicomg.baghdadmunicipality.Views.login;

import android.app.Dialog;
import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.unicomg.baghdadmunicipality.baseClass.BasePresenter;
import com.unicomg.baghdadmunicipality.data.ApisClient.ApiInterface;
import com.unicomg.baghdadmunicipality.data.LocalSqlite.ItemDbHelper;
import com.unicomg.baghdadmunicipality.data.models.Login.AccessTokenModel;
import com.unicomg.baghdadmunicipality.data.models.Login.LoginModel;
import com.unicomg.baghdadmunicipality.data.models.category.CategoriesResponse;
import com.unicomg.baghdadmunicipality.data.models.category.Category;
import com.unicomg.baghdadmunicipality.data.models.scheduled_works.ScheduledWorkModel;
import com.unicomg.baghdadmunicipality.data.models.scheduled_works.ScheduledWorksResponse;
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

public class LoginPresenter  implements BasePresenter<LoginView> {
    LoginView mView;
    boolean isLoaded = false;

    //inject api interface object
    @Inject
    ApiInterface mApiInterface;
    @Inject
    Context mContext;
    // create sqllit reference
    @Inject
    ItemDbHelper mItemDbHelper;
    private String access_token;

    @Override
    public void onAttach(LoginView view) {
        mView = view;
        mView.onAttache();
    }

    @Override
    public void onDetach() {
        mView = null;
    }

    //create Constructor to get reference of api interface object
    public LoginPresenter(Context context){
        ((DaggerApplication)context).getAppComponent().inject(this);
    }

    //this function created to load items from specific endpoint
    public void loadItems() {
        if(!Utilities.checkConnection(mContext)){
            checkConnection(false);
            return;
        }
        mView.showLoading();
    }

    void checkConnection(boolean isConnected) {
        //check internet and  data not loaded
        if(isConnected  && !isLoaded){
            loadItems();
            isLoaded = false;
            //   mView.showMessage(mContext.getString(R.string.connect_to_internet),Color.GREEN);
        }if(!isConnected && isLoaded){
            //offline check and  data loaded
            //  mView.showMessage(mContext.getString(R.string.offline),Color.WHITE);

        }else if(!isConnected && !isLoaded){
            //get offline  data using realm
            //mView.showMessage(mContext.getString(R.string.get_data_from_local),Color.WHITE);
         }else if(isConnected && isLoaded){
            //mView.showMessage(mContext.getString(R.string.connect_to_internet),Color.GREEN);
        }
    }

    public void getLogin(final String email, final String pw, final LinearLayout ll, final Dialog dialog) {

        if(!Utilities.checkConnection(mContext)){
            checkConnection(false);

            mView.showMessage("من فضلك قم بالاتصال بالانترنت",0);
            return;
        }

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                dialog.show();
        mApiInterface.getLogin(email,pw)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<AccessTokenModel>() {
                    @Override
                    public final void onCompleted() { }

                    @Override
                    public final void onError(Throwable e) {

                        dialog.dismiss();
                        Toast.makeText(mContext,"من فضلك حاول مره اخري ",Toast.LENGTH_LONG).show();
                        Log.e("OnErrorLogin",e.getMessage().toString());
                    }

                    @Override
                    public void onNext(AccessTokenModel response ) {

                        if(response.getStatus().equals("success")) {
                            access_token = response.getAccess_token();
                            Constants.saveAcessToked(access_token ,mContext );
                            Log.e("AccessTokenValue" , access_token) ;

                            if( access_token != null && ! access_token.isEmpty()    ) {
                                getLoginData(access_token , dialog);
                            }
                            else {
                                dialog.dismiss();
                                Toast.makeText(mContext, response.getMessage(), Toast.LENGTH_SHORT).show();
                            }

                        }
                        else {
                            dialog.dismiss();
                            Toast.makeText(mContext, response.getMessage(), Toast.LENGTH_SHORT).show();
                        }


                    }
                });

            }
        }, 1000);


        dialog.dismiss();
    }

    public void getShopsActivities(Dialog dialog) {
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
                        getAllCategories(dialog);
                    }

                    @Override
                    public final void onError(Throwable e) {
                        Log.e("shop_activities",e.getMessage().toString());
                        dialog.dismiss();
                        Toast.makeText(mContext, "من فضلك حاول مره اخري .", Toast.LENGTH_SHORT).show();
                        Constants.saveLoginData("", "", "", ""  , "",  "" , ""  , mContext);
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
    public void getAllCategories(Dialog dialog) {
        mApiInterface.getCategories()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<CategoriesResponse>() {
                    @Override
                    public final void onCompleted() {
                        getAllViolations(dialog);
                    }

                    @Override
                    public final void onError(Throwable e) {
                        Toast.makeText(mContext, "من فضلك حاول مره اخري .", Toast.LENGTH_SHORT).show();
                        Log.e("categories",e.getMessage().toString());
                        Constants.saveLoginData("", "", "", ""  , "",  "" , ""  , mContext);
                        dialog.dismiss();
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
    public void getAllViolations(Dialog dialog) {
        mApiInterface.getViolations()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ServerViolationsResponse>() {
                    @Override
                    public final void onCompleted() {

                        getAllScheduledWorks(dialog);
                        //mView.openProjectsActivity();
                    }

                    @Override
                    public final void onError(Throwable e) {
                        Toast.makeText(mContext, "من فضلك حاول مره اخري .", Toast.LENGTH_SHORT).show();
                        Log.e("onErrorServerViolations", e.getMessage().toString());
                        Constants.saveLoginData("", "", "", ""  , "",  "" , ""  , mContext);
                        dialog.dismiss();
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


    public void getLoginData(String accesstoken , Dialog dialog){

        mApiInterface.getLoginData("Bearer " +accesstoken)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<LoginModel>() {
                    @Override
                    public final void onCompleted() {
                        getShopsData(dialog);
                    }

                    public final void onError(Throwable e) {
                        dialog.dismiss();
                        Toast.makeText(mContext, "من فضلك حاول مره اخري .", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNext(LoginModel loginModel) {
                        String user_id = loginModel.getId();
                        String first_name = loginModel.getFirst_name();
                        String last_name = loginModel.getLast_name();
                        String username = loginModel.getUsername();
                        String email = loginModel.getEmail();
                        String permission = loginModel.getPermission();
                        String is_active = loginModel.getIs_active();
                        Log.e("logindata",loginModel.toString());
                        Constants.saveLoginData(user_id,first_name,last_name,username,email,permission,is_active,mContext);
                       // Toast.makeText(mContext,  "مرحبا بك "   + first_name + " " + last_name, Toast.LENGTH_SHORT).show();
                    }

                });

    }
    public void getShopsData(Dialog dialog) {
        access_token = Constants.getuserToken(mContext);
        mApiInterface.getAllShopsData("Bearer " + access_token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ShopsResponse>() {
                    @Override
                    public final void onCompleted() {
                        getShopsActivities(dialog);
                    }

                    @Override
                    public final void onError(Throwable e) {
                        Toast.makeText(mContext, "من فضلك حاول مره اخري .", Toast.LENGTH_SHORT).show();
                         dialog.dismiss();
                        Constants.saveLoginData("", "", "", ""  , "",  "" , ""  , mContext);
                        Log.e("Server_Shops", e.getMessage().toString());
                    }

                    @Override
                    public void onNext(ShopsResponse shopsResponse) {

                        if (!mItemDbHelper.getSERVERShopData().isEmpty()) {
                            mItemDbHelper.deleteAllServerShopData();
                        }

                        List<ShopModel> shopModels = shopsResponse.getShopModels();
                        for (int i = 0; i < shopModels.size(); i++) {
                            ShopModel shopModel = shopModels.get(i);
                            Log.e("shop>>>>>>>", shopModel.toString());
                            mItemDbHelper.add_Server_Shop_Data(shopModel.getShop_id(), shopModel.getOwner_name(), shopModel.getType(), shopModel.getShop_activity_id(), shopModel.getWidth(),
                                    shopModel.getLength(), shopModel.getEmployee_number(), shopModel.getFloor_number(), shopModel.getArea(), shopModel.getStreet(), shopModel.getAlley(), shopModel.getLocality()
                                    , shopModel.getLocality_number(), shopModel.getLicense_number(), shopModel.getLicense_type(), shopModel.getLicense_date(), shopModel.getLicense_end_date(),
                                    shopModel.getBillboard_name(), shopModel.getBillboard_type(), shopModel.getBillboard_width(), shopModel.getBillboard_length()
                                    , shopModel.getBillboard_height(), shopModel.getBillboard_font_type() , shopModel.getShop_notes());


                        }
                    }
                });
    }


    public void getAllScheduledWorks(Dialog dialog) {
        mApiInterface.getScheduledWorksResponseObservable("Bearer " +  Constants.getuserToken(mContext))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ScheduledWorksResponse>() {
                    @Override
                    public final void onCompleted() {
                        Toast.makeText(mContext, "تم تسجيل الدخول بنجاح", Toast.LENGTH_SHORT).show();
                        mView.openProjectsActivity();
                    }

                    @Override
                    public final void onError(Throwable e) {
                        Constants.saveLoginData("", "", "", "", "", "", "", mContext);
                        Toast.makeText(mContext, "من فضلك حاول مره اخري .", Toast.LENGTH_SHORT).show();
                        Log.e("onErrorgAllSchedudWorks", e.getMessage().toString());

                        dialog.dismiss();
                    }

                    @Override
                    public void onNext(ScheduledWorksResponse response) {

                        List<ScheduledWorkModel> scheduledWorkModels = response.getScheduledWorkModels();

                        if (!mItemDbHelper.getScheduledWork(Constants.getUserIdByElamary(mContext)).isEmpty()){
                            mItemDbHelper.deleteAllScheduledWorks(Constants.getUserIdByElamary(mContext));
                        }


                        for (int i = 0; i < scheduledWorkModels.size(); i++) {
                            ScheduledWorkModel scheduledWorkModel = scheduledWorkModels.get(i);
                            Log.e("scheduledWorkModel", scheduledWorkModel.toString());
                            mItemDbHelper.addScheduledWork(scheduledWorkModel , Constants.getUserIdByElamary(mContext));
                        }
                    }
                });
    }

}
