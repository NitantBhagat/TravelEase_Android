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
import java.util.List;

public class TripFragAdapter extends RecyclerView.Adapter<TripFragAdapter.myclass> {

    List<TripItemModel> list;
    Context context;

    public TripFragAdapter(List<TripItemModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public myclass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.cv_item,parent,false);
        return new myclass(v);
    }

    @Override
    public void onBindViewHolder(@NonNull myclass holder, int position) {

        holder.tvID.setText(Integer.toString(list.get(position).getId()));
        holder.tvTitle.setText(list.get(position).getTitle());
        holder.tvDetail.setText(list.get(position).getDetail());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class myclass extends RecyclerView.ViewHolder{
        TextView tvID,tvTitle,tvDetail;
        public myclass(@NonNull View itemView) {
            super(itemView);
            tvDetail=itemView.findViewById(R.id.tv_cv_item_details);
            tvID=itemView.findViewById(R.id.tv_cv_item_id);
            tvTitle=itemView.findViewById(R.id.tv_cv_item_title);

        }
    }
}
