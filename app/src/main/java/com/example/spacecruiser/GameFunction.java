package com.example.spacecruiser;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.preference.Preference;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.example.spacecruiser.GameScreen.database;
import static com.example.spacecruiser.GameScreen.flightName;
import static com.example.spacecruiser.GameScreen.reference;


public class GameFunction extends SurfaceView implements Runnable
{
    //variables
    private Thread thread;
    public static boolean isPlaying = false;
    private boolean isGameOver = false;
    private Background backg1, backg2;
    private Paint paint;
    private Player player;
    private Enemy[] enemies;
    private Debris[] debris;
    private RubberDuck duck;
    private Asteroids[] asteroids;
    private Random random;
    private List<Bullet> bullets;
    private int screenX, screenY, score = 0;
    private int p, a, d, r = 0;
    private SharedPreferences prefs;
    public GameScreen activity;
    public static float screenRatioX, screenRatioY;

    //constructor
    public GameFunction(GameScreen activity, int screenX, int screenY) {
        super(activity);

        prefs = activity.getSharedPreferences("game", Context.MODE_PRIVATE);

        this.activity = activity;

        this.screenX = screenX;
        this.screenY = screenY;
        screenRatioX = 1080f / screenX;
        screenRatioY = 1920f / screenY;

        player = new Player(this, screenY, getResources());
        bullets = new ArrayList<>();

        backg1 = new Background(screenX, screenY, getResources());
        backg2 = new Background(screenX, screenY, getResources());
        backg2.y = 0 - screenY;

        paint = new Paint();
        paint.setTextSize(48);
        paint.setColor(Color.WHITE);

        enemies = new Enemy[4];
        asteroids = new Asteroids[3];
        debris = new Debris[3];
        duck = new RubberDuck(getResources());

        for(int i = 0; i<enemies.length; i++)
        {
            Enemy enemy = new Enemy(getResources());
            enemies[i] = enemy;
        }
        for(int i = 0; i<asteroids.length; i++)
        {
            Asteroids asteroid = new Asteroids(getResources());
            asteroids[i] = asteroid;
        }
        for(int i = 0; i<debris.length; i++)
        {
            Debris deb = new Debris(getResources());
            debris[i] = deb;
        }
        random = new Random();
    }


//when is playing is true the game progresses
    @Override
    public void run() {
        while(isPlaying)
        {
            update();
            draw();
            hold();
        }
    }

