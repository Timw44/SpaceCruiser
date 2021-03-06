package com.example.spacecruiser;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import static com.example.spacecruiser.GameFunction.screenRatioX;
import static com.example.spacecruiser.GameFunction.screenRatioY;


public class Bullet {
//variables
    int x, y, width, height;
    Bitmap bullet;

    Bullet (Resources res) {

        bullet = BitmapFactory.decodeResource(res, R.drawable.playershot);

        width = bullet.getWidth();
        height = bullet.getHeight();

        width /= 4;
        height /= 4;

        width = (int) (width * screenRatioX);
        height = (int) (height * screenRatioY);

        bullet = Bitmap.createScaledBitmap(bullet, width, height, false);

    }

    //hit box
    Rect getCollisionShape () {
        return new Rect(x, y, x + width, y + height);
    }

}
