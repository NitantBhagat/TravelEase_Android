package com.travelease.nitant.Budget;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Context;
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

    static RecyclerView rv_expense;
    static Button btn_add_funds;
    static FloatingActionButton fbtn_add_expense;
    static TextView tv_balance;
    static TextView tv_expense,tv_remaining;
    static MaterialToolbar mt;
    static String uid;
    static Integer finalBalance = 0,finalExpense=0,remainingbal=0;
    static int fund=0;
    static budgetExpenseAdapter budgetExpenseAdapter;
    static String activity,expense;
    static ArrayList<BalanceModel> balanceArrayList = new ArrayList<>();
    static ArrayList<ExpenseModel> expenseModelArrayList = new ArrayList<>();

    static Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget);

        getID();
        Intent intent = getIntent();
        uid= intent.getStringExtra("uid");
        context=this;


        rv_expense.setLayoutManager(new LinearLayoutManager(budgetActivity.this));


        setBalance();
        setExpense();
        showExpense();

        btn_add_funds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showAddBudgetDialog();
                setBalance();
                //calculateoverallbudget
                calcBudget();
            }
        });


        fbtn_add_expense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddExpenseDialog();
                calcBudget();
            }
        });

    }

    public static void setExpense() {
        ArrayList<ExpenseModel> expenseModelArrayList1 = new ArrayList<>();
        BudgetDBHelper budgetDBHelper = new BudgetDBHelper(context);
        expenseModelArrayList1=budgetDBHelper.getExpense(Integer.parseInt(uid));
        Integer expense=0;

        for(ExpenseModel model:expenseModelArrayList1){
            expense+=model.getExpense();
        }
        finalExpense=expense;
        tv_expense.setText("Rs. "+ finalExpense);
        calcBudget();
        showExpense();
        //Calculate
    }


    private void showAddExpenseDialog() {
        Dialog addExpensedialog = new Dialog(budgetActivity.this);
        addExpensedialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        addExpensedialog.setCancelable(true);
        addExpensedialog.setContentView(R.layout.addexpense);
        Window window = addExpensedialog.getWindow();
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        EditText etActivity = addExpensedialog.findViewById(R.id.et_addexpense_activity);
        EditText etExpense = addExpensedialog.findViewById(R.id.et_addexpense_expense);
        Button btnAdd = addExpensedialog.findViewById(R.id.btn_addexpense_add);



        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                activity=etActivity.getText().toString().trim();
                expense=etExpense.getText().toString().trim();

                if(activity.isEmpty())
                {
                    etActivity.setError("Activity can't be empty");
                    etActivity.requestFocus();
                    return;
                }
                if(expense.isEmpty())
                {
                    etExpense.setError("Expense can't be empty");
                    etExpense.requestFocus();
                    return;
                }

                ExpenseModel expenseModel = new ExpenseModel();

                expenseModel.setUid(uid);
                expenseModel.setActivity(activity);
                expenseModel.setExpense(Integer.parseInt(expense));

                BudgetDBHelper budgetDBHelper = new BudgetDBHelper(budgetActivity.this);
                budgetDBHelper.insertExpense(expenseModel);

                showExpense();
                setExpense();
                addExpensedialog.dismiss();
            }
        });
        addExpensedialog.show();
    }

    private static void showExpense() {
        BudgetDBHelper budgetDBHelper = new BudgetDBHelper(context);
        expenseModelArrayList=budgetDBHelper.getExpense(Integer.parseInt(uid));
        if(expenseModelArrayList!=null)
        {
            budgetExpenseAdapter = new budgetExpenseAdapter(expenseModelArrayList,context);
            rv_expense.setAdapter(budgetExpenseAdapter);
        }
    }




    private void showAddBudgetDialog() {
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
                calcBudget();
                adddialog.dismiss();
            }
        });
        adddialog.show();
    }

    private void setBalance() {
        BudgetDBHelper budgetDBHelper = new BudgetDBHelper(budgetActivity.this);
        balanceArrayList=budgetDBHelper.getBalance(Integer.parseInt(uid));

        if(balanceArrayList.isEmpty())
        {
            tv_balance.setText("Rs. "+ 0);
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
            tv_balance.setText("Rs. "+b);
            calcBudget();
        }

    }
    public static void calcBudget() {
        remainingbal=finalBalance-finalExpense;
        tv_remaining.setText(String.valueOf(remainingbal));
    }

    private void getID() {

        rv_expense=findViewById(R.id.rv_budget_expense);
        btn_add_funds=findViewById(R.id.btn_budget_add_funds);
        fbtn_add_expense=findViewById(R.id.fbtn_budget_add_expense);
        tv_balance=findViewById(R.id.tv_budget_amount);
        tv_expense=findViewById(R.id.tv_budget_expense);
        tv_remaining=findViewById(R.id.tv_budget_remainingbalance);
        mt=findViewById(R.id.toolbar);
        mt.setVisibility(View.GONE);

    }

}