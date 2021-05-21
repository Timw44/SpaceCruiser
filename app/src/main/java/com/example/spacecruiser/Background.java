package com.example.spacecruiser;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Background
{
    //variables
    int x, y = 0;
    Bitmap background;

    //makes a plain that the background can move on
    Background(int screenX, int screenY, Resources res) {

        background = BitmapFactory.decodeResource(res, R.drawable.space);
        background = Bitmap.createScaledBitmap(background, screenX, screenY, false);

    }
}
