package com.unicomg.baghdadmunicipality.Views.login;

import android.app.Dialog;
import android.content.ClipData.Item;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.material.textfield.TextInputEditText;
import com.unicomg.baghdadmunicipality.R;
import com.unicomg.baghdadmunicipality.Views.froget_password.ForgetPasswordActivity;
import com.unicomg.baghdadmunicipality.Views.mainscreen.MainActivity;
import com.unicomg.baghdadmunicipality.di.DaggerApplication;
import com.unicomg.baghdadmunicipality.helper.ConnectivityReceiver;
import com.unicomg.baghdadmunicipality.helper.Constants;

import java.util.ArrayList;

import javax.inject.Inject;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class LoginActivity extends AppCompatActivity implements LoginView, ConnectivityReceiver.ConnectivityReceiverListener {

    @Inject
    LoginPresenter loginPresenter;

    TextInputEditText edit_user_name,edtPass;
    Button btn_login;
    ProgressBar pbar;
    LinearLayout ll;

    private static final int PERMISSION_REQUEST_CODE = 200;
    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edit_user_name=findViewById(R.id.edt_un);
        edtPass=findViewById(R.id.edt_pw);
        btn_login=findViewById(R.id.btn_login);
        pbar=findViewById(R.id.pbar);
        ll=findViewById(R.id.ll);
        dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setTitle("تحميل البيانات .....");
        dialog.setContentView(R.layout.custom_dialog);

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        ((DaggerApplication)getApplication()).getAppComponent().inject(this);
        loginPresenter.onAttach(this);
        DaggerApplication.getDaggerApplication().setConnectivityListener(this);


        if (!checkPermission()) {
            requestPermission();
        }

        if(! Constants.getuserId(this).isEmpty()){
            Intent i=new Intent(this,MainActivity.class);
            startActivity(i);
        }

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email,pw;
                email=edit_user_name.getText().toString();
                pw=edtPass.getText().toString();

                if(email.isEmpty() && pw.isEmpty()){
                    Toast.makeText(LoginActivity.this, "لابد من ادخال جميع الحقول ", Toast.LENGTH_SHORT).show();
                }
                else if(email.isEmpty()){
                    Toast.makeText(LoginActivity.this, "لابد من ادخال بريدك الالكترونى ", Toast.LENGTH_SHORT).show();
                    edit_user_name.setError("لابد من ادخال بريدك الالكترونى ");
                }else if(pw.isEmpty()){
                    Toast.makeText(LoginActivity.this, "لابد من ادخال كلمة السر ", Toast.LENGTH_SHORT).show();
                    edtPass.setError("لابد من ادخال كلمة السر");
                }else{
                    loginPresenter.getLogin(email,pw,ll,dialog);
                }
            }
        });
    }

    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(LoginActivity.this)
                .setMessage(message)
                .setPositiveButton("تم", okListener)
                .setNegativeButton("الغاء", null)
                .create()
                .show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0) {

                    boolean locationAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    boolean cameraAccepted = grantResults[1] == PackageManager.PERMISSION_GRANTED;
                    boolean readStorageAccepted = grantResults[2] == PackageManager.PERMISSION_GRANTED;
                    boolean writeStorageAccepted = grantResults[3] == PackageManager.PERMISSION_GRANTED;

                    if (locationAccepted && cameraAccepted && readStorageAccepted && writeStorageAccepted)
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            if (shouldShowRequestPermissionRationale(ACCESS_FINE_LOCATION)) {
                                showMessageOKCancel("التطبيق يحتاج بعض الصلاحيات",
                                        new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                                    requestPermissions(new String[]{ACCESS_FINE_LOCATION, CAMERA, READ_EXTERNAL_STORAGE, WRITE_EXTERNAL_STORAGE},
                                                            PERMISSION_REQUEST_CODE);
                                                }
                                            }
                                        });
                                return;
                            }
                        }

                }

                break;
        }
    }


    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMessage(String message, int mColor) {
        Toast.makeText(LoginActivity.this, ""+message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void updateList(ArrayList<Item> items) {

    }

    @Override
    public void openProjectsActivity() {
        Intent i=new Intent(this, MainActivity.class);
        startActivity(i);
    }

    @Override
    public void onAttache() {

    }

    @Override
    public void onDetach() {

    }

    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        loginPresenter.checkConnection(isConnected);
    }


    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        startActivity(intent);
    }

    private boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(getApplicationContext(), ACCESS_FINE_LOCATION);
        int result1 = ContextCompat.checkSelfPermission(getApplicationContext(), CAMERA);
        int result2 = ContextCompat.checkSelfPermission(getApplicationContext(), READ_EXTERNAL_STORAGE);
        int result3 = ContextCompat.checkSelfPermission(getApplicationContext(), WRITE_EXTERNAL_STORAGE);

        return result == PackageManager.PERMISSION_GRANTED &&
                result1 == PackageManager.PERMISSION_GRANTED &&
                result2 == PackageManager.PERMISSION_GRANTED &&
                result3 == PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermission() {

        ActivityCompat.requestPermissions(this, new String[]{ACCESS_FINE_LOCATION, CAMERA, READ_EXTERNAL_STORAGE, WRITE_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);

    }

    public void openForgetPasswordActivity(View view) {
        startActivity(new Intent(LoginActivity.this, ForgetPasswordActivity.class));
    }
}
