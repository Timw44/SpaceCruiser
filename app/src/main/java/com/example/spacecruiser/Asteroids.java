package com.example.spacecruiser;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import static com.example.spacecruiser.GameFunction.screenRatioX;
import static com.example.spacecruiser.GameFunction.screenRatioY;

public class Asteroids
{
    //variables
    public int speed = 10;
    int x = 0, y, width, height, asterCounter = 1;
    Bitmap aster1, aster2, aster3;

    Asteroids (Resources res) {

        //creates field for them to be drawn
        aster1 = BitmapFactory.decodeResource(res, R.drawable.asteroid);
        aster2 = BitmapFactory.decodeResource(res, R.drawable.asteroid);
        aster3 = BitmapFactory.decodeResource(res, R.drawable.asteroid);

        width = aster1.getWidth();
        height = aster1.getHeight();

        width /= 6;
        height /= 6;

        width = (int) (width * screenRatioX);
        height = (int) (height * screenRatioY);

        aster1 = Bitmap.createScaledBitmap(aster1, width, height, false);
        aster2 = Bitmap.createScaledBitmap(aster2, width, height, false);
        aster3 = Bitmap.createScaledBitmap(aster3, width, height, false);

        y = -height;
    }

    //draws the three asteroids
    Bitmap getAsteroid()
    {
        if (asterCounter == 1) {
            asterCounter++;
            return aster1;
        }

        if (asterCounter == 2) {
            asterCounter++;
            return aster2;
        }

        asterCounter = 1;

        return aster3;
    }

    //hit box
    Rect getCollisionShape()
    {
        return new Rect(x, y, x + width, y + height);
    }
}
