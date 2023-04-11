package com.travelease.nitant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    EditText etName,etEmail,etContact,etPassword,etConPass;
    Button btnRegister;
    TextView tvLogin;

    String uname,email,password,contact,conpass;
    APIInterface apiInterface;
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

        apiInterface = APIClient.getAvik().create(APIInterface.class);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uname = etName.getText().toString().trim();
                contact = etContact.getText().toString().trim();
                email = etEmail.getText().toString().trim();
                password = etPassword.getText().toString().trim();
                conpass = etConPass.getText().toString().trim();
                if(uname.isEmpty())
                {
                    etName.setError("Username is Required");
                    etName.requestFocus();
                    return;
                }
                if(contact.isEmpty())
                {
                    etContact.setError("Contact is Required");
                    etContact.requestFocus();
                    return;
                }
                if(!(contact.length() ==10))
                {
                    etContact.setError("Contact is Required");
                    etContact.requestFocus();
                    return;
                }
                if(!Patterns.PHONE.matcher(contact).matches())
                {
                    etContact.setError("Enter Valid Contact Number");
                    etContact.requestFocus();
                    return;
                }
                if(email.isEmpty())
                {
                    etEmail.setError("Email is Required");
                    etEmail.requestFocus();
                    return;
                }
                if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
                {
                    etEmail.setError("Enter Valid Email ID");
                    etEmail.requestFocus();
                    return;
                }
                if(password.isEmpty())
                {
                    etPassword.setError("Password is Required");
                    etPassword.requestFocus();
                    return;
                }
                if(password.length()<6)
                {
                    etPassword.setError("Min length must be 6 characters");
                    etPassword.requestFocus();
                    return;
                }
                if(conpass.isEmpty())
                {
                    etConPass.setError("Password Does not match");
                    etConPass.requestFocus();
                    return;
                }

                if(!password.equals(conpass))
                {
                    etConPass.setError("Password Does not match");
                    etConPass.requestFocus();
                    return;
                }
                Call call=apiInterface.insert(uname, Double.valueOf(contact),email,password);
                call.enqueue(new Callback() {
                    @Override
                    public void onResponse(Call call, Response response) {
                        Toast.makeText(RegisterActivity.this, "Registration Successful", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call call, Throwable t) {
                        Toast.makeText(RegisterActivity.this, "Error in Registraion", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });

    }

    private void VerifyDetail(String uname, String contact, String email, String password, String conpass) {

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