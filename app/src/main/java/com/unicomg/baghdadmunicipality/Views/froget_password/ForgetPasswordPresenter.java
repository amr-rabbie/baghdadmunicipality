package com.unicomg.baghdadmunicipality.Views.froget_password;

import android.content.Context;
import android.util.Log;
import com.unicomg.baghdadmunicipality.baseClass.BasePresenter;
import com.unicomg.baghdadmunicipality.data.ApisClient.ApiInterface;
import com.unicomg.baghdadmunicipality.data.LocalSqlite.ItemDbHelper;
import com.unicomg.baghdadmunicipality.data.models.forgetpass.ForgetPassResponse;
import com.unicomg.baghdadmunicipality.data.models.forgetpass.ForgetPasswordModel;
import com.unicomg.baghdadmunicipality.di.DaggerApplication;
import com.unicomg.baghdadmunicipality.helper.Utilities;

import javax.inject.Inject;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ForgetPasswordPresenter implements BasePresenter<ForgetPasswordView> {

    @Inject
    ApiInterface mApiInterface;
    @Inject
    Context mContext;
    @Inject
    ItemDbHelper mItemDbHelper;

    ForgetPasswordView mView;

    @Override
    public void onAttach(ForgetPasswordView view) {
        mView = view;
        mView.onAttache();
    }

    @Override
    public void onDetach() {
        mView = null;
    }

    public ForgetPasswordPresenter(Context context) {
        ((DaggerApplication) context).getAppComponent().inject(this);
    }


    public void sendEmail(ForgetPasswordModel forgetPasswordModel ) {

        mView.showLoading();

        if (!Utilities.checkConnection(mContext)) {

            mView.showMessage("لا يوجد اتصال بالانترنت من فضلك حاول مرة اخرى في وقت لاحق" , 0);
            mView.hideLoading();
            return;
        }

        mApiInterface.getNewPassword(forgetPasswordModel)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ForgetPassResponse>() {
                    @Override
                    public final void onCompleted() {

                    }

                    @Override
                    public final void onError(Throwable e) {
                        mView.showMessage("من فضلك حاول مره اخري ." , 0);
                        mView.hideLoading();
                        Log.e("forget_pass :", e.getMessage());

                    }

                    @Override
                    public final void onNext(ForgetPassResponse response) {

                        if (response.getStatus().equals("success")) {
                            Log.e("forgetpass onNext:", "message : " + response.getMessage() + "status : " + response.getStatus());
                            mView.showMessage ("تم اضافة بيانات اللوحة الاعلانية"  , 0);
                            mView.hideLoading();
                        } else {
                            Log.e("forgetpass onNext:", "message : " + response.getMessage() + "status : " + response.getStatus());
                            mView.hideLoading();
                            //mView.showMessage(response.getMessage());
                        }

                    }
                });

    }
}

