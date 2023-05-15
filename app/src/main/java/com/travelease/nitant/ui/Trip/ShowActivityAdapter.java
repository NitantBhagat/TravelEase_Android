package com.travelease.nitant.ui.Trip;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.travelease.nitant.R;

import java.util.ArrayList;

public class ShowActivityAdapter extends RecyclerView.Adapter<ShowActivityAdapter.Myclass> {

    ArrayList<ActivityModel> arrayList;
    Context context;

    public ShowActivityAdapter(ArrayList<ActivityModel> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public Myclass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.customactivity,parent,false);
        return new Myclass(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Myclass holder, int position) {
        holder.Activity.setText(arrayList.get(position).getActivity());
        holder.Date.setText(arrayList.get(position).getDate());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class Myclass extends RecyclerView.ViewHolder {

        TextView Activity,Date;
        public Myclass(@NonNull View itemView) {
            super(itemView);
            Activity = itemView.findViewById(R.id.tv_custact_act);
            Date = itemView.findViewById(R.id.tv_custact_date);
        }
    }
}
