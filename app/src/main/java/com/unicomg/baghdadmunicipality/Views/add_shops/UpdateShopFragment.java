package com.unicomg.baghdadmunicipality.Views.add_shops;


import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.textfield.TextInputEditText;
import com.unicomg.baghdadmunicipality.R;
import com.unicomg.baghdadmunicipality.Views.shopslist.ShopsListFragment;
import com.unicomg.baghdadmunicipality.adapters.ShopActivitiesSpinnerAdapter;
import com.unicomg.baghdadmunicipality.data.models.shops.ShopModel;
import com.unicomg.baghdadmunicipality.data.models.shops_activities.ShopsActivitiesDetailsResponse;
import com.unicomg.baghdadmunicipality.di.DaggerApplication;
import com.unicomg.baghdadmunicipality.helper.GpsTracker;
import com.unicomg.baghdadmunicipality.helper.ScrollViewUIHelper;
import com.unicomg.baghdadmunicipality.helper.Utilities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UpdateShopFragment extends Fragment implements AdapterView.OnItemSelectedListener, NavigationView.OnNavigationItemSelectedListener   , AddShopView {

    private static String myshopid;
    @Inject
    AddShopPresenter addShopPresenter;
    private View mView;
    TextView edt_license_date,edt_license_end_date;
    TextInputEditText edt_owner_name  , edt_area  , shop_notes;
    private TextInputEditText edt_street,edt_locality,edt_width,edt_length,edt_alley,edt_employee_number,edt_shop_number,edt_floor_number,edt_long,edt_lat;
    private TextInputEditText edt_locality_license_number,edt_license_type,edt_billboard_name,edt_billboard_type,edt_billboard_width,edt_billboard_length,edt_billboard_height;
    private Spinner sp_type,sp_shop_activities_id,sp_billboard_font_type;
    private Button btn_previous,btn_next;
    private List<ShopsActivitiesDetailsResponse> shopsactivityoffline;
    private String shopactivityid;
    private String shopactivityname,shopactivitycode;

    private String  types [] ={"اختر نوع المحل*","ملك","ايجار"};
    private String fonttypes [] ={"اختر نوع الخط*","عربى","انجليزى"};
    private String fonttype,type;
    final Calendar myCalendar = Calendar.getInstance();
    final Calendar myCalendar2 = Calendar.getInstance();

    String owner_name,width,length,employee_number,floor_number,area,street,alley,locality,shop_numberr,billboard_height,longt,lat;
    String license_number,license_type,license_date,license_end_date,billboard_name,billboard_type,billboard_width,billboard_length;

    ImageView vimg_license_date,vimg_license_end_date;

    private GpsTracker gpsTracker;
    private double latitude;
    private double longitude;
    ImageButton getLocation;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    ProgressBar loading_indicator;
    @BindView(R.id.btn_back2)
    Button btn_back2;

    @BindView(R.id.btn_back)
    ImageButton btn_back;

    @BindView(R.id.scroll_view)
    ScrollView scrollView;

    ProgressDialog progressDialog;
    private int shopactivityid2;

    @BindView(R.id.spinner_bills_types)
    Spinner spinner_bills_types;

    private String billsTypesArray[] = {"اختر نوع لوحة المحل*", "لوحة الكترونية", "لوحة مكتوبة"};
    String billsTypesSelectedFromSpinner;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_update_shop, container, false);

        ButterKnife.bind(this, v);
        ((DaggerApplication) getActivity().getApplication()).getAppComponent().inject(this);
        addShopPresenter.onAttach(this);

        initctrls(v);
        initData();
        initialBillsTypesSpinner();

        getLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getLocation();
            }
        });
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };

        edt_license_date.setOnClickListener(new View.OnClickListener() {

            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(getActivity(), date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        vimg_license_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(getActivity(), date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        final DatePickerDialog.OnDateSetListener date2 = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar2.set(Calendar.YEAR, year);
                myCalendar2.set(Calendar.MONTH, monthOfYear);
                myCalendar2.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel2();
            }

        };

        edt_license_end_date.setOnClickListener(new View.OnClickListener() {

            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(getActivity(), date2, myCalendar2
                        .get(Calendar.YEAR), myCalendar2.get(Calendar.MONTH),
                        myCalendar2.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        vimg_license_end_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(getActivity(), date2, myCalendar2
                        .get(Calendar.YEAR), myCalendar2.get(Calendar.MONTH),
                        myCalendar2.get(Calendar.DAY_OF_MONTH)).show();
            }
        });



        btn_next.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                saveShopData();
            }
        });

        btn_back2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceView();
            }
        });


        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceView();
            }
        });


        progressDialog = new ProgressDialog(getContext());
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("تحميل ...");
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);

        return v;
    }

    private void initialBillsTypesSpinner() {


        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>
                (getActivity(), android.R.layout.simple_spinner_item, billsTypesArray);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout
                .simple_spinner_dropdown_item);
        spinner_bills_types.setAdapter(spinnerArrayAdapter);

        if (billsTypesSelectedFromSpinner != null) {
            int spinnerPosition = spinnerArrayAdapter.getPosition(billsTypesSelectedFromSpinner);
            spinner_bills_types.setSelection(spinnerPosition);
        }

        spinner_bills_types.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                billsTypesSelectedFromSpinner = (String) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                billsTypesSelectedFromSpinner = "اختر نوع لوحة المحل*";
            }
        });

    }



    private void initData() {
        List<ShopModel> shopDataByIdd = addShopPresenter.getShopDataByIdd(myshopid);
        ShopModel shopModel = shopDataByIdd.get(0);


        edt_owner_name.setText(shopModel.getOwner_name());
        edt_area.setText(shopModel.getArea());
        edt_street.setText(shopModel.getStreet());
        edt_locality.setText(shopModel.getLocality());
        edt_width.setText(shopModel.getWidth());
        edt_length.setText(shopModel.getLength());
        edt_alley.setText(shopModel.getAlley());
        edt_employee_number.setText(shopModel.getEmployee_number());
        edt_shop_number.setText(shopModel.getLocality_number());
        edt_floor_number.setText(shopModel.getFloor_number());
        edt_locality_license_number.setText(shopModel.getLicense_number());
        edt_license_type.setText(shopModel.getLicense_type());
        edt_license_date.setText(shopModel.getLicense_date());
        edt_license_end_date.setText(shopModel.getLicense_end_date());
        edt_billboard_name.setText(shopModel.getBillboard_name());
        edt_billboard_type.setText(shopModel.getBillboard_type());
        edt_billboard_width.setText(shopModel.getBillboard_width());
        edt_billboard_length.setText(shopModel.getBillboard_length());
        edt_billboard_height.setText(shopModel.getBillboard_height());
        edt_lat.setText(shopModel.getLatitude());
        edt_long.setText(shopModel.getLongitude());
        shop_notes.setText(shopModel.getShop_notes());
        edt_long.setText(shopModel.getLongitude());
        edt_lat.setText(shopModel.getLatitude());

        shopactivityid2 = Integer.parseInt(shopModel.getShop_activity_id());

        String billboard_font_type = shopModel.getBillboard_font_type();
        if(billboard_font_type.equals("1")){
            fonttype="عربى";
        }else{
            fonttype="انجليزى";
        }

        String typees = shopModel.getType();
        if(typees.equals("1")){
            type="ملك";
        }else{
            type="ايجار";
        }

        billsTypesSelectedFromSpinner = shopModel.getBillboard_type();
        initshopactivitiesspinner();
        inittypespinner();
        initfonttypespinner();


    }


    private void updateLabel() {
        //String myFormat = "MM/dd/yy"; //In which you need put here
        String myFormat = "yyyy-MM-dd";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        edt_license_date.setText(sdf.format(myCalendar.getTime()));
    }

    private void updateLabel2() {
        //String myFormat = "MM/dd/yy"; //In which you need put here
        String myFormat = "yyyy-MM-dd";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        edt_license_end_date.setText(sdf.format(myCalendar2.getTime()));
    }

    private void initfonttypespinner() {

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>
                (getActivity(), android.R.layout.simple_spinner_item,
                        fonttypes); //selected item will look like a spinner set from XML
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout
                .simple_spinner_dropdown_item);
        sp_billboard_font_type.setAdapter(spinnerArrayAdapter);

        if (fonttype != null) {
            int spinnerPosition = spinnerArrayAdapter.getPosition(fonttype);
            sp_billboard_font_type.setSelection(spinnerPosition);
        }

        sp_billboard_font_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                fonttype = (String) parent.getItemAtPosition(position);

                if(fonttype.equals("عربى")){
                    fonttype="1";
                }else if(fonttype.equals("انجليزى")){
                    fonttype="2";
                }
                else {
                    fonttype = "-1" ;
                }
                Log.e("fonttype_error", fonttype);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(getActivity(), "من فضلك اختر نوع الخط", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void inittypespinner() {
        Spinner spinner = new Spinner(getActivity());
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>
                (getActivity(), android.R.layout.simple_spinner_item,
                        types); //selected item will look like a spinner set from XML
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout
                .simple_spinner_dropdown_item);
        sp_type.setAdapter(spinnerArrayAdapter);

        if (type != null) {
            int spinnerPosition = spinnerArrayAdapter.getPosition(type);
            sp_type.setSelection(spinnerPosition);
        }

        sp_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                type = (String) parent.getItemAtPosition(position);

                if(type.equals("ملك")){
                    type="1";
                }else if (type.equals("ايجار")){
                    type="2";
                }
                else {
                    type="-1";
                }

                Log.e("type_error", type);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(getActivity(), "من فضلك اختر نوع المحل", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initshopactivitiesspinner() {

        shopsactivityoffline = addShopPresenter.getShopactivitiesoffline();


        shopsactivityoffline.add(0, new ShopsActivitiesDetailsResponse("-1", "اختر نشاط المحل*", "0"));

        ShopActivitiesSpinnerAdapter govSpinnerAdapter = new ShopActivitiesSpinnerAdapter(getActivity(), R.layout.spinneritem, shopsactivityoffline);
        sp_shop_activities_id.setAdapter(govSpinnerAdapter);

        //if (shopactivityid2 != null) {
            //int spinnerPosition = ShopActivitiesSpinnerAdapter.getPosition(shopactivityid);

        //}

        int index = 0 ;
        for (ShopsActivitiesDetailsResponse  officeResponseDetails: shopsactivityoffline) {
            if(officeResponseDetails.getId().equals(shopactivityid2+"")) {
                index = shopsactivityoffline.indexOf(officeResponseDetails) ;
            }
        }

        sp_shop_activities_id.setSelection(index);

        sp_shop_activities_id.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                shopactivityid = shopsactivityoffline.get(position).getId();
                shopactivitycode = shopsactivityoffline.get(position).getCode();
                shopactivityname = shopsactivityoffline.get(position).getName();
                Log.e("shopactivityid_error", shopactivityid);
                //citiesList.clear();


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(getActivity(), "من فضلك اختر نشاط المحل", Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void initctrls(View v) {
        edt_owner_name=v.findViewById(R.id.edt_owner_name);
        edt_area=v.findViewById(R.id.edt_area);
        sp_type=v.findViewById(R.id.shop_type);
        edt_street=v.findViewById(R.id.edt_street);
        sp_shop_activities_id=v.findViewById(R.id.sp_shop_activities_id);
        edt_locality=v.findViewById(R.id.edt_locality);
        edt_width=v.findViewById(R.id.edt_width_of_shop);
        edt_length=v.findViewById(R.id.edt_length_of_shop);
        edt_alley=v.findViewById(R.id.edt_alley);
        edt_employee_number=v.findViewById(R.id.edt_employee_number);

        edt_shop_number=v.findViewById(R.id.edt_building_number);
        edt_floor_number=v.findViewById(R.id.edt_floor_number);
        edt_locality_license_number=v.findViewById(R.id.edt_license_number);
        edt_license_type=v.findViewById(R.id.edt_license_type);
        edt_license_date=v.findViewById(R.id.edt_license_date);
        edt_license_end_date=v.findViewById(R.id.edt_license_end_date);
        edt_billboard_name=v.findViewById(R.id.edt_shop_name);

        edt_billboard_type=v.findViewById(R.id.edt_billboard_type);
        edt_billboard_width=v.findViewById(R.id.edt_billboard_width);
        edt_billboard_length=v.findViewById(R.id.edt_billboard_length);
        edt_billboard_height=v.findViewById(R.id.edt_billboard_height);
        sp_billboard_font_type=v.findViewById(R.id.sp_billboard_font_type);

        edt_lat=v.findViewById(R.id.edt_latidue);
        edt_long=v.findViewById(R.id.edt_longtidue);

        vimg_license_date=v.findViewById(R.id.vimg_license_date);
        vimg_license_end_date=v.findViewById(R.id.vimg_license_end_date);

        loading_indicator = v.findViewById(R.id.loading_indicator);

        btn_previous=v.findViewById(R.id.btn_back2);
        btn_next=v.findViewById(R.id.btn_next);
        getLocation = v.findViewById(R.id.get_location);
        shop_notes = v.findViewById(R.id.shop_notes);
    }

    public void getLocation(){

        edt_long.setText("33.312805");
        edt_lat.setText("44.361488");

        if(!Utilities.checkConnection(getContext())){
            edt_long.setText("33.312805");
            edt_lat.setText("44.361488");
            return;
        }
        else {

            gpsTracker = new GpsTracker(getActivity());
            if (gpsTracker.canGetLocation()) {
                latitude = gpsTracker.getLatitude();
                longitude = gpsTracker.getLongitude();

                longt = String.valueOf(longitude);
                lat = String.valueOf(latitude);

                edt_long.setText(longt);
                edt_lat.setText(lat);


            } else {
                gpsTracker.showSettingsAlert();
            }
        }
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        return false;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    // TODO: Rename and change types and number of parameters
    public static UpdateShopFragment newInstance(String param1) {
        UpdateShopFragment fragment = new UpdateShopFragment();
        Bundle args = new Bundle();
        myshopid = param1;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void showLoading() {

        //loading_indicator.setVisibility(View.VISIBLE);

        progressDialog.show();
    }

    @Override
    public void hideLoading() {
        //loading_indicator.setVisibility(View.GONE);
        progressDialog.dismiss();
    }

    @Override
    public void showMessage(String message, int mColor) {

    }

    @Override
    public void showMessageForSave(String message) {

    }

    @Override
    public void replaceView() {
        ShopsListFragment fragment = ShopsListFragment.newInstance("", "");
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction =
                fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout_container, fragment);
        //fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
        progressDialog.dismiss();
    }

    @Override
    public void onAttache() {

    }

    public static boolean isValidDate(String pDateString) throws ParseException {
        Date date = new SimpleDateFormat("MM/dd/yyyy").parse(pDateString);
        return new Date().before(date);
    }



    private void saveShopData() {
        owner_name=edt_owner_name.getText().toString();
        width=edt_width.getText().toString();
        length=edt_length.getText().toString();
        employee_number=edt_employee_number.getText().toString();
        floor_number=edt_floor_number.getText().toString();
        area=edt_area.getText().toString();
        street=edt_street.getText().toString();
        alley=edt_alley.getText().toString();
        locality=edt_locality.getText().toString();
        shop_numberr=edt_shop_number.getText().toString();
        license_number=edt_locality_license_number.getText().toString();
        license_type=edt_license_type.getText().toString();
        license_date=edt_license_date.getText().toString();
        license_end_date=edt_license_end_date.getText().toString();
        billboard_name=edt_billboard_name.getText().toString();
        billboard_type=edt_billboard_type.getText().toString();
        billboard_width=edt_billboard_width.getText().toString();
        billboard_length=edt_billboard_length.getText().toString();
        billboard_height=edt_billboard_height.getText().toString();
        lat=edt_lat.getText().toString();
        longt=edt_long.getText().toString();


        if (owner_name.isEmpty()){
            Toast.makeText(getActivity(), "أدخل اسم صاحب المحل", Toast.LENGTH_SHORT).show();
            edt_owner_name.setError("من فضلك أدخل اسم صاحب المحل");
            ScrollViewUIHelper.getInstance().scrollTo(edt_owner_name , scrollView);
        }

        else if(area.isEmpty()){
            Toast.makeText(getActivity(), "أدخل اسم المنطقة", Toast.LENGTH_SHORT).show();
            edt_area.setError("من فضلك أدخل اسم المنطقة");
            ScrollViewUIHelper.getInstance().scrollTo(edt_area , scrollView);
        }

        else if(type.equals("-1")){
            Toast.makeText(getActivity(), " اختر نوع المحل", Toast.LENGTH_SHORT).show();
            //sp_type.setError("من فضلك أدخل اسم المالك");
            ScrollViewUIHelper.getInstance().scrollTo(sp_type , scrollView);
        }

        else if(street.isEmpty()){
            Toast.makeText(getActivity(), " أدخل اسم الشارع", Toast.LENGTH_SHORT).show();
            edt_street.setError("من فضلك أدخل اسم الشارع");
            ScrollViewUIHelper.getInstance().scrollTo(edt_street , scrollView);
        }

        else if(shopactivityname.equals("اختر نشاط المحل*")){
            Toast.makeText(getActivity(), "اختر نوع نشاط المحل", Toast.LENGTH_SHORT).show();
            //sp_type.setError("من فضلك أدخل اسم المالك");
            ScrollViewUIHelper.getInstance().scrollTo(sp_shop_activities_id , scrollView);
        }

        else if(locality.isEmpty()){
            Toast.makeText(getActivity(), "أدخل اسم المحله", Toast.LENGTH_SHORT).show();
            edt_locality.setError("من فضلك أدخل اسم المحله");
            ScrollViewUIHelper.getInstance().scrollTo(edt_locality , scrollView);
        }

        else if(width.isEmpty()){
            Toast.makeText(getActivity(), "أدخل عرض المحل", Toast.LENGTH_SHORT).show();
            edt_width.setError("من فضلك أدخل عرض المحل");
            ScrollViewUIHelper.getInstance().scrollTo(edt_width , scrollView);
        }

        else if(Double.parseDouble(width) <=0 ){
            Toast.makeText(getActivity(), "العرض اقل من 1 ", Toast.LENGTH_SHORT).show();
            edt_width.setError("العرض اقل من 1");
            ScrollViewUIHelper.getInstance().scrollTo(edt_width , scrollView);
        }

//        else if(Double.parseDouble(width) > 999999.9){
//            Toast.makeText(getActivity(), "اقصى قيمة هى 999999.9", Toast.LENGTH_SHORT).show();
//            edt_width.setError("اقصى قيمة هى 999999.9");
//            ScrollViewUIHelper.getInstance().scrollTo(edt_width , scrollView);
//        }

        else if(length.isEmpty()){
            Toast.makeText(getActivity(), "أدخل طول المحل", Toast.LENGTH_SHORT).show();
            edt_length.setError("من فضلك أدخل طول المحل");
            ScrollViewUIHelper.getInstance().scrollTo(edt_length , scrollView);
        }


        else if(Double.parseDouble(length) <=0){
            Toast.makeText(getActivity(), "الطول اقل من 1", Toast.LENGTH_SHORT).show();
            edt_length.setError("الطول اقل من 1");
            ScrollViewUIHelper.getInstance().scrollTo(edt_length , scrollView);
        }


//        else if(Double.parseDouble(length) > 999999.9){
//            Toast.makeText(getActivity(), "اقصى قيمة هى 999999.9", Toast.LENGTH_SHORT).show();
//            edt_length.setError("اقصى قيمة هى 999999.9");
//            ScrollViewUIHelper.getInstance().scrollTo(edt_length , scrollView);
//        }


        else if(alley.isEmpty()){
            Toast.makeText(getActivity(), "أدخل اسم الزقاق", Toast.LENGTH_SHORT).show();
            edt_alley.setError("من فضلك أدخل اسم الزقاق");
            ScrollViewUIHelper.getInstance().scrollTo(edt_alley , scrollView);
        }


        else if(employee_number.isEmpty() ){
            Toast.makeText(getActivity(), "أدخل عدد الموظفين", Toast.LENGTH_SHORT).show();
            edt_employee_number.setError("من فضلك أدخل عدد الموظفين");
            ScrollViewUIHelper.getInstance().scrollTo(edt_employee_number , scrollView);
        }

        else if (employee_number.charAt(0) == '0') {
            edt_employee_number.setError("عدد الموظفين غير صحيح");
            Toast.makeText(getActivity(), "عدد الموظفين غير صحيح", Toast.LENGTH_SHORT).show();
            ScrollViewUIHelper.getInstance().scrollTo(edt_employee_number , scrollView);
        }


        else if(Integer.parseInt(employee_number) <= 0){
            Toast.makeText(getActivity(), "عدد الموظفين اقل من 1", Toast.LENGTH_SHORT).show();
            edt_employee_number.setError("عدد الموظفين اقل من 1");
            ScrollViewUIHelper.getInstance().scrollTo(edt_employee_number , scrollView);
        }

//        else if(Integer.parseInt(employee_number) > 1000000){
//            Toast.makeText(getActivity(), "اقصى قيمة هى 1000000", Toast.LENGTH_SHORT).show();
//            edt_employee_number.setError("اقصى قيمة هى 1000000");
//            ScrollViewUIHelper.getInstance().scrollTo(edt_employee_number , scrollView);
//        }

        else if(shop_numberr.isEmpty()){
            Toast.makeText(getActivity(), "أدخل رقم العقار", Toast.LENGTH_SHORT).show();
            edt_shop_number.setError("من فضلك أدخل رقم العقار");
            ScrollViewUIHelper.getInstance().scrollTo(edt_shop_number , scrollView);
        }

        else if(shop_numberr.charAt(0) == '0'){
            Toast.makeText(getActivity(), " رقم العقار غير صحيح", Toast.LENGTH_SHORT).show();
            edt_shop_number.setError("رقم العقار غير صحيح");
            ScrollViewUIHelper.getInstance().scrollTo(edt_shop_number , scrollView);
        }

        else if(Integer.parseInt(shop_numberr) <= 0 ){
            Toast.makeText(getActivity(), " رقم العقار اقل من 1", Toast.LENGTH_SHORT).show();
            edt_shop_number.setError(" رقم العقار اقل من 1 ");
            ScrollViewUIHelper.getInstance().scrollTo(edt_shop_number , scrollView);
        }


        else if(floor_number.isEmpty()){
            Toast.makeText(getActivity(), "أدخل عدد الطوابق", Toast.LENGTH_SHORT).show();
            edt_floor_number.setError("من فضلك أدخل عدد الطوابق");
            ScrollViewUIHelper.getInstance().scrollTo(edt_floor_number , scrollView);
        }

        else if(floor_number.charAt(0) == '0'){
            Toast.makeText(getActivity(), " عدد الطوابق غير صحيح", Toast.LENGTH_SHORT).show();
            edt_floor_number.setError("عدد الطوابق غير صحيح ");
            ScrollViewUIHelper.getInstance().scrollTo(edt_floor_number , scrollView);
        }


        else if(Integer.parseInt(floor_number) <= 0){
            Toast.makeText(getActivity(), "عدد الطوابق اقل من 1", Toast.LENGTH_SHORT).show();
            edt_floor_number.setError("عدد الطوابق اقل من 1");
            ScrollViewUIHelper.getInstance().scrollTo(edt_floor_number , scrollView);
        }


        else if(license_number.isEmpty()){
            Toast.makeText(getActivity(), "أدخل رقم الرخصة", Toast.LENGTH_SHORT).show();
            edt_locality_license_number.setError("أدخل رقم الرخصة");
            ScrollViewUIHelper.getInstance().scrollTo(edt_locality_license_number , scrollView);
        }

//        else if(license_number.equals("")){
//            Toast.makeText(getActivity(), "يجب ادخال  1 الي 9  ارقام ", Toast.LENGTH_SHORT).show();
//            edt_locality_license_number.setError("يجب ادخال  1 الي 9  ارقام ");
//            ScrollViewUIHelper.getInstance().scrollTo(edt_locality_license_number , scrollView);
//        }

//        else if(license_type.isEmpty()){
//            Toast.makeText(getActivity(), "أدخل نوع الرخصة", Toast.LENGTH_SHORT).show();
//            edt_license_type.setError("أدخل نوع الرخصة");
//            ScrollViewUIHelper.getInstance().scrollTo(edt_license_type , scrollView);
//        }

        else if ( license_date.equals("تاريخ اصدار الرخصة")) {
            Toast.makeText(getActivity(), " لا بد من اختيار تاريخ اصدار الرخصة   ", Toast.LENGTH_SHORT).show();
            ScrollViewUIHelper.getInstance().scrollTo(edt_license_date , scrollView);
        }

        else if (equal2( license_date)) {
            Toast.makeText(getActivity(), "تاريخ الاصدار يجب ان يكون مثل تاريخ اليوم او قبل اليوم", Toast.LENGTH_SHORT).show();
            ScrollViewUIHelper.getInstance().scrollTo(edt_license_end_date , scrollView);
        }

        else if ( license_end_date.equals("تاريخ انتهاء الرخصة")) {
            Toast.makeText(getActivity(), " لا بد من اختيار تاريخ انتهاء الرخصه", Toast.LENGTH_SHORT).show();
            ScrollViewUIHelper.getInstance().scrollTo(edt_license_end_date , scrollView);
        }



        else if (equal( license_date, license_end_date)) {
            Toast.makeText(getActivity(), "تاريخ الانتهاء  لابد ان يكون بعد  تاريخ الاصدار", Toast.LENGTH_SHORT).show();
            ScrollViewUIHelper.getInstance().scrollTo(edt_license_end_date , scrollView);

        }



        else if(billboard_name.isEmpty()){
            Toast.makeText(getActivity(), "أدخل اسم المحل", Toast.LENGTH_SHORT).show();
            edt_billboard_name.setError("أدخل اسم المحل");
            ScrollViewUIHelper.getInstance().scrollTo(edt_billboard_name , scrollView);
        }

        else if(billsTypesSelectedFromSpinner.equals("اختر نوع لوحة المحل*")){
            Toast.makeText(getActivity(), "اختر نوع لوحة المحل ", Toast.LENGTH_SHORT).show();
            edt_billboard_type.setError("اختر نوع لوحة المحل ");
            ScrollViewUIHelper.getInstance().scrollTo(edt_billboard_type , scrollView);
        }

        else if(billboard_width.isEmpty()){
            Toast.makeText(getActivity(), "أدخل عرض الوحة", Toast.LENGTH_SHORT).show();
            edt_billboard_width.setError("أدخل عرض الوحة");
            ScrollViewUIHelper.getInstance().scrollTo(edt_billboard_width , scrollView);
        }

        else if(Double.parseDouble(billboard_width) <= 0){
            Toast.makeText(getActivity(), "عرض لوحة المحل يجب ان يكون اكبر من صفر ", Toast.LENGTH_SHORT).show();
            edt_billboard_width.setError("عرض لوحة المحل يجب ان يكون اكبر من صفر ");
            ScrollViewUIHelper.getInstance().scrollTo(edt_billboard_width , scrollView);
        }


        else if(billboard_length.isEmpty()){
            Toast.makeText(getActivity(), "أدخل طول الوحة", Toast.LENGTH_SHORT).show();
            edt_billboard_length.setError("أدخل طول الوحة");
            ScrollViewUIHelper.getInstance().scrollTo(edt_billboard_length , scrollView);
        }


        else if(Double.parseDouble(billboard_length)  <= 0 ){
            Toast.makeText(getActivity(), "الطول اقل من 1 ", Toast.LENGTH_SHORT).show();
            edt_billboard_length.setError("الطول اقل من 1 ");
            ScrollViewUIHelper.getInstance().scrollTo(edt_billboard_length , scrollView);
        }


        else if(billboard_height.isEmpty()){
            Toast.makeText(getActivity(), "أدخل ارتفاع الوحة", Toast.LENGTH_SHORT).show();
            edt_billboard_height.setError("أدخل ارتفاع الوحة");
            ScrollViewUIHelper.getInstance().scrollTo(edt_billboard_height , scrollView);
        }

        else if(Double.parseDouble(billboard_height) <= 0){
            Toast.makeText(getActivity(), "الارتفاع اقل من 1 ", Toast.LENGTH_SHORT).show();
            edt_billboard_height.setError("الارتفاع اقل من 1 ");
            ScrollViewUIHelper.getInstance().scrollTo(edt_billboard_height , scrollView);
        }


        else if(fonttype.equals("-1")){
            Toast.makeText(getActivity(), "اختر نوع الخط", Toast.LENGTH_SHORT).show();
            //sp_type.setError("أدخل اسم المالك");
            ScrollViewUIHelper.getInstance().scrollTo(sp_billboard_font_type , scrollView);
        }


        else {

            ShopModel shopModel = new ShopModel(myshopid,
                    owner_name,  type,  shopactivityid,
                    width,  length,  employee_number,
                    floor_number,  area,  street,  alley,
                    locality,  shop_numberr,  license_number,
                    license_type,  license_date,  license_end_date,
                    billboard_name,  billsTypesSelectedFromSpinner,  billboard_width,
                    billboard_length, billboard_height, fonttype
                    ,longt,lat , "0" , shop_notes.getText().toString()

            );


            long rowInserted = addShopPresenter.updateShopDataoffline(shopModel);

            if (rowInserted > 0) {
                if (Utilities.checkConnection(getContext())) {
                    showLoading();
                    addShopPresenter.sendOneUpdatedShopData(shopModel);
                    //replaceView();
                } else {
                    Toast.makeText(getContext(), "تم تعديل بيانات المحل بدون مزامنة", Toast.LENGTH_LONG).show();
                    replaceView();
                }

            }
            else  {
                Toast.makeText(getContext(), "حدث خطأ ما ", Toast.LENGTH_LONG).show();
            }

        }

    }

    @Override
    public void onResume() {
        super.onResume();
    //    getLocation();
    }

    public boolean equal(String startDate , String endDate){

        boolean asd  = false ;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = null;
        try {
            date1 = sdf.parse(startDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date date2 = null;
        try {
            date2 = sdf.parse(endDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        if (date1.compareTo(date2) == 0 || date1.compareTo(date2)> 0  ) {
            asd  = true ;
        }


        return asd ;
    }
    public boolean equal2(String startDate ) {

        boolean asd = false;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = null;
        try {
            date1 = sdf.parse(startDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }



        if (  date1.compareTo(new Date()) > 0) {
            asd = true;
        }


        return asd;
    }

}

