package com.travelease.nitant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Objects;

public class RegisterActivity extends AppCompatActivity {

    EditText etName,etEmail,etContact,etPassword,etConPass;
    Button btnRegister;
    TextView tvLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Objects.requireNonNull(getSupportActionBar()).hide();
        getID();

        //Use "regi" File for session management
        SharedPreferences sharedPreferences = getSharedPreferences("regi", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("is_remember",false);
        editor.apply();

        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });

    }

    //Assignings IDs
    private void getID() {
        etName=findViewById(R.id.et_regi_name);
        etEmail=findViewById(R.id.et_regi_email);
        etContact=findViewById(R.id.et_regi_contact);
        etPassword=findViewById(R.id.et_regi_password);
        etConPass=findViewById(R.id.et_regi_conpassword);
        btnRegister=findViewById(R.id.btn_regi_register);
        tvLogin=findViewById(R.id.tv_regi_Login);
    }
}