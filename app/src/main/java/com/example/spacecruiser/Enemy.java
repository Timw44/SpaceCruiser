package com.example.spacecruiser;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import static com.example.spacecruiser.GameFunction.screenRatioX;
import static com.example.spacecruiser.GameFunction.screenRatioY;

public class Enemy
{
    public int speed = 20;
    public boolean wasShot = true;
    int x = 0, y, width, height, enemyCounter = 1;
    Bitmap alien1, alien2, alien3, alien4;
    Bitmap debris1, debris2, debris3;

    Enemy (Resources res) {

        alien1 = BitmapFactory.decodeResource(res, R.drawable.ufo);
        alien2 = BitmapFactory.decodeResource(res, R.drawable.ufo);
        alien3 = BitmapFactory.decodeResource(res, R.drawable.ufo);
        alien4 = BitmapFactory.decodeResource(res, R.drawable.ufo);

        width = alien1.getWidth();
        height = alien1.getHeight();

        width /= 6;
        height /= 6;

        width = (int) (width * screenRatioX);
        height = (int) (height * screenRatioY);

        alien1 = Bitmap.createScaledBitmap(alien1, width, height, false);
        alien2 = Bitmap.createScaledBitmap(alien2, width, height, false);
        alien3 = Bitmap.createScaledBitmap(alien3, width, height, false);
        alien4 = Bitmap.createScaledBitmap(alien4, width, height, false);

        y = -height;
    }

    Bitmap getEnemy()
    {
        if (enemyCounter == 1) {
            enemyCounter++;
            return alien1;
        }

        if (enemyCounter == 2) {
            enemyCounter++;
            return alien2;
        }

        if (enemyCounter == 3) {
            enemyCounter++;
            return alien3;
        }

        enemyCounter = 1;

        return alien4;
    }

    Rect getCollisionShape()
    {
        return new Rect(x, y, x + width, y + height);
    }
}
