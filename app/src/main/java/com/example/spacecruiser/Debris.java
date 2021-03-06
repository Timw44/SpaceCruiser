package com.example.spacecruiser;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import static com.example.spacecruiser.GameFunction.screenRatioX;
import static com.example.spacecruiser.GameFunction.screenRatioY;

public class Debris
{
    //variables
    public int speed = 10;
    int x = 0, y, width, height, debrisCounter = 1;
    Bitmap debris1, debris2, debris3;

    Debris (Resources res) {

        //makes debris fields
        debris1 = BitmapFactory.decodeResource(res, R.drawable.debris1);
        debris2 = BitmapFactory.decodeResource(res, R.drawable.debris2);
        debris3 = BitmapFactory.decodeResource(res, R.drawable.debris3);

        width = debris1.getWidth();
        height = debris1.getHeight();

        width /= 6;
        height /= 6;

        width = (int) (width * screenRatioX);
        height = (int) (height * screenRatioY);

        debris1 = Bitmap.createScaledBitmap(debris1, width, height, false);
        debris2 = Bitmap.createScaledBitmap(debris2, width, height, false);
        debris3 = Bitmap.createScaledBitmap(debris3, width, height, false);

        y = -height;
    }

    //return each debris's bitmap
    Bitmap getDebris1()
    {
        return debris1;
    }

    Bitmap getDebris2()
    {
        return debris2;
    }

    Bitmap getDebris3()
    {
        return debris3;
    }

    //hit box
    Rect getCollisionShape()
    {
        return new Rect(x, y, x + width, y + height);
    }
}
