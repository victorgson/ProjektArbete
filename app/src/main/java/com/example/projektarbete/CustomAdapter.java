package com.example.projektarbete;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;


import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import java.util.List;

public class CustomAdapter extends ArrayAdapter<Dishes> {

    Context context;
    int layoutResourceId;
    List<Dishes> dish;


    public CustomAdapter(@NonNull Context context, int resource, @NonNull List<Dishes> objects) {
        super(context, resource, objects);

        this.layoutResourceId = resource;
        this.context = context;
        this.dish = objects;


    }

    static class DataHolder {

        TextView dishName;
       // TextView dishInfo;
        TextView dishPrice;


    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        DataHolder holder;
        if (convertView == null) {

            LayoutInflater inflater = ((Activity) context).getLayoutInflater();

            convertView = inflater.inflate(layoutResourceId, parent, false);
            holder = new DataHolder();
            holder.dishName = (TextView) convertView.findViewById(R.id.dName);
            //holder.dishInfo = (TextView) convertView.findViewById(R.id.dInfo);
            holder.dishPrice = (TextView) convertView.findViewById(R.id.dPrice);

            convertView.setTag(holder);
        } else {
            holder = (DataHolder) convertView.getTag();

        }
        Dishes dishes = dish.get(position);
        holder.dishName.setText(dishes.getName());
       // holder.dishInfo.setText(dishes.getInfo());
        holder.dishPrice.setText(dishes.getPrice());

        return convertView;
    }
}

