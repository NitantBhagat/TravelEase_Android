package com.travelease.nitant.ui.Trip;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.travelease.nitant.R;
import com.travelease.nitant.database.ActivityDBHelper;

import java.util.ArrayList;
import java.util.Calendar;

public class EditTripActivity extends AppCompatActivity {

    private DatePickerDialog datePickerDialog;
    private Button dateButton;

    private Button btnAdd;

    private String date,uid;

    private TextView etActivity,etUid;

    private ArrayList<String> activity;

    private ListView lvActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_trip);
        getID();
        initDatePicker();
        activity = new ArrayList<>();
//        USE ACTMODEL TO INSERT ACTIVITY WITH UID AND DATEMODEL TO ADD DATE
        ActivityModel actModel = new ActivityModel();


        Intent intent = getIntent();
        uid= intent.getStringExtra("id");
        etUid.setText(uid);

        dateButton.setText(getTodaysDate());

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,activity);
        lvActivity.setAdapter(adapter);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String act = etActivity.getText().toString().trim();
                if(act.isEmpty())
                {

                    etActivity.setError("Activity can't be empty");
                    etActivity.requestFocus();
                    return;

                }
                else {

                    activity.add(act);
                    actModel.setId(uid);
                    actModel.setActivity(act);

                }
                actModel.setDate(date);
                ActivityDBHelper activityDBHelper = new ActivityDBHelper(EditTripActivity.this);
                activityDBHelper.insertActivity(actModel);

                refreshlist();
                etActivity.setText(null);
//                Toast.makeText(EditTripActivity.this, ""+date , Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void refreshlist() {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,activity);
        lvActivity.setAdapter(adapter);
    }

    private void getID() {
        dateButton = findViewById(R.id.datePickerButton);
        btnAdd = findViewById(R.id.btn_edit_add);
        etActivity = findViewById(R.id.et_edit_activity);
        etUid = findViewById(R.id.tv_edit_uid);
        lvActivity = findViewById(R.id.lv_edit_activity);
    }


    private String getTodaysDate()
    {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month = month + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return makeDateString(day, month, year);
    }

    private void initDatePicker()
    {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day)
            {
                month = month + 1;
                date = makeDateString(day, month, year);
                dateButton.setText(date);

            }
        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);
        //datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());

    }

    private String makeDateString(int day, int month, int year)
    {
        return getMonthFormat(month) + " " + day + " " + year;
    }

    private String getMonthFormat(int month)
    {
        if(month == 1)
            return "JAN";
        if(month == 2)
            return "FEB";
        if(month == 3)
            return "MAR";
        if(month == 4)
            return "APR";
        if(month == 5)
            return "MAY";
        if(month == 6)
            return "JUN";
        if(month == 7)
            return "JUL";
        if(month == 8)
            return "AUG";
        if(month == 9)
            return "SEP";
        if(month == 10)
            return "OCT";
        if(month == 11)
            return "NOV";
        if(month == 12)
            return "DEC";

        //default should never happen
        return "JAN";
    }

    public void openDatePicker(View view)
    {
        datePickerDialog.show();
    }
}