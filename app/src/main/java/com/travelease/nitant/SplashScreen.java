package com.travelease.nitant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class SplashScreen extends AppCompatActivity {

    ImageView ivLogo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        ivLogo=findViewById(R.id.iv_splash_logo);
        getSupportActionBar().hide();

        SharedPreferences sharedlogin = getSharedPreferences("login", Context.MODE_PRIVATE);
        boolean isLogin = sharedlogin.getBoolean("is_Login", false);





        Animation animation = AnimationUtils.loadAnimation(this,R.anim.splashanimation);
        ivLogo.setAnimation(animation);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(isLogin)
                {
                    Intent intent = new Intent(SplashScreen.this,HomeActivity.class);
                    startActivity(intent);
                    finish();
                }
                else {
                    Intent intent = new Intent(SplashScreen.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        },4000);
    }
}