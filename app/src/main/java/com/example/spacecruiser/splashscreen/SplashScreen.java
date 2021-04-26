package com.example.spacecruiser.splashscreen;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.spacecruiser.R;

public class SplashScreen extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        getSupportActionBar().hide();
    }
}
