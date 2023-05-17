package com.travelease.nitant;

import static android.content.ContentValues.TAG;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etUname,etPassword;
    TextView tvcreateaccount,tvforgotpassword;
    CheckBox cbRemember;
    Button btnLogin;
    APIInterface apiInterface;
    String uname,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Objects.requireNonNull(getSupportActionBar()).hide();
        getID();
        apiInterface=APIClient.getAvik().create(APIInterface.class);
        tvcreateaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(i);
            }
        });
        tvforgotpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,ForgotPasswordActivity.class);
                startActivity(intent);
            }
        });
        SharedPreferences sharedPreferences = getSharedPreferences("regi", Context.MODE_PRIVATE);

        boolean isRemember = sharedPreferences.getBoolean("is_remember", false);
        if(isRemember==true)
        {
            String uname = sharedPreferences.getString("uname", "");
            String password = sharedPreferences.getString("password", "");
            etUname.setText(uname);
            etPassword.setText(password);
        }

        btnLogin.setOnClickListener(this);

    }

    private void getID() {
        etUname=findViewById(R.id.et_login_uname);
        etPassword=findViewById(R.id.et_login_passsword);
        tvcreateaccount=findViewById(R.id.tv_login_createaccount);
        cbRemember=findViewById(R.id.cb_login_remember);
        btnLogin=findViewById(R.id.btn_login_login);
        tvforgotpassword=findViewById(R.id.tv_login_forgotpassword);
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.btn_login_login)
        {

            //TO SET TO RESUME LOGIN WITH SERVER
            uname=etUname.getText().toString().trim();
            password=etPassword.getText().toString().trim();

            SharedPreferences sharedPreferences = getSharedPreferences("regi", Context.MODE_PRIVATE);
            SharedPreferences.Editor editorRegi = sharedPreferences.edit();
            if(cbRemember.isChecked()) {
                editorRegi.putBoolean("is_remember", true);
                editorRegi.putString("uname",uname);
                editorRegi.putString("password",password);
                editorRegi.apply();
            }

            SharedPreferences sharedlogin = getSharedPreferences("login", Context.MODE_PRIVATE);
            SharedPreferences.Editor editorLogin = sharedlogin.edit();

            Call call=apiInterface.login(uname,password);
            
            call.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {

                    if(!(response.body() ==null))
                    {
                        editorLogin.putBoolean("is_Login",true);
                        editorLogin.apply();
                        Intent intent = new Intent(LoginActivity.this,HomeActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        finish();
                    }
                    
                }

                @Override
                public void onFailure(Call call, Throwable t) {
//                    Log.d(TAG, "onFailure: "+t);
                    Toast.makeText(LoginActivity.this, "User Name and Password does not match", Toast.LENGTH_SHORT).show();
                    finish();
                    overridePendingTransition( 0, 0);
                    startActivity(getIntent());
                    overridePendingTransition( 0, 0);
                }
            });
            
            
        }
    }
}