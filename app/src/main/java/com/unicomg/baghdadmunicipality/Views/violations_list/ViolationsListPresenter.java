package com.unicomg.baghdadmunicipality.Views.violations_list;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import com.unicomg.baghdadmunicipality.baseClass.BasePresenter;
import com.unicomg.baghdadmunicipality.data.ApisClient.ApiInterface;
import com.unicomg.baghdadmunicipality.data.LocalSqlite.ItemDbHelper;
import com.unicomg.baghdadmunicipality.data.models.violation.VilationImageResponse;
import com.unicomg.baghdadmunicipality.data.models.violation.ViolatImage2;
import com.unicomg.baghdadmunicipality.data.models.violation.ViolationImage;
import com.unicomg.baghdadmunicipality.data.models.violation.ViolationModel;
import com.unicomg.baghdadmunicipality.data.models.violation.ViolationResponse;
import com.unicomg.baghdadmunicipality.di.DaggerApplication;
import com.unicomg.baghdadmunicipality.helper.Constants;
import com.unicomg.baghdadmunicipality.helper.Utilities;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ViolationsListPresenter implements BasePresenter<ViolationView> {

    ViolationView mView;

    boolean isLoaded = false;
    @Inject
    ApiInterface mApiInterface;
    @Inject
    Context mContext;
    @Inject
    ItemDbHelper mItemDbHelper;
    private boolean saveViolationImage = false;


    @Override
    public void onAttach(ViolationView view) {
        mView = view;
        mView.onAttache();
    }

    @Override
    public void onDetach() {
        mView = null;
    }

    public ViolationsListPresenter(Context context){
        ((DaggerApplication)context).getAppComponent().inject(this);
    }

    public void getViolations() {

        mView.showLoading();
        ArrayList<ViolationModel> violations = mItemDbHelper.getViolation(Constants.getUserIdByElamary(mContext));
        mView.showViolations(violations);
        mView.hideLoading();
    }


    void sendOneViolation(ViolationModel violationModel ) {

        if (!Utilities.checkConnection(mContext)) {

            mView.showMessage("لا يوجد اتصال بالانترنت من فضلك حاول مرة اخرى في وقت لاحق", 0);
            mView.hideLoading();
            return;
        }


        mView.showLoading();
        String accesstoken = "Bearer " + Constants.getuserToken(mContext);
        mApiInterface.saveViolation(accesstoken, violationModel
        )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ViolationResponse>() {
                    @Override
                    public final void onCompleted() {
                    }

                    @Override
                    public final void onError(Throwable e) {

                        Log.e("add_Violation", e.getMessage().toString());
                        Toast.makeText(mContext, "من فضلك حاول مره اخري !", Toast.LENGTH_SHORT).show();
                        mView.hideLoading();
                    }

                    @Override
                    public final void onNext(ViolationResponse response) {

                        Log.e("Add Violation response", response.getMessage());
                        if (response.getStatus().equals("success")) {
                            if (mItemDbHelper.getViolationImages(violationModel.getId()).size() > 0 && mItemDbHelper.getViolationImages(violationModel.getId()) != null) {
                                saveViolationImages(violationModel.getId(), convertImageToString(mItemDbHelper.getViolationImages(violationModel.getId())));
                            } else {
                                mItemDbHelper.deleteOneViolation(violationModel.getId());
                                getViolations();
                                mView.hideLoading();
                            }
                        } else {
                            Log.e("<<<<<>>>>>>" , response.getMessage());
                            Toast.makeText(mContext, "من فضلك حاول مره اخري  ", Toast.LENGTH_SHORT).show();
                            mView.hideLoading();

                        }

                    }
                });
    }
    public void saveViolationImages(final String ViolationId, final List<ViolatImage2> violationImages) {

        final int[] counter = {0};
        if (violationImages != null && violationImages.size() != 0) {
            for (final ViolatImage2 violationImage : violationImages) {
                if (!Utilities.checkConnection(mContext)) {

                    mView.showMessage(  "لا يوجد اتصال بالانترنت من فضلك حاول مرة اخرى في وقت لاحق"    , 0);
                    return;
                }

                mView.showLoading();
                mApiInterface.saveViolationImage("Bearer " + Constants.getuserToken(mContext), violationImage)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subscriber<VilationImageResponse>() {
                            @Override
                            public final void onCompleted() {

                                counter[0]++;
                                if (counter[0] == violationImages.size()) {
                                    if (saveViolationImage) {
                                        mItemDbHelper.deleteOneViolation(ViolationId);
                                        mItemDbHelper.deleteAllViolationImageByViolationId(ViolationId);
                                        getViolations();
                                        mView.hideLoading();
                                        mView.showMessage("تم حفظ بيانات المخالفه ", 0);
                                    }

                                }
                            }

                            @Override
                            public final void onError(Throwable e) {

                                mView.hideLoading();
                                Log.e("saveViolationImage", e.getMessage());
                                saveViolationImage = false;

                            }

                            @Override
                            public final void onNext(VilationImageResponse response) {
                                if (response.getStatus().equals("success")) {
                                    saveViolationImage = true;
                                } else {
                                    saveViolationImage = false;
                                    mView.hideLoading();
                                    mView.showMessage("من فضلك حاول مرة أخري", 0);
                                }

                                Log.e("save_violationImages:", response.getMessage().toString());
                            }
                        });
            }

        } else {

            mItemDbHelper.deleteOneViolation(ViolationId);
            getViolations();
            mView.hideLoading();
        }


    }
    public List<ViolatImage2> convertImageToString(List<ViolationImage> violationImages) {

        List<ViolatImage2> listSketches = new ArrayList<>();
        Bitmap bitmapSketchDetails;
        ByteArrayOutputStream byteArrayOutputStreamSketches;
        byte[] byteArraySketches;
        String encodedSketch;
        for (int i = 0; i < violationImages.size(); i++) {
            ViolatImage2 violatImage2 = new ViolatImage2();
            bitmapSketchDetails = violationImages.get(i).getSource();
            byteArrayOutputStreamSketches = new ByteArrayOutputStream();
            bitmapSketchDetails.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStreamSketches);
            byteArraySketches = byteArrayOutputStreamSketches.toByteArray();
            encodedSketch = Base64.encodeToString(byteArraySketches, Base64.DEFAULT);
            violatImage2.setSource("data:image/jpeg;base64,"+encodedSketch);
            violatImage2.setDescription(violationImages.get(i).getDescription());
            violatImage2.setViolation_monitoring_id(violationImages.get(i).getViolation_monitoring_id());
            listSketches.add(violatImage2);
        }

        return listSketches;
    }

}
