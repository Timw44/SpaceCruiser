package com.example.spacecruiser;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       initiatePlayBtn();
       initiateHighScoreBtn();
    }

    private void initiatePlayBtn()
    {
        Button playBtn = (Button) findViewById(R.id.playBtn);
        playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, GameScreen.class));
            }
        });
    }

    private void initiateHighScoreBtn()
    {
        Button playBtn = (Button) findViewById(R.id.highScoreBtn);
        playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, HighscoreScreen.class));
            }
        });
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