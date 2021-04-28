package com.example.spacecruiser;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    //variables


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

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


    public void creditsClicked(View view)
    {
        ImageView creditsPanel = (ImageView) findViewById(R.id.creditsIV);
        Button closePanelBtn = (Button) findViewById(R.id.closePanelBtn);
        TextView credits = (TextView) findViewById(R.id.creditsTV);
        creditsPanel.setVisibility(View.VISIBLE);
        closePanelBtn.setVisibility(View.VISIBLE);
        credits.setVisibility(View.VISIBLE);
    }

    public void closePanel(View view)
    {
        ImageView creditsPanel = (ImageView) findViewById(R.id.creditsIV);
        Button closePanelBtn = (Button) findViewById(R.id.closePanelBtn);
        TextView credits = (TextView) findViewById(R.id.creditsTV);
        creditsPanel.setVisibility(View.GONE);
        closePanelBtn.setVisibility(View.GONE);
        credits.setVisibility(View.GONE);
    }

    public void exit(View view)
    {
        finish();
        System.exit(0);
    }
}