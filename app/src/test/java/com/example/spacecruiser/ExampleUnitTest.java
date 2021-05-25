package com.example.spacecruiser;

import android.graphics.Rect;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    @Test
    public void collisionXSame()
    {
        Rect enemy = new Rect(1,5,1,3);
        Rect player = new Rect(1,1,1,1);

        assertEquals(false, Rect.intersects(enemy, player));
    }
    @Test
    public void collisionYSame()
    {
        Rect enemy = new Rect(5,1,4,1);
        Rect player = new Rect(1,1,1,1);

        assertEquals(false, Rect.intersects(enemy, player));
    }
    @Test
    public void collision()
    {
        Rect enemy = new Rect(1,1,1,1);
        Rect player = new Rect(1,1,1,1);

        assertEquals(true, Rect.intersects(enemy, player));
    }

    @Test
    public void test()
    {

    }
}