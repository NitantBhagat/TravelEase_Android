package com.travelease.nitant.Budget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.travelease.nitant.R;

import java.util.ArrayList;

public class budgetExpenseAdapter extends RecyclerView.Adapter<budgetExpenseAdapter.Myclass> {

    ArrayList<ExpenseModel> arrayList;
    Context context;

    public budgetExpenseAdapter(ArrayList<ExpenseModel> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public Myclass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.customexpenselist,parent,false);
        return new Myclass(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Myclass holder, int position) {
        holder.tvActivity.setText(arrayList.get(position).getActivity());
        holder.tvExpense.setText(String.valueOf(arrayList.get(position).getExpense()));
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class Myclass extends RecyclerView.ViewHolder {

        TextView tvActivity,tvExpense;

        public Myclass(@NonNull View itemView) {
            super(itemView);

            tvExpense= itemView.findViewById(R.id.tv_custexpense_expense);
            tvActivity= itemView.findViewById(R.id.tv_custexpense_act);

        }
    }
}
