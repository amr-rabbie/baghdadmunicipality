package com.unicomg.baghdadmunicipality.Views.bill_board_list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.unicomg.baghdadmunicipality.R;
import com.unicomg.baghdadmunicipality.data.models.billboard.BillboardModel2;

import java.util.Collections;
import java.util.List;

public class BillboardAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private LayoutInflater inflater;
    public List<BillboardModel2> data = Collections.emptyList();
    BillboardModel2 current;

    private BillboardItemClick lOnClickListener;
    public BillboardAdapter(BillboardItemClick listener) {
        lOnClickListener = listener;
    }
    public void setBillBoardData(List<BillboardModel2> recipesIn, Context context) {
        data = recipesIn;
        mContext = context;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutIdForListItem = R.layout.biiboard_item;

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
        myHolder.address.setText(current.getArea() + "،" + current.getAilley() + "،" + current.getStreet() + "،" + current.getBulding_number() );

        if(current.getSend().equals("1")) {
            myHolder.deleteBtn.setVisibility(View.GONE);
        }

        myHolder.deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lOnClickListener.sendBillboard(current , v, position);
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
        TextView jobTitle, address ;

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