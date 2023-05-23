package com.travelease.nitant.Budget;

import static com.travelease.nitant.Budget.budgetActivity.calcBudget;
import static com.travelease.nitant.Budget.budgetActivity.setExpense;

import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.travelease.nitant.R;
import com.travelease.nitant.database.BudgetDBHelper;

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
        holder.tvExpense.setText(String.valueOf("Rs. "+arrayList.get(position).getExpense()));
        Integer id = arrayList.get(position).getId();

        holder.itemView.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
            @Override
            public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

                MenuItem remove = menu.add(0,0,0,"Remove");
                remove.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(@NonNull MenuItem item) {
                        BudgetDBHelper budgetDBHelper = new BudgetDBHelper(context);
                        budgetDBHelper.deleteExpense(id);
                        setExpense();
                        calcBudget();
                        return false;
                    }
                });
            }
        });
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
