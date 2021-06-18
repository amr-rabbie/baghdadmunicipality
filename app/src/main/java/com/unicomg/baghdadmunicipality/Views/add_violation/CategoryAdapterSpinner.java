package com.unicomg.baghdadmunicipality.Views.add_violation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.unicomg.baghdadmunicipality.R;
import com.unicomg.baghdadmunicipality.data.models.category.Category;
import com.unicomg.baghdadmunicipality.data.models.shops.ShopModel;

import java.util.List;

public class CategoryAdapterSpinner extends ArrayAdapter<String> {

    private final LayoutInflater mInflater;
    private final Context mContext;
    private final List<Category> items;
    private final int mResource;
    Category current;

    public CategoryAdapterSpinner(@NonNull Context context, @LayoutRes int resource,
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
    public @NonNull
    View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return createItemView(position, convertView, parent);
    }

    private View createItemView(int position, View convertView, ViewGroup parent) {
        final View view = mInflater.inflate(mResource, parent, false);

        TextView tvTitle = view.findViewById(R.id.tvTitle);

        if (items.size() != 0) {
            current = items.get(position);
            tvTitle.setText(current.getName());
        }

        return view;
    }
}
