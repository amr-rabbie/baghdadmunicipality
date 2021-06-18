package com.unicomg.baghdadmunicipality.Views.add_violation;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.unicomg.baghdadmunicipality.R;

public class RoomImageViewHolder  extends RecyclerView.ViewHolder {


    public ImageView imageViewInDoor;

    public Button btnInDoorDelete;

    public RoomImageViewHolder(View itemView) {
        super(itemView);
        imageViewInDoor = itemView.findViewById(R.id.image_room_item) ;
        btnInDoorDelete = itemView.findViewById(R.id.button_room_delete_image) ;
    }


}

