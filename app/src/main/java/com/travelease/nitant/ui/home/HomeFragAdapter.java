package com.travelease.nitant.ui.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.travelease.nitant.City;
import com.travelease.nitant.Location;
import com.travelease.nitant.R;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class HomeFragAdapter extends RecyclerView.Adapter<HomeFragAdapter.Myclass> {


    ArrayList<City> location;
    Context context;

    public HomeFragAdapter(ArrayList<City> location, Context context) {
        this.location = location;
        this.context = context;
    }

    @NonNull
    @Override
    public Myclass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.locationcustom,parent,false);
        return new Myclass(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Myclass holder, int position) {
        holder.tvLocation.setText(location.get(position).getPlace());
    }

    @Override
    public int getItemCount() {
        return location.size();
    }

    class Myclass extends RecyclerView.ViewHolder {

        TextView tvLocation;

        public Myclass(@NonNull View itemView) {
            super(itemView);
            tvLocation=itemView.findViewById(R.id.tv_cv_location);
        }
    }
}
