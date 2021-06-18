package com.unicomg.baghdadmunicipality.adapters;

import android.content.Context;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.unicomg.baghdadmunicipality.R;
import com.unicomg.baghdadmunicipality.data.models.shops_activities.ShopsActivitiesDetailsResponse;

import java.util.List;

public class ShopActivitiesSpinnerAdapter extends ArrayAdapter<String> {

    private final LayoutInflater mInflater;
    private final Context mContext;
    private final List<ShopsActivitiesDetailsResponse> items;
    private final int mResource;

    public ShopActivitiesSpinnerAdapter(@NonNull Context context, @LayoutRes int resource,
                                @NonNull List objects) {
        super(context, resource, 0, objects);

        mContext = context;
        mInflater = LayoutInflater.from(context);
        mResource = resource;
        items = objects;
    }
    @Override
    public View getDropDownView(int position, @Nullable View convertView,
                                @NonNull ViewGroup parent) {
        return createItemView(position, convertView, parent);
    }

    @Override
    public @NonNull View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return createItemView(position, convertView, parent);
    }

    private View createItemView(int position, View convertView, ViewGroup parent){
        final View view = mInflater.inflate(mResource, parent, false);

        TextView tvTitle = view.findViewById(R.id.tvTitle);


        if (items.size() != 0)
        {
            ShopsActivitiesDetailsResponse shopsActivitiesDetailsResponse = items.get(position);

            tvTitle.setText(shopsActivitiesDetailsResponse.getName());
        }

        return view;
    }
}
