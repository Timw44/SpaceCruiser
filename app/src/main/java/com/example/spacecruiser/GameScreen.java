package com.example.spacecruiser;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.spacecruiser.splashscreen.SplashScreen;

public class GameScreen extends AppCompatActivity
{
    Button beginBtn = (Button) findViewById(R.id.beginBtn);
    Button backBtn = (Button) findViewById(R.id.backGameBtn);
    TextView intro = (TextView) findViewById(R.id.introTX);
    ImageView ship = (ImageView) findViewById(R.id.SpaceCruiser);
    private ViewGroup mainLayout = (ConstraintLayout) findViewById(R.id.game);
    private int xDelta;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game);
        getSupportActionBar().hide();
    }


    public void startGame(View view)
    {
        beginBtn.setVisibility(View.GONE);
        backBtn.setVisibility(View.GONE);
        intro.setVisibility(View.GONE);
        ship.setVisibility(View.VISIBLE);
        GameFunction.isPlaying = true;
    }

    public void backToMainMenu(View view)
    {
        finish();
    }
}
