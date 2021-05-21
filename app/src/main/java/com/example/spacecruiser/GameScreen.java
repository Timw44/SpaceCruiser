package com.example.spacecruiser;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Point;
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
    //variables
    GameFunction gameFunction;
    Button beginBtn;
    Button backBtn;
    TextView intro;
    ImageView ship;
    TextView highScoreTX;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game);
        getSupportActionBar().hide();

        highScoreTX = findViewById(R.id.personalHSTX);
        SharedPreferences prefs = getSharedPreferences("game", MODE_PRIVATE);


        if(prefs.getInt("highscore", 0) != Integer.parseInt(highScoreTX.getText().toString()))
        {
            highScoreTX.setText("" + prefs.getInt("highscore", 0));
            endGame();
        }


        Point point = new Point();
        getWindowManager().getDefaultDisplay().getSize(point);

        gameFunction = new GameFunction(this, point.x, point.y);
    }




//    @Override
//    protected void onPause() {
//        super.onPause();
//        gameFunction.stop();
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        gameFunction.start();
//    }


    public void startGame(View view)
    {
        beginBtn = (Button) findViewById(R.id.beginBtn);
        backBtn = (Button) findViewById(R.id.backGameBtn);
        intro = (TextView) findViewById(R.id.introTX);
        ship = (ImageView) findViewById(R.id.SpaceCruiser);

        beginBtn.setVisibility(View.GONE);
        backBtn.setVisibility(View.GONE);
        intro.setVisibility(View.GONE);
        //ship.setVisibility(View.VISIBLE);
        setContentView(gameFunction);
        super.onResume();
        gameFunction.start();
    }

    public void endGame()
    {
        beginBtn = (Button) findViewById(R.id.beginBtn);
        backBtn = (Button) findViewById(R.id.backGameBtn);
        intro = (TextView) findViewById(R.id.introTX);
        //finish();
        //startActivity(getIntent());
        //super.onPause();
        //gameFunction.stop();
        beginBtn.setText("Play Again");
        intro.setVisibility(View.GONE);
        beginBtn.setVisibility(View.VISIBLE);
        backBtn.setVisibility(View.VISIBLE);
    }

    public void backToMainMenu(View view)
    {
        finish();
    }
}
