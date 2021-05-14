package com.example.spacecruiser;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;


public class GameFunction extends SurfaceView implements Runnable
{
    private Thread thread;
    public static boolean isPlaying = false;
    private boolean isGameOver = false;
    private Background backg1, backg2;
    private Paint paint;
    private Player player;
    private List<Bullet> bullets;
    private int screenX, screenY, score = 0;
    public static float screenRatioX, screenRatioY;

    public GameFunction(Context context, int screenX, int screenY) {
        super(context);

        this.screenX = screenX;
        this.screenY = screenY;
        screenRatioX = 1080f / screenX;
        screenRatioY = 1920f / screenY;

        player = new Player(this, screenY, getResources());
        bullets = new ArrayList<>();

        backg1 = new Background(screenX, screenY, getResources());
        backg2 = new Background(screenX, screenY, getResources());
        backg2.y = 0 - screenY;
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
        //moves background
        backg1.y += 10 * screenRatioY;
        backg2.y += 10 * screenRatioY;

        if (backg1.y >= screenY) {
            backg1.y = backg2.y - screenY;
        }

        if (backg2.y >= screenY) {
            backg2.y = backg1.y - screenY;
        }

        //moves player and checks that they don't leave the screen
        if(player.isMovingRight && player.x <= screenX - player.width)
        {
            player.x += 20;
        }
        if(player.isMovingLeft && player.x > 0)
        {
            player.x -= 20;
        }

        //makes bullets and deletes them
        List<Bullet> dud = new ArrayList<>();

        for (Bullet bullet : bullets)
        {
            if(bullet.y < 0)
            {
                dud.add(bullet);
            }
            bullet.y -= 50 * screenRatioY;
        }
        player.shoot++;

        /*for (Bullet bullet : dud)
        {
            dud.remove(bullet);
        }*/
    }

    private void draw()
    {
//draws everything
        if(getHolder().getSurface().isValid())
        {
            Canvas canvas = getHolder().lockCanvas();
            canvas.drawBitmap(backg1.background, backg1.x, backg1.y, paint);
            canvas.drawBitmap(backg2.background, backg2.x, backg2.y, paint);

            canvas.drawBitmap(player.getPlayer(), player.x, player.y, paint);

            for (Bullet bullet : bullets)
            {
                canvas.drawBitmap(bullet.bullet, bullet.x, bullet.y, paint);
            }

            getHolder().unlockCanvasAndPost(canvas);
        }
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
                   player.isMovingLeft = true;
                }
                else {
                    player.isMovingRight = true;
                }
                //player.shoot = 1;
                break;
            case MotionEvent.ACTION_UP:
                player.isMovingLeft = false;
                player.isMovingRight = false;
                //player.shoot = 0;
                break;
        }
        return true;
    }

    public void newBullet()
    {
        Bullet bullet = new Bullet(getResources());
        bullet.x = player.x + (player.width / 2);
        bullet.y = player.y;
        bullets.add(bullet);
    }
}
