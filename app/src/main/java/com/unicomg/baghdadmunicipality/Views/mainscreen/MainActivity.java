package com.unicomg.baghdadmunicipality.Views.mainscreen;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.unicomg.baghdadmunicipality.R;
import com.unicomg.baghdadmunicipality.Views.add_bill_board.AddBillFragment;
import com.unicomg.baghdadmunicipality.Views.add_bill_board.UpdateBillBoardFragment;
import com.unicomg.baghdadmunicipality.Views.add_violation.AddViolationFragment;
import com.unicomg.baghdadmunicipality.Views.bill_board_list.BillListFragment;
import com.unicomg.baghdadmunicipality.Views.login.LoginActivity;
import com.unicomg.baghdadmunicipality.Views.scheduled_work.WorksListFragment;
import com.unicomg.baghdadmunicipality.Views.shopslist.ShopsListFragment;
import com.unicomg.baghdadmunicipality.Views.violations_list.ViolationsListFragment;
import com.unicomg.baghdadmunicipality.helper.Constants;

import java.util.concurrent.Callable;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.functions.Function;

public class MainActivity extends AppCompatActivity implements ShopsListFragment.OnFragmentInteractionListener , BillListFragment.OnFragmentInteractionListener ,
        ViolationsListFragment.OnFragmentInteractionListener, AddBillFragment.OnFragmentInteractionListener, AddViolationFragment.OnFragmentInteractionListener  ,
        UpdateBillBoardFragment.OnFragmentInteractionListener , WorksListFragment.OnFragmentInteractionListener {

    @BindView(R.id.rootView)
    ScrollView rootView;

    @BindView(R.id.shops_btn)
    LinearLayout shops_btn;
    @BindView(R.id.bills_btn)
    LinearLayout bills_btn;
    @BindView(R.id.violation_btn)
    LinearLayout violation_btn;

    @BindView(R.id.vwork_btn)
    LinearLayout vwork_btn;

    @BindView(R.id.log_out)
    Button log_out ;
    @BindView(R.id.user_name)
    TextView user_name ;

    @BindView(R.id.txt_shop_btn)
    TextView txt_shop_btn ;

    @BindView(R.id.txt_bills_btn)
    TextView txt_bills_btn ;

    @BindView(R.id.txt_work_btn)
    TextView txt_work_btn ;



    @BindView(R.id.txt_violations_btn)
    TextView txt_violations_btn ;


    @BindView(R.id.img_shops_btn)
    ImageView img_shops_btn ;

    @BindView(R.id.img_bills_btn)
    ImageView img_bills_btn ;

    @BindView(R.id.img_violations_btn)
    ImageView img_violations_btn ;

    @BindView(R.id.img_work_btn)
    ImageView img_work_btn ;


    @BindView(R.id.view_shop_btn)
    View view_shop_btn ;

    @BindView(R.id.view_bills_btn)
    View view_bills_btn ;

    @BindView(R.id.view_violations_btn)
    View view_violations_btn ;

    @BindView(R.id.view_work_btn)
    View view_work_btn ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        rootView.getLayoutParams().height = this.getWindowManager().getDefaultDisplay().getHeight();
        ShopsListFragment fragment = ShopsListFragment.newInstance("", "");
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction =
                fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout_container, fragment);
        //fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();


        shops_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.frameLayout_container);
                if( !(currentFragment instanceof ShopsListFragment || currentFragment instanceof ViolationsListFragment || currentFragment instanceof BillListFragment || currentFragment instanceof  WorksListFragment)){
                    showDialogForShops("سوف يتم فقد جميع البيانات المُدخلة في هذه الصفحة،  ");
                }else{
                    gotoShopsFragment();
                }
            }
        });

        bills_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.frameLayout_container);
                if( !(currentFragment instanceof ShopsListFragment || currentFragment instanceof ViolationsListFragment || currentFragment instanceof BillListFragment || currentFragment instanceof  WorksListFragment)){
                    showDialogForBills("سوف يتم فقد جميع البيانات المُدخلة في هذه الصفحة،  ");
                }else{
                    gotoBillsFragment();
                }

            }
        });
        violation_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.frameLayout_container);
                if( !(currentFragment instanceof ShopsListFragment || currentFragment instanceof ViolationsListFragment || currentFragment instanceof BillListFragment || currentFragment instanceof  WorksListFragment)){
                    showDialogForViolation("سوف يتم فقد جميع البيانات المُدخلة في هذه الصفحة،  ");
                }else{
                    gotoViolation();
                }


            }
        });


        vwork_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.frameLayout_container);
                if( !(currentFragment instanceof ShopsListFragment || currentFragment instanceof ViolationsListFragment || currentFragment instanceof BillListFragment || currentFragment instanceof  WorksListFragment)){
                    showDialogForWorks("سوف يتم فقد جميع البيانات المُدخلة في هذه الصفحة،  ");
                }else{
                    gotoWorksFragment();
                }
            }
        });



        log_out.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                showDialogForLogout("سوف يتم فقد جميع البيانات المُدخلة في هذه الصفحة،");
            }
        });

        user_name.setText(Constants.getuserName(this));


    }


    public void showDialogForLogout(String msg){
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog);

        TextView text = (TextView) dialog.findViewById(R.id.text_dialog);
        text.setText(msg);

        Button dialogButtonYes = (Button) dialog.findViewById(R.id.ok);

        dialogButtonYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Constants.saveLoginData("", "", "", ""  , "",  "" , ""  , MainActivity.this);
                Intent i = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(i);
                dialog.dismiss();
            }
        });

        Button dialogButtonCancel = (Button) dialog.findViewById(R.id.cancel);
        dialogButtonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();

    }

    public void showDialogForViolation(String msg){
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog);

        TextView text = (TextView) dialog.findViewById(R.id.text_dialog);
        text.setText(msg);

        Button dialogButtonYes = (Button) dialog.findViewById(R.id.ok);

        dialogButtonYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                gotoViolation();
                dialog.dismiss();
            }
        });

        Button dialogButtonCancel = (Button) dialog.findViewById(R.id.cancel);
        dialogButtonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();

    }

    void gotoViolation(){
        ViolationsListFragment fragment = ViolationsListFragment.newInstance("", "");
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction =
                fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout_container, fragment);
        //fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();



        img_shops_btn.setImageResource(R.drawable.gray_ic_icon_store);
        txt_shop_btn.setTextColor(getResources().getColor(R.color.ramady));
        view_shop_btn.setBackgroundColor(getResources().getColor(R.color.ramady));

        img_bills_btn.setImageResource(R.drawable.gray_ic_icon_ad);
        txt_bills_btn.setTextColor(getResources().getColor(R.color.ramady));
        view_bills_btn.setBackgroundColor(getResources().getColor(R.color.ramady));


        img_violations_btn.setImageResource(R.drawable.white_ic_icon_violation);
        txt_violations_btn.setTextColor(getResources().getColor(R.color.light_wieht));
        view_violations_btn.setBackgroundColor(getResources().getColor(R.color.light_wieht));

        img_work_btn.setImageResource(R.drawable.gray_ic_icon_calendar);
        txt_work_btn.setTextColor(getResources().getColor(R.color.ramady));
        view_work_btn.setBackgroundColor(getResources().getColor(R.color.ramady));
    }

    public void showDialogForBills(String msg){
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog);

        TextView text = (TextView) dialog.findViewById(R.id.text_dialog);
        text.setText(msg);

        Button dialogButtonYes = (Button) dialog.findViewById(R.id.ok);

        dialogButtonYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoBillsFragment();
                dialog.dismiss();
            }
        });

        Button dialogButtonCancel = (Button) dialog.findViewById(R.id.cancel);
        dialogButtonCancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();

    }

    void gotoBillsFragment(){
        BillListFragment fragment = BillListFragment.newInstance("", "");
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction =
                fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout_container, fragment);
        //fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

        img_shops_btn.setImageResource(R.drawable.gray_ic_icon_store);
        txt_shop_btn.setTextColor(getResources().getColor(R.color.ramady));
        view_shop_btn.setBackgroundColor(getResources().getColor(R.color.ramady));

        img_bills_btn.setImageResource(R.drawable.white_ic_icon_ad);
        txt_bills_btn.setTextColor(getResources().getColor(R.color.light_wieht));
        view_bills_btn.setBackgroundColor(getResources().getColor(R.color.light_wieht));

        img_violations_btn.setImageResource(R.drawable.gray_ic_icon_violation);
        txt_violations_btn.setTextColor(getResources().getColor(R.color.ramady));
        view_violations_btn.setBackgroundColor(getResources().getColor(R.color.ramady));

        img_work_btn.setImageResource(R.drawable.gray_ic_icon_calendar);
        txt_work_btn.setTextColor(getResources().getColor(R.color.ramady));
        view_work_btn.setBackgroundColor(getResources().getColor(R.color.ramady));
    }

    public void showDialogForShops(String msg){
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog);

        TextView text = (TextView) dialog.findViewById(R.id.text_dialog);
        text.setText(msg);

        Button dialogButtonYes = (Button) dialog.findViewById(R.id.ok);

        dialogButtonYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoShopsFragment();
                dialog.dismiss();
            }
        });

        Button dialogButtonCancel = (Button) dialog.findViewById(R.id.cancel);
        dialogButtonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();

    }

    void gotoShopsFragment(){
        ShopsListFragment fragment = ShopsListFragment.newInstance("", "");
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction =
                fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout_container, fragment);
        //fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

        img_shops_btn.setImageResource(R.drawable.white_ic_icon_store);
        txt_shop_btn.setTextColor(getResources().getColor(R.color.light_wieht));
        view_shop_btn.setBackgroundColor(getResources().getColor(R.color.light_wieht));

        img_bills_btn.setImageResource(R.drawable.gray_ic_icon_ad);
        txt_bills_btn.setTextColor(getResources().getColor(R.color.ramady));
        view_bills_btn.setBackgroundColor(getResources().getColor(R.color.ramady));

        img_violations_btn.setImageResource(R.drawable.gray_ic_icon_violation);
        txt_violations_btn.setTextColor(getResources().getColor(R.color.ramady));
        view_violations_btn.setBackgroundColor(getResources().getColor(R.color.ramady));

        img_work_btn.setImageResource(R.drawable.gray_ic_icon_calendar);
        txt_work_btn.setTextColor(getResources().getColor(R.color.ramady));
        view_work_btn.setBackgroundColor(getResources().getColor(R.color.ramady));
    }


    public void showDialogForWorks(String msg){
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog);

        TextView text = (TextView) dialog.findViewById(R.id.text_dialog);
        text.setText(msg);

        Button dialogButtonYes = (Button) dialog.findViewById(R.id.ok);

        dialogButtonYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoWorksFragment();
                dialog.dismiss();
            }
        });

        Button dialogButtonCancel = (Button) dialog.findViewById(R.id.cancel);
        dialogButtonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();

    }

    void gotoWorksFragment(){
        WorksListFragment fragment = WorksListFragment.newInstance("", "");
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction =
                fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout_container, fragment);
        //fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

        img_work_btn.setImageResource(R.drawable.white_ic_icon_calendar);
        txt_work_btn.setTextColor(getResources().getColor(R.color.light_wieht));
        view_work_btn.setBackgroundColor(getResources().getColor(R.color.light_wieht));

        img_shops_btn.setImageResource(R.drawable.gray_ic_icon_store);
        txt_shop_btn.setTextColor(getResources().getColor(R.color.ramady));
        view_shop_btn.setBackgroundColor(getResources().getColor(R.color.ramady));

        img_bills_btn.setImageResource(R.drawable.gray_ic_icon_ad);
        txt_bills_btn.setTextColor(getResources().getColor(R.color.ramady));
        view_bills_btn.setBackgroundColor(getResources().getColor(R.color.ramady));

        img_violations_btn.setImageResource(R.drawable.gray_ic_icon_violation);
        txt_violations_btn.setTextColor(getResources().getColor(R.color.ramady));
        view_violations_btn.setBackgroundColor(getResources().getColor(R.color.ramady));

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        startActivity(intent);
    }
}
