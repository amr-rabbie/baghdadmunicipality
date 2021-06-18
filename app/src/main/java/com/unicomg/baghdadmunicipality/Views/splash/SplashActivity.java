package com.unicomg.baghdadmunicipality.Views.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.unicomg.baghdadmunicipality.BuildConfig;
import com.unicomg.baghdadmunicipality.R;
import com.unicomg.baghdadmunicipality.Views.login.LoginActivity;
import com.unicomg.baghdadmunicipality.di.DaggerApplication;
import com.unicomg.baghdadmunicipality.helper.ConnectivityReceiver;

import javax.inject.Inject;

import butterknife.ButterKnife;

public class SplashActivity extends AppCompatActivity implements SplashView, ConnectivityReceiver.ConnectivityReceiverListener {

    @Inject
    SplashPresenter mainPresenter;
    ProgressBar progressBar;
    private TextView txtVersion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        progressBar = findViewById(R.id.progressBar2);
        ButterKnife.bind(this);

        ((DaggerApplication) getApplication()).getAppComponent().inject(this);
        mainPresenter.onAttach(this);

//        if(!Constants.getuserId(this).isEmpty()){
//            mainPresenter.getShopsData();
//        }
//        else {
//            mainPresenter.getShopsActivities();
//        }


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent mainIntent = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(mainIntent);
                finish();
            }
        }, 3000);


        txtVersion = findViewById(R.id.textView_version);
        txtVersion.setText("2019 © Unicomg " + BuildConfig.VERSION_NAME);

    }

    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        mainPresenter.checkConnection(isConnected);
    }


    @Override
    public void showMessage(String message, int mColor) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void shopActivitiesSetProgress() {
        progressBar.setProgress(33, true);
    }

    @Override
    public void allCategoriesSetProgress() {
        progressBar.setProgress(70, true);
    }

    @Override
    public void allViolationsSetProgress() {
        progressBar.setProgress(100, true);
    }

    @Override
    public void allShopsSetProgress() {
        progressBar.setProgress(25, true);
    }

    @Override
    public void startLoginActivity() {

        Intent mainIntent = new Intent(SplashActivity.this, LoginActivity.class);
        startActivity(mainIntent);
    }


    @Override
    public void onAttache() {
    }

    @Override
    public void onDetach() {
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        startActivity(intent);
    }
}