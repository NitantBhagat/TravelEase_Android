package com.travelease.nitant.ui.Trip;

import static com.travelease.nitant.ui.Trip.TripFragment.refresh;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.travelease.nitant.R;
import com.travelease.nitant.database.TripItemDBHelper;

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
    public void onBindViewHolder(@NonNull myclass holder, @SuppressLint("RecyclerView") int position) {

        holder.tvID.setText(Integer.toString(list.get(position).getId()));
        holder.tvTitle.setText(list.get(position).getTitle());
        holder.tvDetail.setText(list.get(position).getDetail());
        holder.itemView.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
            @Override
            public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
                MenuItem update = contextMenu.add(0,0,0,"Update");
                MenuItem delete = contextMenu.add(0,1,0,"Delete");

                update.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(@NonNull MenuItem menuItem) {
                        Intent intent = new Intent(context,UpdateActivity.class);
                        intent.putExtra("id",list.get(position).getId());
                        intent.putExtra("title",list.get(position).getTitle());
                        intent.putExtra("detail",list.get(position).getDetail());
                        context.startActivity(intent);

                        return false;
                    }
                });
                delete.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(@NonNull MenuItem menuItem) {
                        TripItemDBHelper tripItemDBHelper = new TripItemDBHelper(context);
                        tripItemDBHelper.Delete(list.get(position).getId());
                        refresh();
                        return false;
                    }
                });
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, ""+list.get(position).getTitle(), Toast.LENGTH_SHORT).show();
            }
        });
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
