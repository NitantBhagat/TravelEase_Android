package com.travelease.nitant.ui.Trip;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.travelease.nitant.R;

import java.util.ArrayList;

public class TripManageActivity extends AppCompatActivity {

    private TextView tv_Destination,tv_StartDate,tv_Id;
    private Button btn_Edit;
    private ArrayList<String> arrayList;
    private ListView lvDestination;

//    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_manage);
        getID();

        Intent intent = getIntent();
        String destination = intent.getStringExtra("destination");
        String uid = intent.getStringExtra("uid");
        Toast.makeText(this, ""+uid, Toast.LENGTH_SHORT).show();
        tv_Destination.setText(destination);
        tv_Id.setText(uid);

//        arrayList = new ArrayList<>();
//        arrayList.add("Activity 1");
//        arrayList.add("Activity 2");
//        arrayList.add("Activity 3");
//
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayList);
//        lvDestination.setAdapter(adapter);
        btn_Edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TripManageActivity.this,EditTripActivity.class);
                intent.putExtra("id",uid);
                startActivity(intent);
            }
        });




    }

    private void getID() {
        tv_StartDate=findViewById(R.id.tv_Manage_StartDate);
        tv_Id=findViewById(R.id.tv_Manage_Id);
        tv_Destination=findViewById(R.id.tv_Manage_Destination);
        btn_Edit=findViewById(R.id.btn_Manage_Edit);
//        btn_Delete=findViewById(R.id.btn_Manage_Delete);
        lvDestination= findViewById(R.id.lv_Manage_Activity);

    }
}