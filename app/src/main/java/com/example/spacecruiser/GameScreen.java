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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.spacecruiser.splashscreen.SplashScreen;
import com.google.firebase.database.FirebaseDatabase;

public class GameScreen extends AppCompatActivity
{
    //variables
    GameFunction gameFunction;
    Button beginBtn;
    Button backBtn;
    TextView intro;
    ImageView ship;
    TextView highScoreTX;
    FirebaseDatabase database;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game);
        getSupportActionBar().hide();

        //database = new FirebaseDatabase();

        highScoreTX = findViewById(R.id.personalHSTX);
        SharedPreferences prefs = getSharedPreferences("game", MODE_PRIVATE);
        highScoreTX.setText("" + prefs.getInt("highscore2", 0));


        if(prefs.getInt("highscore1", 0) != prefs.getInt("highscore2", 0))
        {
            SharedPreferences.Editor editor = prefs.edit();
            editor.putInt("highscore2", prefs.getInt("highscore1", 0));
            editor.apply();
            highScoreTX.setText("" + prefs.getInt("highscore1", 0));
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
