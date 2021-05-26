package com.example.spacecruiser;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import static com.example.spacecruiser.GameFunction.screenRatioX;
import static com.example.spacecruiser.GameFunction.screenRatioY;

public class RubberDuck
{
    //variables
    public int speed = 10;
    int x = 0, y, width, height;
    Bitmap duck;

    RubberDuck (Resources res) {

        //makes duck bitmap field
        duck = BitmapFactory.decodeResource(res, R.drawable.duck);

        width = duck.getWidth();
        height = duck.getHeight();

        width /= 6;
        height /= 6;

        width = (int) (width * screenRatioX);
        height = (int) (height * screenRatioY);

        duck = Bitmap.createScaledBitmap(duck, width, height, false);

        y = -height;
    }

    //return duck bitmap
    Bitmap getDuck()
    {
        return duck;
    }

    //hit box
    Rect getCollisionShape()
    {
        return new Rect(x, y, x + width, y + height);
    }
}
