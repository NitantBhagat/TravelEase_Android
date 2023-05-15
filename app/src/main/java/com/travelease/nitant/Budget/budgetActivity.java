package com.travelease.nitant.Budget;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.travelease.nitant.R;

public class budgetActivity extends AppCompatActivity {

    RecyclerView rv_expense;
    Button btn_add_funds;
    FloatingActionButton fbtn_add_expense;
    TextView tv_balance;

    MaterialToolbar mt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget);

        getID();




    }

    private void getID() {

        rv_expense=findViewById(R.id.rv_budget_expense);
        btn_add_funds=findViewById(R.id.btn_budget_add_funds);
        fbtn_add_expense=findViewById(R.id.fbtn_budget_add_expense);
        tv_balance=findViewById(R.id.tv_budget_amount);
        mt=findViewById(R.id.toolbar);
        mt.setVisibility(View.GONE);

    }
}