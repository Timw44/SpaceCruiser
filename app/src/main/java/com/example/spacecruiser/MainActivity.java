package com.example.spacecruiser;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void playBtnClicked(View view)
    {
        setContentView(R.layout.game);
    }

    public void highScoreClicked(View view)
    {
        setContentView(R.layout.highscore);
    }

    public void exit(View view)
    {
        finish();
        System.exit(0);
    }
}