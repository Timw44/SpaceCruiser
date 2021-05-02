package com.example.spacecruiser;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.spacecruiser.splashscreen.SplashScreen;

public class GameScreen extends AppCompatActivity
{
    Button beginBtn = (Button) findViewById(R.id.beginBtn);
    Button backBtn = (Button) findViewById(R.id.backGameBtn);
    TextView intro = (TextView) findViewById(R.id.introTX);
    ImageView ship = (ImageView) findViewById(R.id.SpaceCruiser);

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
    }

    public void backToMainMenu(View view)
    {
        finish();
    }
}
