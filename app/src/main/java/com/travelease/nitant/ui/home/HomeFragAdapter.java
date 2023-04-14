package com.travelease.nitant.ui.home;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.travelease.nitant.CityModel;
import com.travelease.nitant.R;

import java.util.ArrayList;

public class HomeFragAdapter extends RecyclerView.Adapter<HomeFragAdapter.Myclass> {

    ArrayList<CityModel> arraylocation;
    Context context;

    public HomeFragAdapter(ArrayList<CityModel> arraylocation, Context context) {
        this.arraylocation = arraylocation;
        this.context = context;
    }

    @NonNull
    @Override
    public Myclass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.locationcustom,parent,false);
        return new Myclass(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Myclass holder, @SuppressLint("RecyclerView") int position) {
        holder.tvCity.setText(arraylocation.get(position).getPlace());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, ""+position+" "+arraylocation.get(position).getPlace(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return arraylocation.size();
    }

    class Myclass extends RecyclerView.ViewHolder {

        TextView tvCity;
        public Myclass(@NonNull View itemView) {
            super(itemView);
            tvCity=itemView.findViewById(R.id.tv_cv_location);
        }
    }
}
