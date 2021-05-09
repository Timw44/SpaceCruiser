package com.example.spacecruiser;

import android.content.Context;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;


public class GameFunction extends SurfaceView implements Runnable
{
    private Thread thread;
    public static boolean isPlaying = false;
    private boolean isGameOver, isMoving = false;
    private int screenX, score = 0;

    public GameFunction(Context context) {
        super(context);
    }

    @Override
    public void run() {
        while(isPlaying)
        {

        }
    }

    public void start()
    {
        isPlaying = true;
        thread = new Thread(this);
        thread.start();
    }

    public void stop()
    {
        try {
            isPlaying = true;
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (event.getX() < screenX / 2) {
                   isMoving = true;
                }
                break;
            case MotionEvent.ACTION_UP:
                isMoving = false;
                if (event.getX() > screenX / 2)
                    //toShoot++;
                break;
        }
        return true;
    }
}
