package com.travelease.nitant.ui.Trip;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.travelease.nitant.R;
import com.travelease.nitant.Budget.budgetActivity;
import com.travelease.nitant.database.ActivityDBHelper;

import java.text.BreakIterator;
import java.util.ArrayList;

public class TripManageActivity extends AppCompatActivity {

    private static ActivityDBHelper activityDBHelper;
    private static ArrayList<ActivityModel> actmodellist;
    private static TextView tv_StartDate;
    private static RecyclerView rvShowAct;
    private static String uid;
    private static ShowActivityAdapter showActivityAdapter;
    private TextView tv_Destination,tv_Id;
    private Button btn_Edit,btn_Budget;

    private static Context context;



    String destination;

//    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_manage);
        context = this;
        getID();

        Intent intent = getIntent();
        destination = intent.getStringExtra("destination");
        uid = intent.getStringExtra("uid");
        Toast.makeText(this, ""+uid, Toast.LENGTH_SHORT).show();
        tv_Destination.setText(destination);
        tv_Id.setText(uid);
//        tv_StartDate.setText("APR 25 2023");

        refreshActivity();

        btn_Edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TripManageActivity.this,EditTripActivity.class);
                intent.putExtra("id",uid);
                startActivity(intent);
            }
        });

        btn_Budget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(TripManageActivity.this, budgetActivity.class);
                startActivity(intent1);
            }
        });



    }

    @Override
    protected void onResume() {
        super.onResume();

        refreshActivity();

    }

    public static void refreshActivity()
    {

        activityDBHelper = new ActivityDBHelper(context);
        actmodellist = (ArrayList<ActivityModel>) activityDBHelper.showActivity(uid);
        if(!(actmodellist.isEmpty()))
        {
            tv_StartDate.setText(actmodellist.get(0).getDate());
        }

        rvShowAct.setLayoutManager(new LinearLayoutManager(context));
        showActivityAdapter = new ShowActivityAdapter(actmodellist,context);

        rvShowAct.setAdapter(showActivityAdapter);
    }


    private void getID() {
        btn_Budget=findViewById(R.id.btn_Manage_Budget);
        tv_StartDate=findViewById(R.id.tv_Manage_StartDate);
        tv_Id=findViewById(R.id.tv_Manage_Id);
        tv_Destination=findViewById(R.id.tv_Manage_Destination);
        btn_Edit=findViewById(R.id.btn_Manage_Edit);
//        btn_Delete=findViewById(R.id.btn_Manage_Delete);
        rvShowAct= findViewById(R.id.rv_Manage_Activity);

    }
}