    //calls every few milli seconds to make the illusion of motion
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
            player.x += 25;
        }
        if(player.isMovingLeft && player.x > 0)
        {
            player.x -= 25;
        }

        //makes bullets and deletes them
        List<Bullet> dud = new ArrayList<>();

        for (Bullet bullet : bullets)
        {
            if(bullet.y < 0)
            {
                dud.add(bullet);
                bullet.x = -100;
            }
            bullet.y -= 100 * screenRatioY;

            if(Rect.intersects(duck.getCollisionShape(), bullet.getCollisionShape()))//duck hits a bullet
            {
                score += 10;
                int bound = (int) (35 * screenRatioX);
                duck.speed = random.nextInt(bound);

                if (duck.speed < 20 * screenRatioX) {
                    duck.speed = (int) (20 * screenRatioX);
                }
                duck.y = 0 - random.nextInt(2000);
                duck.x = random.nextInt(screenX - duck.width);
                bullet.x = -100;
            }

            for (Enemy enemy : enemies)
            {
               if (Rect.intersects(enemy.getCollisionShape(), bullet.getCollisionShape()))//if enemy hits a bullet
                {
                    score += 2;
                    int bound = (int) (30 * screenRatioX);
                    enemy.speed = random.nextInt(bound);

                    if (enemy.speed < 10 * screenRatioX) {
                        enemy.speed = (int) (10 * screenRatioX);
                    }
                    enemy.y = 0 - random.nextInt(1000);
                    enemy.x = random.nextInt(screenX - enemy.width);
                    bullet.x = -100;
                }
            }
            for (Asteroids asteroid : asteroids)
            {
                if (Rect.intersects(asteroid.getCollisionShape(), bullet.getCollisionShape()))//if bullet hits asteroid
                {
                    bullet.x = -100;
                }
            }
            for (Debris deb : debris)
            {
                if (Rect.intersects(deb.getCollisionShape(), bullet.getCollisionShape()))//debris hits a bullet
                {
                    score++;
                    int bound = (int) (20 * screenRatioX);
                    deb.speed = random.nextInt(bound);

                    if (deb.speed < 12 * screenRatioX) {
                        deb.speed = (int) (12 * screenRatioX);
                    }
                    deb.y = 0 - random.nextInt(700);
                    deb.x = random.nextInt(screenX - deb.width);
                    bullet.x = -100;
                }
            }
        }
        player.shoot++;

        for (Enemy enemy : enemies)
        {
            enemy.y += enemy.speed;

            if(enemy.y > screenY || p < enemies.length)
            {
                int bound = (int) (30 * screenRatioX);
                enemy.speed = random.nextInt(bound);

                if (enemy.speed < 10 * screenRatioX) {
                    enemy.speed = (int) (10 * screenRatioX);
                }

                enemy.y = 0 - random.nextInt(1000);
                enemy.x = random.nextInt(screenX - enemy.width);
                p++;
            }
            if (Rect.intersects(enemy.getCollisionShape(), player.getCollisionShape())) //player hits enemy
            {
                isGameOver = true;
                return;
            }
        }//end of enemy for each loop

        for (Asteroids aster : asteroids)
        {
            aster.y += aster.speed;

            if(aster.y > screenY || a < asteroids.length)
            {
                int bound = (int) (20 * screenRatioX);
                aster.speed = random.nextInt(bound);

                if (aster.speed < 10 * screenRatioX) {
                    aster.speed = (int) (10 * screenRatioX);
                }

                aster.y = 0 - random.nextInt(300);
                aster.x = random.nextInt(screenX - aster.width);
                a++;
            }
            if (Rect.intersects(aster.getCollisionShape(), player.getCollisionShape())) //player hits asteroid
            {
                isGameOver = true;
                return;
            }
        }//end of asteroids for each loop

        for (Debris deb : debris)
        {
            deb.y += deb.speed;

            if(deb.y > screenY || d < debris.length)
            {
                int bound = (int) (30 * screenRatioX);
                deb.speed = random.nextInt(bound);

                if (deb.speed < 10 * screenRatioX) {
                    deb.speed = (int) (10 * screenRatioX);
                }

                deb.y = 0 - random.nextInt(700);
                deb.x = random.nextInt(screenX - deb.width);
                d++;
            }
            if (Rect.intersects(deb.getCollisionShape(), player.getCollisionShape())) //player hits debris
            {
                isGameOver = true;
                return;
            }
        }//end of debris for each loop

        //resets rubber duck position if goes off screen
        duck.y += duck.speed;

        if(duck.y > screenY || r == 0)
        {
            int bound = (int) (35 * screenRatioX);
            duck.speed = random.nextInt(bound);

            if (duck.speed < 20 * screenRatioX) {
                duck.speed = (int) (20 * screenRatioX);
            }
            duck.y = 0 - random.nextInt(2000);
            duck.x = random.nextInt(screenX - duck.width);
            r++;
        }

        dud.removeAll(bullets);//removes dud bullets
    }

    private void draw()
    {
//draws everything
        if(getHolder().getSurface().isValid())
        {
            Canvas canvas = getHolder().lockCanvas();
            canvas.drawBitmap(backg1.background, backg1.x, backg1.y, paint);
            canvas.drawBitmap(backg2.background, backg2.x, backg2.y, paint);

            //checks if game is over to delete the canvas
            if(isGameOver)
            {
                isPlaying = false;//ends game
                isHighScore();
                waitToExit();
                //canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
                getHolder().unlockCanvasAndPost(canvas);
                return;
            }

            paint.setColor(Color.WHITE);
            canvas.drawText(score + "", screenX/2f + 50, 100, paint);
            paint.setColor(Color.YELLOW);
            canvas.drawText("Score: ", screenX/2f - 100, 100, paint);

            canvas.drawBitmap(player.getPlayer(), player.x, player.y+500, paint);
            canvas.drawBitmap(player.spaceShip, player.x, player.y, paint);

            for (Bullet bullet : bullets)
            {
                canvas.drawBitmap(bullet.bullet, bullet.x, bullet.y, paint);
            }

            for (Enemy enemy : enemies)
            {
                canvas.drawBitmap(enemy.getEnemy(), enemy.x, enemy.y, paint);
            }

            for (Asteroids asteroid : asteroids)
            {
                canvas.drawBitmap(asteroid.getAsteroid(), asteroid.x, asteroid.y, paint);
            }

            canvas.drawBitmap(debris[0].getDebris1(), debris[0].x, debris[0].y, paint);
            canvas.drawBitmap(debris[1].getDebris2(), debris[1].x, debris[1].y, paint);
            canvas.drawBitmap(debris[2].getDebris3(), debris[2].x, debris[2].y, paint);

            canvas.drawBitmap(duck.getDuck(), duck.x, duck.y, paint);

            getHolder().unlockCanvasAndPost(canvas);
        }
    }

    private void waitToExit()
    {
        try {
            Thread.sleep(3000);
            //save flight in data base
            String high = (score + "");
            String name = flightName.getText().toString();

            reference = database.getReference("Flight");
            reference.child(high).setValue(name);

            //reload main screen
            activity.finish();
            activity.startActivity(new Intent(activity, GameScreen.class));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }// leaves game after death

    //spaces the updates
    private void hold()
    {
        try {
            thread.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //starts the run function
    public void start()
    {
        isPlaying = true;
        thread = new Thread(this);
        thread.start();
    }

    //ends run function
    public void stop()
    {
        try {
            isPlaying = false;
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //handles when the player touches to move
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
                break;
            case MotionEvent.ACTION_UP:
                player.isMovingLeft = false;
                player.isMovingRight = false;
                break;
        }
        return true;
    }

    //creates a new bullet
    public void newBullet()
    {
        Bullet bullet = new Bullet(getResources());
        bullet.x = player.x + (player.width / 2);
        bullet.y = player.y;
        bullets.add(bullet);
    }

    //checks if the score was the high score and saves it if true
    private void isHighScore()
    {
        if(score > prefs.getInt("highscore1", 0))
        {
            SharedPreferences.Editor editor = prefs.edit();
            editor.putInt("highscore1", score);
            editor.apply();
        }
    }
}
