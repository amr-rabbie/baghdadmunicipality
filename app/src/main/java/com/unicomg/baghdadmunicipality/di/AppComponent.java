package com.unicomg.baghdadmunicipality.di;


import com.unicomg.baghdadmunicipality.Views.add_bill_board.AddBillFragment;
import com.unicomg.baghdadmunicipality.Views.add_bill_board.AddBillPresenter;
import com.unicomg.baghdadmunicipality.Views.add_bill_board.UpdateBillBoardFragment;
import com.unicomg.baghdadmunicipality.Views.add_shops.AddShopFragment;
import com.unicomg.baghdadmunicipality.Views.add_shops.AddShopPresenter;
import com.unicomg.baghdadmunicipality.Views.add_shops.UpdateShopFragment;
import com.unicomg.baghdadmunicipality.Views.add_violation.AddViolationFragment;
import com.unicomg.baghdadmunicipality.Views.add_violation.AddViolationPresenter;
import com.unicomg.baghdadmunicipality.Views.bill_board_list.BillListFragment;
import com.unicomg.baghdadmunicipality.Views.bill_board_list.BillListPresenter;
import com.unicomg.baghdadmunicipality.Views.froget_password.ForgetPasswordActivity;
import com.unicomg.baghdadmunicipality.Views.froget_password.ForgetPasswordPresenter;
import com.unicomg.baghdadmunicipality.Views.login.LoginActivity;
import com.unicomg.baghdadmunicipality.Views.login.LoginPresenter;

import com.unicomg.baghdadmunicipality.Views.scheduled_work.WorksListFragment;
import com.unicomg.baghdadmunicipality.Views.scheduled_work.WorksListPresenter;
import com.unicomg.baghdadmunicipality.Views.shopslist.ShopsListFragment;
import com.unicomg.baghdadmunicipality.Views.shopslist.ShopsListPresenter;
import com.unicomg.baghdadmunicipality.Views.splash.SplashActivity;
import com.unicomg.baghdadmunicipality.Views.splash.SplashPresenter;
import com.unicomg.baghdadmunicipality.Views.violations_list.ViolationsListFragment;
import com.unicomg.baghdadmunicipality.Views.violations_list.ViolationsListPresenter;


import javax.inject.Singleton;

import dagger.Component;

// this class created for register who need inject
@Singleton
@Component(modules = {AppModule.class, NetworkModule.class, PresenterModule.class})
public interface AppComponent {

    void inject(SplashActivity splashActivity);
    void inject(SplashPresenter mainPresenter);

    void inject(LoginPresenter loginPresenter);
    void inject(LoginActivity loginActivity);

    /////////////////////////////////////////////////////

    void inject(BillListFragment billListFragment);
    void inject(BillListPresenter billListPresenter);
    void inject(AddBillFragment addBillFragment);
    void inject(UpdateBillBoardFragment updateBillBoardFragment);

    void inject(AddBillPresenter addBillPresenter);
    void inject(ViolationsListFragment violationsListFragment);
    void inject(ViolationsListPresenter violationsListPresenter);
    void inject(AddViolationFragment addViolationFragment);
    void inject(AddViolationPresenter addViolationPresenter);

    void inject(AddShopPresenter addShopPresenter);
    void inject(AddShopFragment addShopFragment);
    void inject(UpdateShopFragment updateShopFragment);


    void inject(ShopsListFragment shopsListFragment);
    void inject(ShopsListPresenter  shopsListPresenter);



    void inject(ForgetPasswordActivity forgetPasswordActivity);
    void inject(ForgetPasswordPresenter forgetPasswordPresenter);


    void inject(WorksListFragment worksListFragment);
    void inject(WorksListPresenter worksListPresenter);

    /////////////////////////////////////////////////////////
}
