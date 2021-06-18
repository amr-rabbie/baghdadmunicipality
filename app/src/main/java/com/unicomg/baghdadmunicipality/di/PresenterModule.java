package com.unicomg.baghdadmunicipality.di;


import android.content.Context;

import com.unicomg.baghdadmunicipality.Views.add_bill_board.AddBillPresenter;
import com.unicomg.baghdadmunicipality.Views.add_shops.AddShopPresenter;
import com.unicomg.baghdadmunicipality.Views.add_violation.AddViolationPresenter;
import com.unicomg.baghdadmunicipality.Views.bill_board_list.BillListPresenter;

import com.unicomg.baghdadmunicipality.Views.froget_password.ForgetPasswordPresenter;
import com.unicomg.baghdadmunicipality.Views.login.LoginPresenter;


import com.unicomg.baghdadmunicipality.Views.scheduled_work.WorksListPresenter;
import com.unicomg.baghdadmunicipality.Views.shopslist.ShopsListPresenter;
import com.unicomg.baghdadmunicipality.Views.splash.SplashPresenter;
import com.unicomg.baghdadmunicipality.Views.violations_list.ViolationsListPresenter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


//this class created to put  providers for Presenters

@Module
public class PresenterModule {

    @Provides
    @Singleton
    SplashPresenter provideMainPresenter(Context context) {
        return new SplashPresenter(context);
    }


    @Provides
    @Singleton
    LoginPresenter provideLoginPresenter(Context context) {
        return new LoginPresenter(context);
    }

    @Provides
    @Singleton
    BillListPresenter billListPresenter(Context context) {
        return new BillListPresenter(context);
    }


    @Provides
    @Singleton
    AddBillPresenter addBillPresenter(Context context) {
        return new AddBillPresenter(context);
    }

    @Provides
    @Singleton
    ViolationsListPresenter violationsListPresenter(Context context) {
        return new ViolationsListPresenter(context);
    }

    @Provides
    @Singleton
    AddViolationPresenter addViolationPresenter(Context context) {
        return new AddViolationPresenter(context);
    }
    @Provides
    @Singleton
       AddShopPresenter addShopPresenter(Context context) {
        return new AddShopPresenter(context);
    }


    @Provides
    @Singleton
    ShopsListPresenter shopsListPresenter(Context context) {
        return new ShopsListPresenter(context);
    }



    @Provides
    @Singleton
    ForgetPasswordPresenter forgetPasswordPresenter(Context context) {
        return new ForgetPasswordPresenter(context);
    }

    @Provides
    @Singleton
    WorksListPresenter worksListPresenter(Context context) {
        return new WorksListPresenter(context);
    }

}

