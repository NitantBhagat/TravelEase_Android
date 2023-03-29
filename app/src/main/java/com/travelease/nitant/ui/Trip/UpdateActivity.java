package com.travelease.nitant.ui.Trip;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.travelease.nitant.R;
import com.travelease.nitant.database.TripItemDBHelper;

public class UpdateActivity extends AppCompatActivity {

    EditText etTitle,etDetail;
    Button btnUpdate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        getID();
        Intent intent = getIntent();
        Integer id = intent.getIntExtra("id",0);
        String title = intent.getStringExtra("title");
        String detail = intent.getStringExtra("detail");


        etTitle.setText(title);
        etDetail.setText(detail);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = etTitle.getText().toString().trim();
                String detail = etDetail.getText().toString().trim();

                TripItemModel tripmodel = new TripItemModel();
                tripmodel.setTitle(title);
                tripmodel.setDetail(detail);
                tripmodel.setId(id);
                TripItemDBHelper tripItemDBHelper = new TripItemDBHelper(UpdateActivity.this);
                tripItemDBHelper.Update(tripmodel);

                finish();
            }
        });
    }

    private void getID() {
        etTitle=findViewById(R.id.et_updatetrip_title);
        etDetail=findViewById(R.id.et_updatetrip_detail);
        btnUpdate=findViewById(R.id.btn_updatetrip_update);
    }
}