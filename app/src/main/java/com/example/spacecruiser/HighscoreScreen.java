package com.example.spacecruiser;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

public class HighscoreScreen extends AppCompatActivity
{
    //variables
    TextView first, second, third, fourth, fifth, sixth, seventh, eighth, ninth, tenth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.highscore);
        getSupportActionBar().hide();
    }

    public void setLeaderBoard(View view)
    {
        first = (TextView) findViewById(R.id.scoreTX1);
        second = (TextView) findViewById(R.id.scoreTX2);
        third = (TextView) findViewById(R.id.scoreTX3);
        fourth = (TextView) findViewById(R.id.scoreTX4);
        fifth = (TextView) findViewById(R.id.scoreTX5);
        sixth = (TextView) findViewById(R.id.scoreTX6);
        seventh = (TextView) findViewById(R.id.scoreTX7);
        eighth = (TextView) findViewById(R.id.scoreTX8);
        ninth = (TextView) findViewById(R.id.scoreTX9);
        tenth = (TextView) findViewById(R.id.scoreTX10);


    }

    public void back(View view)
    {
        finish();
    }
}
