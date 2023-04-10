package com.travelease.nitant.ui.Trip;

import static com.travelease.nitant.ui.Trip.TripFragment.refresh;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.travelease.nitant.R;
import com.travelease.nitant.database.TripItemDBHelper;

public class AddActivity extends AppCompatActivity {

    EditText etTitle,etDetail;
    Button btnAdd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        getID();
        //ADD SQLITE DATABASE IN LOCAL DATABASE
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = etTitle.getText().toString().trim();
                String detail = etDetail.getText().toString().trim();

                TripItemModel tripmodel = new TripItemModel();
                tripmodel.setTitle(title);
                tripmodel.setDetail(detail);
                TripItemDBHelper tripItemDBHelper = new TripItemDBHelper(AddActivity.this);
                tripItemDBHelper.Insert(tripmodel);
                refresh();
                finish();
            }
        });
    }

    private void getID() {
        etTitle=findViewById(R.id.et_addtrip_title);
        etDetail=findViewById(R.id.et_addtrip_detail);
        btnAdd=findViewById(R.id.btn_addtrip_add);
    }
}