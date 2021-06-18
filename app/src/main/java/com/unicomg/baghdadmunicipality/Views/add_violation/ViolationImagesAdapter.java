package com.unicomg.baghdadmunicipality.Views.add_violation;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;


import com.unicomg.baghdadmunicipality.R;
import com.unicomg.baghdadmunicipality.data.models.violation.ViolationImage;

import java.util.List;

public class ViolationImagesAdapter extends RecyclerView.Adapter<RoomImageViewHolder> {

    private Context context;
    private List<ViolationImage> items;
    private RoomItemClick mItemClickListener;
    private int yy;

    public ViolationImagesAdapter(Context context, List<ViolationImage> items, RoomItemClick itemClickListener, int hh) {
        this.context = context;
        this.items = items;
        mItemClickListener = itemClickListener;
        this.yy = hh;
    }

    @Override
    public RoomImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.room_image_item, parent, false);
        return new RoomImageViewHolder(view);
    }


    @Override
    public void onBindViewHolder(final RoomImageViewHolder holder, final int position) {
        final ViolationImage item = items.get(position);
        holder.imageViewInDoor.setImageBitmap(item.getSource());
        if (yy == 0) {
            holder.btnInDoorDelete.setVisibility(View.GONE);
        }
        holder.btnInDoorDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mItemClickListener.onItemClickDelete(position, item, holder.itemView);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

}