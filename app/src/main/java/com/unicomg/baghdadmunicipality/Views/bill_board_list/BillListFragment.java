package com.unicomg.baghdadmunicipality.Views.bill_board_list;

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
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.unicomg.baghdadmunicipality.R;
import com.unicomg.baghdadmunicipality.Views.add_bill_board.AddBillFragment;
import com.unicomg.baghdadmunicipality.Views.add_bill_board.UpdateBillBoardFragment;
import com.unicomg.baghdadmunicipality.Views.add_shops.UpdateShopFragment;
import com.unicomg.baghdadmunicipality.data.models.billboard.BillboardModel;
import com.unicomg.baghdadmunicipality.data.models.billboard.BillboardModel2;
import com.unicomg.baghdadmunicipality.di.DaggerApplication;
import com.unicomg.baghdadmunicipality.helper.ConnectivityReceiver;

import java.util.ArrayList;

import javax.inject.Inject;


public class BillListFragment extends Fragment implements BillListView, ConnectivityReceiver.ConnectivityReceiverListener, BillboardItemClick {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    private OnFragmentInteractionListener mListener;
    @Inject
    BillListPresenter mainPresenter;
    RecyclerView recyclerView;
    ProgressBar loading_indicator;
    BillboardAdapter billboardAdapter;

    Button add_bill_intent_btn;
    Button add_bill_intent_btn2;
    LinearLayout empty_view;

    public BillListFragment() {

    }


    public static BillListFragment newInstance(String param1, String param2) {
        BillListFragment fragment = new BillListFragment();
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
        View view = inflater.inflate(R.layout.fragment_bill_list, container, false);
        empty_view = view.findViewById(R.id.empty_view);
        add_bill_intent_btn = view.findViewById(R.id.add_bill_intent_btn);
        add_bill_intent_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddBillFragment fragment = AddBillFragment.newInstance("", "");
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction =
                        fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frameLayout_container, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        add_bill_intent_btn2 = view.findViewById(R.id.add_shop_intent_btn2);
        add_bill_intent_btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddBillFragment fragment = AddBillFragment.newInstance("", "");
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction =
                        fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frameLayout_container, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });


        recyclerView = view.findViewById(R.id.main_bill_boards);
        loading_indicator = view.findViewById(R.id.loading_indicator);
//        StaggeredGridLayoutManager layoutManager
//                = new StaggeredGridLayoutManager (2, StaggeredGridLayoutManager.VERTICAL);
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        billboardAdapter = new BillboardAdapter(this);

        mainPresenter.getBillBoards();
        return view;



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
    public void showMessage(String message) {
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

    public ArrayList<BillboardModel2>  cureentBillBoardList ;
    @Override
    public void showBillBoards(ArrayList<BillboardModel2> billboardModels) {
        if (billboardModels.isEmpty()) {
            empty_view.setVisibility(View.VISIBLE);
            add_bill_intent_btn.setVisibility(View.GONE);
        } else {
            empty_view.setVisibility(View.GONE);
            add_bill_intent_btn.setVisibility(View.VISIBLE);

            billboardAdapter.setBillBoardData(billboardModels, getContext());
            recyclerView.setAdapter(billboardAdapter);
        }

        cureentBillBoardList = billboardModels ;
        hideLoading();
        billboardAdapter.setBillBoardData(billboardModels, getContext());
        recyclerView.setAdapter(billboardAdapter);
    }


    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {

    }

    @Override
    public void sendBillboard(BillboardModel2 billboardModel1 , View v, int position) {

        BillboardModel2 billboardModel = cureentBillBoardList.get(position) ;
        BillboardModel sendedBillboard = new BillboardModel( billboardModel.getBillId() , billboardModel.getOwner_name() , billboardModel.getBillboard_name() ,
                billboardModel.getBillboard_type() , billboardModel.getWidth() , billboardModel.getLength() , billboardModel.getHeight() ,
                billboardModel.getFont_language() ,billboardModel.getArea() , billboardModel.getArea() , billboardModel.getAilley() , billboardModel.getStreet() ,
                billboardModel.getBulding_number() , billboardModel.getBillboard_license() ,billboardModel.getBillboard_license_number() ,
                billboardModel.getLicense_date(), billboardModel.getLicense_end_date(), billboardModel1.getLatitude(), billboardModel1.getLongitude() , "1");

        mainPresenter.sendOneBillboard(sendedBillboard , billboardModel.getId());
    }

    @Override
    public void updateJob(View v, int position) {
        BillboardModel2 billboardModel = cureentBillBoardList.get(position) ;
        String id = billboardModel.getId();
        UpdateBillBoardFragment fragment = UpdateBillBoardFragment.newInstance(id);
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction =
                fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout_container, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
