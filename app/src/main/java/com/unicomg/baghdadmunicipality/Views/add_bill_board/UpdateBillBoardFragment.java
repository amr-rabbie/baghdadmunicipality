package com.unicomg.baghdadmunicipality.Views.add_bill_board;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.textfield.TextInputEditText;
import com.unicomg.baghdadmunicipality.R;
import com.unicomg.baghdadmunicipality.Views.bill_board_list.BillListFragment;
import com.unicomg.baghdadmunicipality.data.models.billboard.BillboardModel;
import com.unicomg.baghdadmunicipality.data.models.billboard.BillboardModel2;
import com.unicomg.baghdadmunicipality.di.DaggerApplication;
import com.unicomg.baghdadmunicipality.helper.ConnectivityReceiver;
import com.unicomg.baghdadmunicipality.helper.GpsTracker;
import com.unicomg.baghdadmunicipality.helper.ScrollViewUIHelper;
import com.unicomg.baghdadmunicipality.helper.Utilities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link UpdateBillBoardFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UpdateBillBoardFragment extends Fragment   implements AddBillView, ConnectivityReceiver.ConnectivityReceiverListener{
    public String generatedId;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static String myid;
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    @Inject
    AddBillPresenter mainPresenter;
    @BindView(R.id.bill_owner_name)
    TextInputEditText bill_owner_name;
    @BindView(R.id.bill_name)
    TextInputEditText bill_name;
    @BindView(R.id.bill_type)
    TextInputEditText bill_type;
    @BindView(R.id.bill_length)
    TextInputEditText bill_length;
    @BindView(R.id.bill_width)
    TextInputEditText bill_width;
    @BindView(R.id.bill_height)
    TextInputEditText bill_height;
    @BindView(R.id.bill_font_type)
    Spinner bill_font_type;
    @BindView(R.id.bill_area)
    TextInputEditText bill_area;
    @BindView(R.id.bill_street)
    TextInputEditText bill_street;
    @BindView(R.id.bill_locality)
    TextInputEditText bill_locality;
    @BindView(R.id.bill_alley)
    TextInputEditText bill_alley;
    @BindView(R.id.bill_building_number)
    TextInputEditText bill_building_number;
    @BindView(R.id.bill_license)
    TextInputEditText bill_license;
    @BindView(R.id.bill_license_number)
    TextInputEditText bill_license_number;
    @BindView(R.id.bill_begin_date)
    LinearLayout bill_begin_date;
    @BindView(R.id.bill_end_date)
    LinearLayout bill_end_date;
    @BindView(R.id.txt_license_start_date)
    TextView txt_license_start_date;
    @BindView(R.id.txt_license_end_date)
    TextView txt_license_end_date;
    @BindView(R.id.btn_save)
    Button btn_save;
    @BindView(R.id.btn_back2)
    Button btn_back2;
    @BindView(R.id.btn_back)
    ImageButton btn_back;
    @BindView(R.id.longitude)
    TextInputEditText longitude;
    @BindView(R.id.latitude)
    TextInputEditText latitude;

    @BindView(R.id.get_location)
    ImageButton get_location;

    @BindView(R.id.loading_indicator)
    ProgressBar loading_indicator;

    @BindView(R.id.srcroll)
    ScrollView srcroll;
    ProgressDialog progressDialog;
    DatePickerDialog datePickerDialog;
    Calendar cal;
    String fonttype;
    private String fonttypes[] = {"اختر نوع الخط", "عربى", "انجليزى"};
    int startYear = 0;
    int startMonth = 0;
    int startDay = 0;


    @BindView(R.id.spinner_linces_types)
    Spinner spinner_linces_types;
    private String licenseTypesArray [] = {  "اختر نوع الرخصة*"   , "رخصة مؤقتة" , "رخصة دائمة" } ;
    String licenseTypesSelectedFromSpinner ;

    @BindView(R.id.spinner_bills_types)
    Spinner spinner_bills_types;

    private String billsTypesArray [] = {  "اختر نوع اللوحة*"   , "لوحة الكترونية" , "لوحة مكتوبة" } ;
    String billsTypesSelectedFromSpinner ;


    public UpdateBillBoardFragment() {
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
        View view = inflater.inflate(R.layout.fragment_update_bill_board, container, false);
        ButterKnife.bind(this, view);


        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savedata();
            }
        });

        get_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getLocation();
            }
        });

        bill_begin_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                final int day = c.get(Calendar.DAY_OF_MONTH);
                final Date cc = Calendar.getInstance().getTime();
                datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                        if (compareDate(view.getYear() + "-" + (view.getMonth() + 1) + "-" + view.getDayOfMonth(), cc.getYear() + "-" + (cc.getMonth() + 1) + "-" + cc.getDay()) == 0
                                && compareDate(view.getYear() + "-" + (view.getMonth() + 1) + "-" + view.getDayOfMonth(), cc.getYear() + "-" + (cc.getMonth() + 1) + "-" + cc.getDay()) < 0) {
                            showMessage("تاريخ الاصدار يجب ان يكون اقل من تاريخ اليوم .");

                        } else {
                            String date = view.getYear() + "-" + (view.getMonth() + 1) + "-" + view.getDayOfMonth();
                            txt_license_start_date.setText(date);
                            startYear = year;
                            startMonth = month;
                            startDay = day;
                        }


                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });

        bill_end_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);

                datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                        if (compareDate(view.getYear() + "-" + (view.getMonth() + 1) + "-" + view.getDayOfMonth(), startYear + "-" + (startMonth + 1) + "-" + startDay) == 0
                                && compareDate(view.getYear() + "-" + (view.getMonth() + 1) + "-" + view.getDayOfMonth(), startYear + "-" + (startMonth + 1) + "-" + startDay) < 0) {
                            showMessage("تاريخ الانتهاء يجب ان يكون اكبر من تاريخ اليوم و الاصدار");
                        } else {
                            String date = view.getYear() + "-" + (view.getMonth() + 1) + "-" + view.getDayOfMonth();
                            txt_license_end_date.setText(date);
                        }

                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });

        btn_back2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BillListFragment fragment = BillListFragment.newInstance("", "");
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction =
                        fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frameLayout_container, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });


        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BillListFragment fragment = BillListFragment.newInstance("", "");
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction =
                        fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frameLayout_container, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });


        progressDialog = new ProgressDialog(getContext());
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("تحميل ...");
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);

        initData();
        initFontTypeSpinner();
        initialBillsSpinner();
        initialBillsSpinner2();


        return view;

    }

    private void initData() {
        List<BillboardModel2> billBoardByIdd = mainPresenter.getBillBoardByIdd(myid);
        BillboardModel2 billboardModel2 = billBoardByIdd.get(0);

        generatedId = billboardModel2.getBillId();
        bill_owner_name.setText(billboardModel2.getOwner_name());
        bill_name.setText(billboardModel2.getBillboard_name());
        bill_type.setText(billboardModel2.getBillboard_type());
        bill_width.setText(billboardModel2.getWidth());
        bill_length.setText(billboardModel2.getLength());
        bill_height.setText(billboardModel2.getHeight());
        bill_area.setText(billboardModel2.getArea());
        bill_street.setText(billboardModel2.getStreet());
        bill_locality.setText(billboardModel2.getLocality());
        bill_alley.setText(billboardModel2.getAilley());
        bill_building_number.setText(billboardModel2.getBulding_number());
        bill_license.setText(billboardModel2.getBillboard_license());
        bill_license_number.setText(billboardModel2.getBillboard_license_number());
        txt_license_start_date.setText(billboardModel2.getLicense_date());
        txt_license_end_date.setText(billboardModel2.getLicense_end_date());
        longitude.setText(billboardModel2.getLongitude());
        latitude.setText(billboardModel2.getLatitude());
        String billboard_font_type = billboardModel2.getFont_language();
        if(billboard_font_type.equals("1")){
            fonttype="عربى";
        }else{
            fonttype="انجليزى";
        }
        licenseTypesSelectedFromSpinner  = billboardModel2.getBillboard_license();
        billsTypesSelectedFromSpinner = billboardModel2.getBillboard_type() ;


    }
    private void initialBillsSpinner() {

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>
                (getActivity(), android.R.layout.simple_spinner_item, licenseTypesArray);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout
                .simple_spinner_dropdown_item);
        spinner_linces_types.setAdapter(spinnerArrayAdapter);

        if (licenseTypesSelectedFromSpinner != null) {
            int spinnerPosition = spinnerArrayAdapter.getPosition(licenseTypesSelectedFromSpinner);
            spinner_linces_types.setSelection(spinnerPosition);
        }

        spinner_linces_types.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                licenseTypesSelectedFromSpinner = (String) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                licenseTypesSelectedFromSpinner = "اختر نوع الرخصة*" ;
            }
        });

    }
    private void initialBillsSpinner2() {

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
                billsTypesSelectedFromSpinner = "اختر نوع اللوحة*" ;
            }
        });

    }

    private int compareDate(String d1, String d2) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); //For declaring values in new date objects. use same date format when creating dates
        try {
            Date date1 = sdf.parse(d1);
            Date date2 = sdf.parse(d2);
            return date1.compareTo(date2); // = 0 ,  < -1 ,  > 1
        } catch (ParseException e) {
            e.printStackTrace();
        } finally {
            return 0;
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
    public void showMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading() {
        progressDialog.show();
        // loading_indicator.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progressDialog.dismiss();
        // loading_indicator.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {

    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }

    public boolean validate() {

        boolean valid = true;
        String ownerName = bill_owner_name.getText().toString().trim();
        String billName = bill_name.getText().toString().trim();
        String billType = bill_type.getText().toString().trim();
        String billWidth = bill_width.getText().toString().trim();
        String billLength = bill_length.getText().toString().trim();
        String billHeight = bill_height.getText().toString().trim();
        String area = bill_area.getText().toString().trim();
        String street = bill_street.getText().toString().trim();
        String locality = bill_locality.getText().toString().trim();
        String alley = bill_alley.getText().toString().trim();
        String buildingnumber = bill_building_number.getText().toString().trim();
        String lincise = bill_license.getText().toString().trim();
        String lisinceNumber = bill_license_number.getText().toString().trim();
        String startDate = txt_license_start_date.getText().toString().trim();
        String endDate = txt_license_end_date.getText().toString().trim();


        if (ownerName.isEmpty()) {
            bill_owner_name.setError("ادخل اسم المالك");
            showMessage("ادخل اسم المالك");
            ScrollViewUIHelper.getInstance().scrollTo(bill_owner_name , srcroll);
            valid = false;
        }



        else if (area.isEmpty() ) {
            bill_area.setError("ادخل اسم المنطقة");
            showMessage("ادخل اسم المنطقة");
            ScrollViewUIHelper.getInstance().scrollTo(bill_area , srcroll);
            valid = false;
        }



        else if (billName.isEmpty() ) {
            bill_name.setError("ادخل اسم اللوحة");
            showMessage("ادخل اسم اللوحة");
            ScrollViewUIHelper.getInstance().scrollTo(bill_name , srcroll);
            valid = false;
        }

        else if (street.isEmpty()) {
            bill_street.setError("ادخل اسم الشارع");
            showMessage("ادخل اسم الشارع");
            valid = false;
            ScrollViewUIHelper.getInstance().scrollTo(bill_street , srcroll);
        }

        else if (billsTypesSelectedFromSpinner.equals("اختر نوع اللوحة*") ) {
            bill_type.setError("اختر نوع اللوحة");
            showMessage("اختر نوع اللوحة");
            ScrollViewUIHelper.getInstance().scrollTo(spinner_bills_types , srcroll);
            valid = false;
        }


        else if (locality.isEmpty()) {
            bill_locality.setError("ادخل اسم المحلة");
            showMessage("ادخل اسم المحلة");
            valid = false;
            ScrollViewUIHelper.getInstance().scrollTo(bill_locality , srcroll);
        }


        else if (billLength.isEmpty() ) {
            bill_length.setError("ادخل طول اللوحة");
            showMessage("ادخل طول اللوحة");
            ScrollViewUIHelper.getInstance().scrollTo(bill_length , srcroll);
            valid = false;
        }

        else if ( Double.parseDouble(billLength)<= 0 ) {

            bill_length.setError("طول اللوحة اقل من 1");
            showMessage("طول اللوحة اقل من 1");
            ScrollViewUIHelper.getInstance().scrollTo(bill_length , srcroll);
            valid = false;
        }

        else  if (billWidth.isEmpty() ) {
            bill_width.setError("ادخل عرض اللوحة");
            showMessage("ادخل عرض اللوحة");
            ScrollViewUIHelper.getInstance().scrollTo(bill_width , srcroll);
            valid = false;
        }

        else if (Double.parseDouble(billWidth) <=  0) {
            bill_width.setError("عرض اللوحة اقل من 1");
            showMessage("عرض اللوحة اقل من 1");
            ScrollViewUIHelper.getInstance().scrollTo(bill_width , srcroll);
            valid = false;
        }

        else  if (alley.isEmpty()) {
            bill_alley.setError("ادخل اسم الزقاق");
            showMessage("ادخل اسم الزقاق");
            valid = false;
            ScrollViewUIHelper.getInstance().scrollTo(bill_alley , srcroll);
        }

        else if (billHeight.isEmpty() ) {
            bill_height.setError(" ادخل ارتفاع اللوحة");
            showMessage("ادخل ارتفاع اللوحة");
            ScrollViewUIHelper.getInstance().scrollTo(bill_height , srcroll);
            valid = false;
        }

        else  if (Double.parseDouble(billHeight) <= 0 ) {
            bill_height.setError("ارتفاع اللوحة اقل من 1");
            showMessage("ارتفاع اللوحة اقل من 1");
            ScrollViewUIHelper.getInstance().scrollTo(bill_height , srcroll);
            valid = false;
        }

        else if (buildingnumber.isEmpty()) {
            bill_building_number.setError("ادخل رقم العقار");
            showMessage("ادخل رقم العقار");
            valid = false;
            ScrollViewUIHelper.getInstance().scrollTo(bill_building_number , srcroll);
        }

        else if (buildingnumber.charAt(0) == '0') {
            bill_building_number.setError("رقم العقار غير صحيح");
            showMessage("رقم العقار غير صحيح");
            valid = false;
            ScrollViewUIHelper.getInstance().scrollTo(bill_building_number , srcroll);
        }



        else  if ( Integer.parseInt(buildingnumber) <= 0) {
            bill_building_number.setError("رقم العقار اقل من 1 ");
            showMessage("رقم العقار اقل من 1");
            valid = false;
            ScrollViewUIHelper.getInstance().scrollTo(bill_building_number , srcroll);
        }


        else if (fonttype.equals("0") || fonttype.equals("-1")) {
            Toast.makeText(getContext(), "اختر نوع الخط", Toast.LENGTH_SHORT).show();
            ScrollViewUIHelper.getInstance().scrollTo(bill_font_type , srcroll);
            valid = false;
        }


        else if (licenseTypesSelectedFromSpinner.equals("اختر نوع الرخصة*")) {
            bill_license.setError("اختر نوع الرخصة");
            showMessage("اختر نوع الرخصة");
            valid = false;
            ScrollViewUIHelper.getInstance().scrollTo(spinner_linces_types , srcroll);
        }


        else if (lisinceNumber.isEmpty() ) {
            bill_license_number.setError("ادخل رقم رخصة اللوحة");
            showMessage("ادخل رقم رخصة اللوحة");
            valid = false;
            ScrollViewUIHelper.getInstance().scrollTo(bill_license_number , srcroll);
        }


        else if (startDate.isEmpty() || startDate.equals("تاريخ اصدار الرخصة")) {
            txt_license_start_date.setError("ادخل تاريخ الاصدار. ");
            showMessage("ادخل تاريخ الاصدار");
            valid = false;
            ScrollViewUIHelper.getInstance().scrollTo(txt_license_start_date , srcroll);
        }


        else   if (endDate.isEmpty() || endDate.equals("تاريخ انتهاء الرخصة")) {
            txt_license_end_date.setError("ادخل تاريخ الانتهاء");
            showMessage("ادخل تاريخ الانتهاء");
            valid = false;
            ScrollViewUIHelper.getInstance().scrollTo(txt_license_end_date , srcroll);
        } else {

            if (equal(startDate, endDate)) {
                Toast.makeText(getActivity(), "تاريخ الانتهاء  لابد ان يكون بعد  تاريخ الاصدار  ", Toast.LENGTH_SHORT).show();
                valid = false;
                ScrollViewUIHelper.getInstance().scrollTo(txt_license_end_date , srcroll);
            } else {
                txt_license_end_date.setError(null);
            }


            if (equal2(startDate)) {
                Toast.makeText(getActivity(), "  تاريخ الاصدار يجب ان يكون مثل تاريخ اليوم او قبل اليوم   ", Toast.LENGTH_SHORT).show();
                valid = false;
                ScrollViewUIHelper.getInstance().scrollTo(txt_license_end_date , srcroll);
            } else {
                txt_license_end_date.setError(null);
            }

        }

        return valid;
    }


    public void savedata() {
        if (!validate()) {
            ///// onLoginFailed();
            return;
        }
        String ownerName = bill_owner_name.getText().toString().trim();
        String billName = bill_name.getText().toString().trim();
        String billType = bill_type.getText().toString().trim();
        String billWidth = bill_width.getText().toString().trim();
        String billLength = bill_length.getText().toString().trim();
        String billHeight = bill_height.getText().toString().trim();

        String area = bill_area.getText().toString().trim();
        String street = bill_street.getText().toString().trim();
        String locality = bill_locality.getText().toString().trim();
        String alley = bill_alley.getText().toString().trim();
        String buildingnumber = bill_building_number.getText().toString().trim();
        String lincise = bill_license.getText().toString().trim();
        String lisinceNumber = bill_license_number.getText().toString().trim();
        String startDate = txt_license_start_date.getText().toString().trim();
        String endDate = txt_license_end_date.getText().toString().trim();

        BillboardModel billboardModel = new BillboardModel(generatedId,
                ownerName,
                billName,
                billsTypesSelectedFromSpinner,
                billWidth,
                billLength,
                billHeight,
                fonttype,
                area,
                locality,
                alley,
                street,
                buildingnumber,
                licenseTypesSelectedFromSpinner,
                lisinceNumber,
                startDate,
                endDate, latitude.getText().toString() + "", longitude.getText().toString(), "0");



        ConnectivityManager cm =
                (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null && activeNetwork.isConnectedOrConnecting()) {

            mainPresenter.saveUpdatedBillBoardToServer(billboardModel , myid);

        } else {
            if (mainPresenter.updateLocally(billboardModel,myid) > 0) {
                replaceView();
                showMessage("تم تعديل بيانات اللوحة الاعلانية بدون مزامنة .");
            }


        }
    }

    private void initFontTypeSpinner() {

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>
                (getActivity(), android.R.layout.simple_spinner_item, fonttypes);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout
                .simple_spinner_dropdown_item);
        bill_font_type.setAdapter(spinnerArrayAdapter);

        if (fonttype != null) {
            int spinnerPosition = spinnerArrayAdapter.getPosition(fonttype);
            bill_font_type.setSelection(spinnerPosition);
        }

        bill_font_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                fonttype = (String) parent.getItemAtPosition(position);

                if (fonttype.equals("عربى")) {
                    fonttype = "1";
                } else if (fonttype.equals("انجليزى")) {
                    fonttype = "2";
                } else {
                    fonttype = "0";
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                fonttype = "-1";
            }
        });

    }

    @Override
    public void replaceView() {
        BillListFragment fragment = BillListFragment.newInstance("", "");
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction =
                fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout_container, fragment);
        //fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    public void getLocation() {
        longitude.setText("44.361488");
        latitude.setText("33.312805");
        latitude2 = 00.00;

        if(!Utilities.checkConnection(getContext())){
            longitude.setText("44.361488");
            latitude.setText("33.312805");
            return;
        }
        else {
            gpsTracker = new GpsTracker(getContext());
            if (gpsTracker.canGetLocation()) {
                latitude2 = gpsTracker.getLatitude();
                longitude2 = gpsTracker.getLongitude();

                longitude.setText(String.valueOf(longitude2));
                latitude.setText(String.valueOf(latitude2));

            } else {
                gpsTracker.showSettingsAlert();
            }

        }

    }

    private GpsTracker gpsTracker;
    private double latitude2;
    private double longitude2;

    @Override
    public void onResume() {
        super.onResume();
       // getLocation();
    }

    // TODO: Rename and change types and number of parameters
    public static UpdateBillBoardFragment newInstance(String param1) {
        UpdateBillBoardFragment fragment = new UpdateBillBoardFragment();
        Bundle args = new Bundle();
        //args.putString(ARG_PARAM1, param1);
        //args.putString(ARG_PARAM2, param2);
        myid = param1;
        fragment.setArguments(args);
        return fragment;
    }

    public boolean equal(String startDate, String endDate) {

        boolean asd = false;
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


        if (date1.compareTo(date2) == 0 || date1.compareTo(date2) > 0  || date1.compareTo(new Date()) > 0) {
            asd = true;
        }


        return asd;
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
