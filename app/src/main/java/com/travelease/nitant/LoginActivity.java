package com.travelease.nitant;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Objects;
import java.util.Set;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etEmail,etPassword;
    TextView tvcreateaccount;
    CheckBox cbRemember;
    Button btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Objects.requireNonNull(getSupportActionBar()).hide();
        getID();

        tvcreateaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(i);
            }
        });

        btnLogin.setOnClickListener(this);

    }

    private void getID() {
        etEmail=findViewById(R.id.et_login_email);
        etPassword=findViewById(R.id.et_login_passsword);
        tvcreateaccount=findViewById(R.id.tv_login_createaccount);
        cbRemember=findViewById(R.id.cb_login_remember);
        btnLogin=findViewById(R.id.btn_login_login);
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.btn_login_login)
        {
            if(cbRemember.isChecked()) {
                SharedPreferences sharedPreferences = getSharedPreferences("regi", Context.MODE_PRIVATE);
                SharedPreferences.Editor editorRegi = sharedPreferences.edit();
                editorRegi.putBoolean("is_remember", true);
                editorRegi.apply();
            }
            Intent intent = new Intent(LoginActivity.this,HomeActivity.class);
            startActivity(intent);
        }
    }
}