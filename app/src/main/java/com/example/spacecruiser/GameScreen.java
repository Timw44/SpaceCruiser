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

import com.example.spacecruiser.splashscreen.SplashScreen;

public class GameScreen extends AppCompatActivity
{
    Button beginBtn = (Button) findViewById(R.id.beginBtn);
    Button backBtn = (Button) findViewById(R.id.backGameBtn);
    TextView intro = (TextView) findViewById(R.id.introTX);
    ImageView ship = (ImageView) findViewById(R.id.SpaceCruiser);
    private ViewGroup mainLayout = (RelativeLayout) findViewById(R.id.main);
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
        ship.setOnTouchListener(onTouchListener());
    }


//moves player back and forth
    private View.OnTouchListener onTouchListener() {
        return new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                final int x = (int) event.getRawX();

                switch (event.getAction() & MotionEvent.ACTION_MASK) {

                    case MotionEvent.ACTION_DOWN:
                        RelativeLayout.LayoutParams lParams = (RelativeLayout.LayoutParams)
                                view.getLayoutParams();
                        xDelta = x - lParams.leftMargin;
                        break;

                    case MotionEvent.ACTION_MOVE:
                        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
                        layoutParams.leftMargin = x - xDelta;
                        layoutParams.rightMargin = 0;
                        layoutParams.bottomMargin = 0;
                        view.setLayoutParams(layoutParams);
                        break;
                }
                mainLayout.invalidate();
                return true;
            }
        };
    }

    public void backToMainMenu(View view)
    {
        finish();
    }
}
