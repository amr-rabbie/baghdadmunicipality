package com.unicomg.baghdadmunicipality.Views.shopslist;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.unicomg.baghdadmunicipality.R;
import com.unicomg.baghdadmunicipality.data.models.shops.ShopModel;


import java.util.Collections;
import java.util.List;

public class ShopsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private LayoutInflater inflater;
    public List<ShopModel> data = Collections.emptyList();
    ShopModel current;

    private ShopItemClick lOnClickListener;
    public ShopsAdapter(ShopItemClick listener) {
        lOnClickListener = listener;
    }
    public void setShopsData(List<ShopModel> recipesIn, Context context) {
        data = recipesIn;
        mContext = context;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutIdForListItem = R.layout.shop_item;

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(layoutIdForListItem, parent, false);
        MyHolder viewHolder = new MyHolder(view);

        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        MyHolder myHolder = (MyHolder) holder;
        current = data.get(position);
        myHolder.jobTitle.setText(current.getBillboard_name());

        if(current.getSend().equals("1")) {
            myHolder.deleteBtn.setVisibility(View.GONE);
        }
       myHolder.address.setText(current.getArea() + "،" + current.getStreet() + "،" + current.getLocality() + "،" +
               current.getAlley() + "،" + current.getLocality_number() );
        myHolder.deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lOnClickListener.sendOneShop(current , v, position);
            }
        });

        myHolder.updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lOnClickListener.updateJob(v, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {
        ImageView deleteBtn, updateBtn;
        TextView jobTitle, address, jobNotes , yyy;

        public MyHolder(View itemView) {
            super(itemView);
            deleteBtn = (ImageView) itemView.findViewById(R.id.delete_job_btn);
            updateBtn = (ImageView) itemView.findViewById(R.id.update_job_btn);

            jobTitle = (TextView) itemView.findViewById(R.id.bill_title);
            address = (TextView) itemView.findViewById(R.id.address);


        }
    }

    public void clear() {
        final int size = data.size();
        data.clear();
        notifyItemRangeRemoved(0, size);
    }
}