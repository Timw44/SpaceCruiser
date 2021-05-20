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
    int shoot = 0;
    int x, y, width, height, shotCounter = 0;
    Bitmap spaceShip, shot1, shot2, shot3;
    private GameFunction gameFunction;

    Player(GameFunction gameFunction, int screenY, Resources res)
    {
        this.gameFunction = gameFunction;

        //creates player ship
        spaceShip = BitmapFactory.decodeResource(res, R.drawable.spacecrusier);

        width = spaceShip.getWidth();
        height = spaceShip.getHeight();

        width /= 4;
        height /= 4;

        width = (int) (width * screenRatioX);
        height = (int) (height * screenRatioY);

        spaceShip = Bitmap.createScaledBitmap(spaceShip, width, height, false);

        //creates player bullets
        shot1 = BitmapFactory.decodeResource(res, R.drawable.playershot);
        shot2 = BitmapFactory.decodeResource(res, R.drawable.playershot);
        shot3 = BitmapFactory.decodeResource(res, R.drawable.playershot);

        shot1 = Bitmap.createScaledBitmap(shot1, width, height, false);
        shot2 = Bitmap.createScaledBitmap(shot2, width, height, false);
        shot3 = Bitmap.createScaledBitmap(shot3, width, height, false);

        y = (int) (screenY / 1.2);
        x = (int) (64 * screenRatioX);
    }

    Bitmap getPlayer() {
        if (shoot % 20 == 0) {

            gameFunction.newBullet();
            if (shotCounter == 1) {
                shotCounter++;
                return shot1;
            }

            if (shotCounter == 2) {
                shotCounter++;
                return shot2;
            }

            shotCounter = 1;
            return shot3;
        }

        return spaceShip;
    }

    Rect getCollisionShape()
    {
        return new Rect(x, y, (x + width)/2, (y + height)/2);
    }
}
