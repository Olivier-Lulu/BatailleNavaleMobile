package com.mobile.bataillenavale.lulu.bataillenavalemobile.controleur;

import android.view.GestureDetector;
import android.view.MotionEvent;

/**
 * Created by lulu on 10/01/18.
 */

public class GestureListener extends GestureDetector.SimpleOnGestureListener {

    private static final int SWIPE_DISTANCE_THRESHOLD = 100;
    private static final int SWIPE_VELOCITY_THRESHOLD = 100;

    public void onSwipeLeft(){}

    public void onSwipeRight(){}


    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        float distanceX = e2.getX() - e1.getX();
        float distanceY = e2.getY() - e1.getY();
        if (Math.abs(distanceX) > Math.abs(distanceY) && Math.abs(distanceX) > SWIPE_DISTANCE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
            if (distanceX > 0)
                onSwipeRight();
            else
                onSwipeLeft();
            return true;
        }
        return false;
    }
}

