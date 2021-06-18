package com.unicomg.baghdadmunicipality.Views.add_violation;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.core.content.FileProvider;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;
import com.unicomg.baghdadmunicipality.BuildConfig;
import com.unicomg.baghdadmunicipality.R;
import com.unicomg.baghdadmunicipality.Views.mainscreen.MainActivity;
import com.unicomg.baghdadmunicipality.Views.shopslist.ShopsListFragment;
import com.unicomg.baghdadmunicipality.Views.violations_list.ViolationsListFragment;
import com.unicomg.baghdadmunicipality.data.models.category.Category;
import com.unicomg.baghdadmunicipality.data.models.serverViolations.ServerViolation;
import com.unicomg.baghdadmunicipality.data.models.shops.ShopModel;
import com.unicomg.baghdadmunicipality.data.models.violation.ViolationImage;
import com.unicomg.baghdadmunicipality.data.models.violation.ViolationModel;
import com.unicomg.baghdadmunicipality.di.DaggerApplication;
import com.unicomg.baghdadmunicipality.helper.ConnectivityReceiver;
import com.unicomg.baghdadmunicipality.helper.ScrollViewUIHelper;
import com.unicomg.baghdadmunicipality.helper.Utilities;
import com.unicomg.baghdadmunicipality.helper.Utility;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;


public class AddViolationFragment extends Fragment implements AddViolationView, ConnectivityReceiver.ConnectivityReceiverListener, RoomItemClick {
    @Inject
    AddViolationPresenter mainPresenter;

    @BindView(R.id.shop_name)
    Spinner shop_name;
    @BindView(R.id.violence_group)
    Spinner violence_group;
    @BindView(R.id.violence_name)
    Spinner violence_name;
    @BindView(R.id.action_name)
    TextView action_name;
    @BindView(R.id.txt_violence_value)
    TextView txt_violence_value;
    @BindView(R.id.notes)
    TextInputEditText notes;
    @BindView(R.id.btn_save)
    Button btn_save;
    @BindView(R.id.btn_print)
    Button btn_print;

