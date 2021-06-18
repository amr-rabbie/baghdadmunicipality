package com.unicomg.baghdadmunicipality.Views.scheduled_work;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
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

import com.unicomg.baghdadmunicipality.R;
import com.unicomg.baghdadmunicipality.Views.add_shops.AddShopFragment;
import com.unicomg.baghdadmunicipality.Views.add_shops.UpdateShopFragment;
import com.unicomg.baghdadmunicipality.data.models.scheduled_works.ScheduledWorkModel;
import com.unicomg.baghdadmunicipality.data.models.shops.ShopModel;
import com.unicomg.baghdadmunicipality.di.DaggerApplication;
import com.unicomg.baghdadmunicipality.helper.ConnectivityReceiver;

import java.util.ArrayList;

import javax.inject.Inject;


public class WorksListFragment extends Fragment implements WorkListView, ConnectivityReceiver.ConnectivityReceiverListener, WorkItemClick {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    private OnFragmentInteractionListener mListener;

    @Inject
    WorksListPresenter mainPresenter;

    RecyclerView recyclerView;
    ProgressBar loading_indicator;
    WorksAdapter shopsAdapter;

    Button add_bill_intent_btn2;
    LinearLayout empty_view ;


    public WorksListFragment() {
        // Required empty public constructor
    }


    public static WorksListFragment newInstance(String param1, String param2) {
        WorksListFragment fragment = new WorksListFragment();
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

        //register some activity   for injections
        ((DaggerApplication) getActivity().getApplication()).getAppComponent().inject(this);
        mainPresenter.onAttach(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.work_list, container, false);




        add_bill_intent_btn2 = view.findViewById(R.id.add_shop_intent_btn2);
        add_bill_intent_btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddShopFragment fragment = AddShopFragment.newInstance("", "");
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction =
                        fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frameLayout_container, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        empty_view = view.findViewById(R.id.empty_view) ;

        recyclerView = view.findViewById(R.id.main_shops);
        loading_indicator = view.findViewById(R.id.loading_indicator);
//        StaggeredGridLayoutManager layoutManager
//                = new StaggeredGridLayoutManager (2, StaggeredGridLayoutManager.VERTICAL);
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        shopsAdapter = new WorksAdapter(this);

        mainPresenter.getShops();
        return view;

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
    public void sendOneShop(ScheduledWorkModel shopModel, View v, int position) {

    }

    @Override
    public void updateJob(View v, int position) {
//        ShopModel shopModel22 = currentShopList.get(position);
//        String shop_id = shopModel22.getShop_id();
//
//        UpdateShopFragment fragment = UpdateShopFragment.newInstance(shop_id);
//        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
//        FragmentTransaction fragmentTransaction =
//                fragmentManager.beginTransaction();
//        fragmentTransaction.replace(R.id.frameLayout_container, fragment);
//        fragmentTransaction.addToBackStack(null);
//        fragmentTransaction.commit();

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



    public void showShops(ArrayList<ScheduledWorkModel> shopModels) {
      //  currentShopList = shopModels;
        if (shopModels.isEmpty()) {
            empty_view.setVisibility(View.VISIBLE);
            hideLoading();

        } else {
            empty_view.setVisibility(View.GONE);

            shopsAdapter.setWorkData(shopModels, getContext());
            recyclerView.setAdapter(shopsAdapter);
        }
    }

    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {

    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
