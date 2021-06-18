package com.unicomg.baghdadmunicipality.Views.violations_list;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.unicomg.baghdadmunicipality.R;
import com.unicomg.baghdadmunicipality.Views.add_violation.AddViolationFragment;
import com.unicomg.baghdadmunicipality.data.models.billboard.BillboardModel2;
import com.unicomg.baghdadmunicipality.data.models.violation.ViolationModel;
import com.unicomg.baghdadmunicipality.di.DaggerApplication;
import com.unicomg.baghdadmunicipality.helper.ConnectivityReceiver;

import java.util.ArrayList;

import javax.inject.Inject;

public class ViolationsListFragment extends Fragment implements ViolationView, ConnectivityReceiver.ConnectivityReceiverListener, ViolationItemClick {

    @Inject
    ViolationsListPresenter mainPresenter;

    RecyclerView recyclerView;
    ProgressBar loading_indicator;
    ViolationAdapter violationAdapter;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    Button add_bill_intent_btn;
    Button add_bill_intent_btn2;
    LinearLayout empty_view ;
    private OnFragmentInteractionListener mListener;

    public ViolationsListFragment() {
        // Required empty public constructor
    }



    // TODO: Rename and change types and number of parameters
    public static ViolationsListFragment newInstance(String param1, String param2) {
        ViolationsListFragment fragment = new ViolationsListFragment();
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_violations_list, container, false);

          add_bill_intent_btn = view.findViewById(R.id.add_violation_intent_btn);
        add_bill_intent_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddViolationFragment fragment = AddViolationFragment.newInstance("", "");
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction =
                        fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frameLayout_container, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        empty_view = view.findViewById(R.id.empty_view);
        add_bill_intent_btn2 = view.findViewById(R.id.add_shop_intent_btn2);
        add_bill_intent_btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddViolationFragment fragment = AddViolationFragment.newInstance("", "");
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction =
                        fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frameLayout_container, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        recyclerView = view.findViewById(R.id.main_violations);
        loading_indicator = view.findViewById(R.id.loading_indicator);

//        StaggeredGridLayoutManager layoutManager
//                = new StaggeredGridLayoutManager (2, StaggeredGridLayoutManager.VERTICAL);
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        violationAdapter = new ViolationAdapter(this);

        mainPresenter.getViolations();

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
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
    public void showLoading() {
        loading_indicator.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        loading_indicator.setVisibility(View.GONE);

    }

    public ArrayList<ViolationModel> cureentViolationList;

    @Override
    public void showViolations(ArrayList<ViolationModel> violations) {

        if (violations.isEmpty()) {
            empty_view.setVisibility(View.VISIBLE);
            add_bill_intent_btn.setVisibility(View.GONE);
        }
        else {
            empty_view.setVisibility(View.GONE);
            add_bill_intent_btn.setVisibility(View.VISIBLE);
        }
        cureentViolationList = violations;
        hideLoading();
        violationAdapter.setViolationsData(violations, getContext());
        recyclerView.setAdapter(violationAdapter);
    }


    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {

    }

    @Override
    public void sendOneViolation(ViolationModel violation, View v, int position) {
        ViolationModel violationModel = cureentViolationList.get(position) ;
        Log.e("click_violation" , violation.toString());
        mainPresenter.sendOneViolation(violationModel);
    }

    @Override
    public void updateJob(View v, int position) {

    }

    public interface OnFragmentInteractionListener {

        void onFragmentInteraction(Uri uri);
    }
}