    @BindView(R.id.shop_name_print)
    TextView shop_name_print;
    @BindView(R.id.owner_name)
    TextView owner_name;
    @BindView(R.id.activity_id)
    TextView activity_id;
    @BindView(R.id.linceceecc)
    TextView linceceecc;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.violation_print)
    TextView violation_print;
    @BindView(R.id.action_print)
    TextView action_print;

    @BindView(R.id.price)
    TextView price;
    @BindView(R.id.violencetitle)
    TextView violencetitle;


    @BindView(R.id.llPdf)
    LinearLayout llPdf;

    @BindView(R.id.add_violation_img)
    LinearLayout add_violation_img;

    @BindView(R.id.btn_back)
    ImageButton btn_back;


    @BindView(R.id.recyclerViewInDoorPhoto)
    RecyclerView recyclerViewInDoorPhoto;

    @BindView(R.id.scroll_view_violations)
    ScrollView scroll_view_violations ;

    private int REQUEST_CAMERA = 0, SELECT_FILE = 1;
    private String userChoosenTask;
    private Uri picUri;

    ShopModel selectedShop;
    Category selectedCategory;
    ServerViolation selectedServerViolation;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;

    Bitmap bitmap,bitmap2;
    ViolationImage violationImage;
    private OnFragmentInteractionListener mListener;
    private Intent intent;
    ProgressDialog progressDialog;

    public AddViolationFragment() {

    }


    public static AddViolationFragment newInstance(String param1, String param2) {
        AddViolationFragment fragment = new AddViolationFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        ((DaggerApplication) getActivity().getApplication()).getAppComponent().inject(this);
        mainPresenter.onAttach(this);


    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_add_violation, container, false);
        ButterKnife.bind(this, view);

        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        dialog = new Dialog(getContext());


        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savedata();
            }
        });

        add_violation_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });


        btn_print.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {

                printData();

            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceView();
            }
        });
        mainPresenter.getShops();
        mainPresenter.getCategories();

        List<ViolationImage> violationImageList = mainPresenter.getViolationImages();
        GridLayoutManager linearLayoutManager = new GridLayoutManager(getContext(), 4);
        recyclerViewInDoorPhoto.setLayoutManager(linearLayoutManager);
        recyclerViewInDoorPhoto.setHasFixedSize(true);

        if (violationImageList.size() > 0) {
            ViolationImagesAdapter adapter = new ViolationImagesAdapter(getContext(), violationImageList, this, 0);
            recyclerViewInDoorPhoto.setAdapter(adapter);
        }

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("تحميل ...");
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);


        return view;
    }


    public static Bitmap loadBitmapFromView(View v, int width, int height) {
        Bitmap b = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(b);
        v.draw(c);

        return b;
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onAttache() {

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void showMessage(String message, int mColor) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setShops(ArrayList<ShopModel> shops) {

        shops.add(0, new ShopModel("-1" ,"" , "" ,"" ,"" ,"" ,"" ,"" ,"" ,"" ,"" ,""
                ,"" ,"" , "" ,"" ,"" ,"اسم المحل*" ,"" ,
                "" ,"" ,"" ,"" , ""  ,"","" , ""));

        ShopAdapterSpinner shopActivitiesSpinnerAdapter = new ShopAdapterSpinner(getActivity(), R.layout.spinneritem, shops);
        shop_name.setAdapter(shopActivitiesSpinnerAdapter);
        shop_name.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                selectedShop = shops.get(position) ;

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(getActivity(), "من فضلك اختر اسم المحل", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void setGroups(ArrayList<Category> groups) {
        groups.add(0 , new Category("-1" , "مجموعة المخالفات*" , "-1")) ;
    CategoryAdapterSpinner shopActivitiesSpinnerAdapter = new CategoryAdapterSpinner(getActivity(), R.layout.spinneritem, groups);
        violence_group.setAdapter(shopActivitiesSpinnerAdapter);
        violence_group.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                selectedCategory = groups.get(position) ;
                mainPresenter.getServerViolations(selectedCategory.getId());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(getActivity(), "من فضلك اختر مجموعه المخالفات", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void setViolations(ArrayList<ServerViolation> serverViolations) {

        serverViolations.add(0 , new ServerViolation("-1" , "-1" , "اسم المخالفة*" , "" , "00.00")) ;
        ServerViolationAdapterSpinner  serverViolationAdapterSpinner  = new ServerViolationAdapterSpinner(getActivity(), R.layout.spinneritem, serverViolations);
        violence_name.setAdapter(serverViolationAdapterSpinner);
        violence_name.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                selectedServerViolation = serverViolations.get(position) ;
                action_name.setText(selectedServerViolation.getType());
                txt_violence_value.setText(selectedServerViolation.getFinancial_value());

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(getActivity(), "من فضلك اختر  اسم المخالفة ", Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public void afterDelete(List<ViolationImage> violationImages) {

    }

    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {

    }

    @Override
    public void onItemClickDelete(int position, ViolationImage roomImage, View view) {

    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }

    public boolean validate() {

        boolean valid = true;

        if (selectedShop.getShop_id().equals("-1")) {
            showMessage("اختر اسم المحل ", 0);
            ScrollViewUIHelper.getInstance().scrollTo(shop_name , scroll_view_violations);
            valid = false;
        }

       else if (selectedCategory.getId().equals("-1")) {
            showMessage("اختر مجموعة المخالفات ", 0);
            ScrollViewUIHelper.getInstance().scrollTo(violence_group , scroll_view_violations);
            valid = false;
        }


       else if (selectedServerViolation.getId().equals("-1")) {
            showMessage("اختر اسم المخالفة ", 0);
            ScrollViewUIHelper.getInstance().scrollTo(violence_name , scroll_view_violations);
            valid = false;
        }

//        if (mainPresenter.getImagesCount() < 1) {
//            showMessage("لا بد من ادخال صورة علي الاقل .", 0);
//            valid = false;
//        }
//        if (notes.getText().toString().isEmpty()) {
//
//            notes.setError("الملاحظات حقل مطلوب ");
//            valid = false;
//        }



        return valid;
    }


    public void savedata() {
        if (!validate()) {
            return;
        }

        long time = System.currentTimeMillis();
        //  final int random = new Random().nextInt(9999999);

        ViolationModel violationModel ;
        if(notes.getText().toString().equals("")){

            violationModel = new ViolationModel(String.valueOf(time),
                    selectedShop.getShop_id(),selectedShop.getBillboard_name() , selectedCategory.getId(), selectedServerViolation.getId(),
                    selectedServerViolation.getType(), "لا توجد ملاحظات");
        }

        else {
            violationModel = new ViolationModel(String.valueOf(time),
                    selectedShop.getShop_id(),selectedShop.getBillboard_name() , selectedCategory.getId(), selectedServerViolation.getId(),
                    selectedServerViolation.getType(), notes.getText().toString());
        }


        Log.e("ViolationModel", violationModel.toString());

        long rowInserted = mainPresenter.saveLocally(violationModel);

        if (rowInserted > 0) {
            mainPresenter.updateViolationImagesId(violationModel.getId());

//               if (Utilities.checkConnection(getContext())) {
//                   shopLoading();
//                   mainPresenter.sendOneViolation(violationModel);
//                   //replaceView();
//               } else {
//                   Toast.makeText(getContext(), "تم حفظ بيانات المخالفة بدون مزامنة ", Toast.LENGTH_LONG).show();
//                   replaceView();
//               }

            Toast.makeText(getContext(), "تم حفظ بيانات المخالفة بدون مزامنة ", Toast.LENGTH_LONG).show();


            final Dialog dialog2 = new Dialog(getContext());
            dialog2.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog2.setCancelable(false);
            dialog2.setContentView(R.layout.dialog);

            TextView text = (TextView) dialog2.findViewById(R.id.text_dialog);
            text.setText("تم الحفظ هل تريد طباعة اشعار ؟");

            Button dialogButtonYes = (Button) dialog2.findViewById(R.id.ok);

            dialogButtonYes.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                @Override
                public void onClick(View v) {
                    printData();
                    dialog2.dismiss();
                    replaceView();

                }
            });

            Button dialogButtonCancel = (Button) dialog2.findViewById(R.id.cancel);
            dialogButtonCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog2.dismiss();
                    replaceView();
                }
            });

            dialog2.show();

//            android.app.AlertDialog.Builder builder;
//            builder = new android.app.AlertDialog.Builder(getContext());
//            builder.setMessage();
//            builder.setPositiveButton("نعم", new DialogInterface.OnClickListener() {
//                @RequiresApi(api = Build.VERSION_CODES.KITKAT)
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                    printData();
//                    dialog.dismiss();
//                    replaceView();
//                }
//            });
//            builder.setNegativeButton("لا", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//
//                    replaceView();
//
//                }
//            });
//
//            builder.show();

           }


//        else {
//            final int random2 = new Random().nextInt(9999999) + 20;
//            ViolationModel violationModel2 = new ViolationModel(random2 + "" + Constants.getuser_Id(getContext()),
//                    selectedShop.getShop_id(), selectedCategory.getId(), selectedServerViolation.getId(),
//                    selectedServerViolation.getType(), notes.getText().toString());
//
//            long rowInserted2 = mainPresenter.saveLocally(violationModel2);
//            if (rowInserted2 > 0) {
//                Toast.makeText(getContext(), "تم حفظ بيانات المخالفة", Toast.LENGTH_LONG).show();
//                mainPresenter.updateViolationImagesId(violationModel2.getId());
//                replaceView();
//            }
//        }



        /*
        ConnectivityManager cm =
                (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null && activeNetwork.isConnectedOrConnecting()) {
            mainPresenter.saveToServer(violationModel);

        } else {
            mainPresenter.saveLocally(violationModel);
        }*/
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void printData() {
        if (!validate()) {
            return;
        }

        llPdf.setVisibility(View.VISIBLE);

        String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());

        SimpleDateFormat dateFormat = new SimpleDateFormat(" HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        String time = dateFormat.format(cal.getTime());

        String s="التاريخ " + date + " الوقت " + time ;

        violencetitle.setText(s);



        shop_name_print.setText(selectedShop.getBillboard_name());

        owner_name.setText(selectedShop.getOwner_name());

        activity_id.setText(selectedShop.getShop_activity_id());

        linceceecc.setText(selectedShop.getLicense_number());

        title.setText(selectedShop.getArea() + " " + selectedShop.getAlley() + " "  + selectedShop.getStreet() + " " + selectedShop.getLocality_number());

        violation_print.setText(selectedServerViolation.getName());

        action_print.setText(selectedServerViolation.getType());
        price.setText(selectedServerViolation.getFinancial_value());
        bitmap2 = loadBitmapFromView(llPdf, 700, 650);
        //bitmap = loadBitmapFromView(llPdf, 300, 500);
        createPdf();

       // showAddDialog();
    }

     Dialog dialog  ;
    private void selectImage() {

        dialog.setCancelable(true);
        dialog.setContentView(R.layout.camera_or_gallary);
        TextView pic = dialog.findViewById(R.id.pic);
        ImageButton btnPic = dialog.findViewById(R.id.picImage);
        ImageButton btnChoose = dialog.findViewById(R.id.chooseImage);
        Window window = dialog.getWindow();
        window.setLayout(DrawerLayout.LayoutParams.WRAP_CONTENT, DrawerLayout.LayoutParams.WRAP_CONTENT);
        window.setGravity(Gravity.CENTER);

        final boolean result = Utility.checkPermission(getActivity());

        btnPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (result) {
                    cameraIntent();
                }
                dialog.cancel();
            }
        });
        dialog.show();

        btnChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (result) {
                    galleryIntent();
                }
                dialog.cancel();

            }
        });
        dialog.show();

    }

    private void galleryIntent() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);//
        startActivityForResult(Intent.createChooser(intent, "Select File"), SELECT_FILE);
    }

    private void cameraIntent() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_CAMERA);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == SELECT_FILE) {
                if (getSizeOfImage(data) <= 1024) {
                    try {
                        if (mainPresenter.getImagesCount() < 10) {
                            //Toast.makeText(getActivity(), getSizeOfImage(data) + "", Toast.LENGTH_SHORT).show();
                            //  Log.e(">>>>>>>>>>", getSizeOfImage(data) + "");
                            picUri = onSelectFromGalleryResult(data);
                            Bitmap bitmap1 = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(), picUri);

                            violationImage = new ViolationImage("-1", bitmap1, "....");
                            mainPresenter.saveViolationImage(violationImage);
                        } else {
                            Toast.makeText(getContext(), "عشرة صور  فقط  ", Toast.LENGTH_SHORT).show();
                        }

                    } catch (Exception e) {
                    }

                } else {
                    Toast.makeText(getContext(), "الصوره حجمها كبير لابد ان لا يزيد عن ا ميجا ", Toast.LENGTH_SHORT).show();
                }

            } else if (requestCode == REQUEST_CAMERA) {
                try {
                    picUri = onCaptureImageResult(data);

                    bitmap = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(), picUri);

                    violationImage = new ViolationImage("-1", bitmap, "333");
                    mainPresenter.saveViolationImage(violationImage);

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

        }


    }

    private Uri onCaptureImageResult(Intent data) {

        Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        thumbnail.compress(Bitmap.CompressFormat.JPEG, 100, bytes);

        File destination = new File(Environment.getExternalStorageDirectory(),
                System.currentTimeMillis() + ".jpg");

        FileOutputStream fo;
        try {
            destination.createNewFile();
            fo = new FileOutputStream(destination);
            fo.write(bytes.toByteArray());
            fo.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        picUri = getImageUri(getContext(), thumbnail);
        //ivImage.setImage_bitmap(thumbnail);
        return picUri;

    }

    @SuppressWarnings("deprecation")
    private Uri onSelectFromGalleryResult(Intent data) {

        Bitmap bm = null;
        if (data != null) {
            try {
                bm = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(), data.getData());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        picUri = getImageUri(getContext(), bm);
        return picUri;
    }

    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }

    public double getSizeOfImage(Intent data) {
        long dataSize = 0;
        Uri uri = data.getData();
        String scheme = uri.getScheme();

        if (scheme.equals(ContentResolver.SCHEME_CONTENT)) {
            try {
                InputStream fileInputStream = getContext().getContentResolver().openInputStream(uri);
                dataSize = fileInputStream.available();

            } catch (Exception e) {
                e.printStackTrace();
            }

        } else if (scheme.equals(ContentResolver.SCHEME_FILE)) {
            String path = uri.getPath();
            File f;
            try {
                f = new File(path);
                dataSize = f.length();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return (dataSize / 1024);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        dialog.dismiss();
        mainPresenter.deleteAllImageWithNegativeOne();
    }

    @Override
    public void afterAdd(List<ViolationImage> violationImages) {
        GridLayoutManager linearLayoutManager = new GridLayoutManager(getContext(), 4);
        recyclerViewInDoorPhoto.setLayoutManager(linearLayoutManager);
        recyclerViewInDoorPhoto.setHasFixedSize(true);
        if (violationImages.size() > 0) {
            ViolationImagesAdapter adapter = new ViolationImagesAdapter(getContext(), violationImages, this, 0);
            recyclerViewInDoorPhoto.setAdapter(adapter);
        }
    }


    @Override
    public void replaceView() {
        ViolationsListFragment fragment = ViolationsListFragment.newInstance("", "");
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction =
                fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout_container, fragment);
        //fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }


    @Override
    public void shopLoading() {
        progressDialog.show();
    }

    @Override
    public void hideLoading2() {
        progressDialog.dismiss();
    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void createPdf() {

        WindowManager wm = (WindowManager) getActivity().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics displaymetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        float hight = displaymetrics.heightPixels ;
        float width = displaymetrics.widthPixels ;



        int convertHighet = (int) hight, convertWidth = (int) width;
        PdfDocument document = new PdfDocument();
        PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(convertWidth, convertHighet, 1).create();
        PdfDocument.Page page = document.startPage(pageInfo);

        Canvas canvas = page.getCanvas();

        Paint paint = new Paint();
        canvas.drawPaint(paint);

        bitmap2 = Bitmap.createScaledBitmap(bitmap2, convertWidth, convertHighet, true);

        paint.setColor(Color.BLUE);
        canvas.drawBitmap(bitmap2, 0, 0, null);
        document.finishPage(page);

        // write the document content
        String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        String targetPdf = "/sdcard/violations_"+selectedShop.getBillboard_name()+ " " + date + " .pdf";
        File filePath;
        filePath = new File(targetPdf);
        try {
            document.writeTo(new FileOutputStream(filePath));
            Toast.makeText(getContext(),    "تم طباعة الملف بنجاح في " ,  Toast.LENGTH_SHORT).show();
            //llPdf.setVisibility(View.GONE);


        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(getContext(), "حدث خطا اثناء الطباعة " + e.toString(), Toast.LENGTH_LONG).show();
        }

        document.close();

        llPdf.setVisibility(View.GONE);

        //previewpdf();

        //openGeneratedPDF();

        String myfile=Environment.getExternalStorageDirectory()+"/violations_"+selectedShop.getBillboard_name()+ " " + date + " .pdf";

        openPdfFile(myfile);


    }


    private void openPdfFile(String pdffile) {
        String mimeType =  MimeTypeMap.getSingleton().getMimeTypeFromExtension(MimeTypeMap.getFileExtensionFromUrl(pdffile.toString()));
        Uri uri = FileProvider.getUriForFile(getActivity(), BuildConfig.APPLICATION_ID + ".provider",new File(pdffile));

        try {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            //intent.setDataAndType(Uri.fromFile(new File(pdffile)));
            //intent.setDataAndType(Uri.fromFile(new File(pdffile)),mimeType);
            intent.setDataAndType(uri,mimeType);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            // no Activity to handle this kind of files
        }
    }



}
