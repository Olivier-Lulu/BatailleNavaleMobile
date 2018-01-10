package com.mobile.bataillenavale.lulu.bataillenavalemobile.controleur;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import com.mobile.bataillenavale.lulu.bataillenavalemobile.R;
import com.mobile.bataillenavale.lulu.bataillenavalemobile.controleur.jeu.BaseEcranJeu;
import com.mobile.bataillenavale.lulu.bataillenavalemobile.controleur.jeu.EcranAdverseActivity;

import java.util.Vector;

/**
 * Inspired by Edward Brey on 21/10/13
 */

public class OnSwipeTouchListener implements View.OnTouchListener {
    /**
     * Detects left and right swipes across a view.
     */

    private final GestureDetector gestureDetector;

    public OnSwipeTouchListener(GestureDetector gestureDetector) {
        this.gestureDetector = gestureDetector;
    }

    public boolean onTouch(View v, MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }

}

