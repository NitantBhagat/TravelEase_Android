package com.travelease.nitant.Budget;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.travelease.nitant.R;
import com.travelease.nitant.database.BudgetDBHelper;

import java.util.ArrayList;

public class budgetActivity extends AppCompatActivity {

    RecyclerView rv_expense;
    Button btn_add_funds;
    FloatingActionButton fbtn_add_expense;
    TextView tv_balance;
    MaterialToolbar mt;
    String uid;
    Integer finalBalance = 0;
    int fund=0;
    ArrayList<BalanceModel> balanceArrayList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget);

        getID();
        Intent intent = getIntent();
        uid= intent.getStringExtra("uid");

        setBalance();

        btn_add_funds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showAddDialog();
                setBalance();
            }
        });



    }

    private void setBalance() {
        BudgetDBHelper budgetDBHelper = new BudgetDBHelper(budgetActivity.this);
        balanceArrayList=budgetDBHelper.getBalance(Integer.parseInt(uid));


        if(balanceArrayList.isEmpty())
        {
            tv_balance.setText(String.valueOf(0));
        }
        else
        {

            finalBalance=0;
            for(BalanceModel model : balanceArrayList)
            {
                finalBalance+=model.getBalance();
            }
            String b= String.valueOf(finalBalance);
            //set balance
            tv_balance.setText(b);
        }

    }

    private void showAddDialog() {
        Dialog adddialog = new Dialog(budgetActivity.this);
        adddialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        adddialog.setCancelable(true);
        adddialog.setContentView(R.layout.addfund);
        Window window = adddialog.getWindow();
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        EditText etfund = adddialog.findViewById(R.id.et_addfund_fund);
        Button btnAdd = adddialog.findViewById(R.id.btn_addfund_add);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fund= Integer.parseInt(etfund.getText().toString());
                BalanceModel balanceModel = new BalanceModel();
                balanceModel.setBalance(fund);
                balanceModel.setUid(uid);
                BudgetDBHelper budgetDBHelper = new BudgetDBHelper(budgetActivity.this);
                budgetDBHelper.insertFunds(balanceModel);
                setBalance();
                //add calculateRemainingBalance method
                adddialog.dismiss();
            }
        });
        adddialog.show();
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