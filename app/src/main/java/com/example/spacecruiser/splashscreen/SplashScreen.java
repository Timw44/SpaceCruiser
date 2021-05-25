package com.example.spacecruiser.splashscreen;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.example.spacecruiser.MainActivity;
import com.example.spacecruiser.R;

public class SplashScreen extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        getSupportActionBar().hide();

        //displays this activity when app is opened
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(SplashScreen.this, MainActivity.class);
                startActivity(i);
                finish();
            }

        }, 3*1000); // wait for 3 seconds
    }
}
