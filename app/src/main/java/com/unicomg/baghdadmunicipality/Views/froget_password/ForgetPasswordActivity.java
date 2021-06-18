package com.unicomg.baghdadmunicipality.Views.froget_password;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.unicomg.baghdadmunicipality.R;
import com.unicomg.baghdadmunicipality.Views.login.LoginActivity;
import com.unicomg.baghdadmunicipality.data.models.forgetpass.ForgetPasswordModel;
import com.unicomg.baghdadmunicipality.di.DaggerApplication;
import com.unicomg.baghdadmunicipality.helper.ConnectivityReceiver;

import javax.inject.Inject;

public class ForgetPasswordActivity extends AppCompatActivity implements ForgetPasswordView, ConnectivityReceiver.ConnectivityReceiverListener {

    @Inject
    ForgetPasswordPresenter forgetPasswordPresenter;

    ProgressDialog progressDialog  ;
    TextInputEditText email;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        email = findViewById(R.id.email);
        button = findViewById(R.id.btn_send);
        ((DaggerApplication) getApplication()).getAppComponent().inject(this);
        forgetPasswordPresenter.onAttach(this);
        DaggerApplication.getDaggerApplication().setConnectivityListener(this);

        progressDialog = new ProgressDialog(ForgetPasswordActivity.this);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("تحميل ...");
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               login();

            }
        });
    }

    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {

    }

    @Override
    public void showLoading() {
        progressDialog.show();
    }

    @Override
    public void hideLoading() {
        progressDialog.dismiss();
    }

    @Override
    public void showMessage(String message, int mColor) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAttache() {

    }

    @Override
    public void onDetach() {

    }

    public boolean validate() {
        boolean valid = true;
        String emailValue = email.getText().toString().trim();

        if (emailValue.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(emailValue).matches()) {
            email.setError("خطأ في البريد الالكتروني ");
            valid = false;
        } else {
            email.setError(null);
        }

        return valid;
    }

    public void login() {
        if (!validate()) { return;}
        String emailValue = email.getText().toString().trim();
        ForgetPasswordModel forgetPasswordModel = new ForgetPasswordModel(emailValue);
        forgetPasswordPresenter.sendEmail(forgetPasswordModel);
    }
}
