package com.unicomg.baghdadmunicipality.di;

import android.app.Application;
import com.unicomg.baghdadmunicipality.helper.ConnectivityReceiver;
public class DaggerApplication extends Application {
    private AppComponent appComponent;
    public static DaggerApplication mDaggerApplication;
    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = initDagger(this);
        mDaggerApplication  = this;
    }

    protected AppComponent initDagger(DaggerApplication application) {
        return DaggerAppComponent.builder()
                .appModule(new AppModule(application))
                .build();
    }
    public AppComponent getAppComponent() {
        return appComponent;
    }
    public static DaggerApplication getDaggerApplication() {
        return mDaggerApplication;
    }
    public void setConnectivityListener(ConnectivityReceiver.ConnectivityReceiverListener listener) {
        ConnectivityReceiver.connectivityReceiverListener = listener;
    }

}