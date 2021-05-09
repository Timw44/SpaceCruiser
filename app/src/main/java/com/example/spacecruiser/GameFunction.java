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
    private Background backg1, backg2;
    private int screenX, screenY, score = 0;

    public GameFunction(Context context, int screenX, int screenY) {
        super(context);

        this.screenX = screenX;
        this.screenY = screenY;

        backg1 = new Background(screenX, screenY, getResources());
        backg2 = new Background(screenX, screenY, getResources());
        backg2.y = screenY;
    }



    @Override
    public void run() {
        while(isPlaying)
        {
            update();
            draw();
            hold();
        }
    }

    private void update()
    {
        backg1.x -= 10;
        backg2.x -= 10;

        if (backg1.y + backg1.background.getHeight() < 0) {
            backg1.y = screenY;
        }

        if (backg2.x + backg2.background.getHeight() < 0) {
            backg2.x = screenX;
        }
    }

    private void draw()
    {

    }

    private void hold()
    {
        try {
            thread.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
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
