package com.example.spacecruiser;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import static com.example.spacecruiser.GameFunction.screenRatioX;
import static com.example.spacecruiser.GameFunction.screenRatioY;

public class Player
{
    boolean isMovingLeft, isMovingRight = false;
    int x, y, width, height;
    Bitmap spaceShip;

    Player(int screenY, Resources res)
    {
        spaceShip = BitmapFactory.decodeResource(res, R.drawable.spacecrusier);

        width = spaceShip.getWidth();
        height = spaceShip.getHeight();

        width /= 4;
        height /= 4;

        width = (int) (width * screenRatioX);
        height = (int) (height * screenRatioY);

        spaceShip = Bitmap.createScaledBitmap(spaceShip, width, height, false);

        y = screenY / 2;
        x = (int) (64 * screenRatioX);
    }

}
