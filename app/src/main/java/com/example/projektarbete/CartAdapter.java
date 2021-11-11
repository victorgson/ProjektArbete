package com.example.projektarbete;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.projektarbete.Dishes;
import com.example.projektarbete.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class CartAdapter extends ArrayAdapter<Dishes> {

    Context context;
    int layoutResourceId;
    List<Dishes> dish;
    FirebaseAuth mAuth;
    FirebaseUser currentUser;
    DatabaseReference mDatabase;
    DatabaseReference ref;
    CartActivity cr = new CartActivity();




    public CartAdapter(@NonNull Context context, int resource, @NonNull List<Dishes> objects) {
        super(context, resource, objects);

        this.layoutResourceId = resource;
        this.context = context;
        this.dish = objects;


    }



    static class CartDataHolder {

        TextView cartName;
       // TextView dishInfo;
        TextView cartPrice;
        ImageView cartButton;


    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        CartDataHolder holder;
        if (convertView == null) {

            LayoutInflater inflater = ((Activity) context).getLayoutInflater();

            convertView = inflater.inflate(layoutResourceId, parent, false);
            holder = new CartDataHolder();
            holder.cartName = (TextView) convertView.findViewById(R.id.cName);
            //holder.dishInfo = (TextView) convertView.findViewById(R.id.dInfo);
            holder.cartPrice = (TextView) convertView.findViewById(R.id.cPrice);
            holder.cartButton = (ImageView) convertView.findViewById(R.id.ImageViewDelete);
            convertView.setTag(holder);
        } else {
            holder = (CartDataHolder) convertView.getTag();

        }
        Dishes dishes = dish.get(position);

        holder.cartName.setText(dishes.getName());
       // holder.dishInfo.setText(dishes.getInfo());
        holder.cartPrice.setText(dishes.getPrice());

        holder.cartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dish.remove(dishes);
                //dish.remove(position);
                System.out.println(position + "hallå?");
                CartAdapter.this.notifyDataSetChanged();
                cr.removeOrder(position);



            }
        });



        return convertView;

    }



}

