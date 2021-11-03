package com.example.projektarbete;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class ListAdapter extends ArrayAdapter<Restaurants> {

    public ListAdapter(Context context, ArrayList<Restaurants> restaurantsArrayList) {

        super(context, R.layout.list_item_hannes, restaurantsArrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Restaurants restaurants = getItem(position);

        if (convertView == null) {

            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_hannes, parent, false);
        }

        ImageView imageView = convertView.findViewById(R.id.restaurant_pic);
        TextView restaurantName = convertView.findViewById(R.id.restaurant_name);
        TextView restaurantInfo = convertView.findViewById(R.id.restaurant_info);

        imageView.setImageResource(restaurants.imageId);
        restaurantName.setText(restaurants.name);
        restaurantInfo.setText(restaurants.info);

        return convertView;
    }
}