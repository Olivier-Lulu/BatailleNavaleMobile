package com.mobile.bataillenavale.lulu.bataillenavalemobile.jeu;

import android.view.View;

/**
 * Created by simon on 20/12/17.
 */

interface Controleur {
    boolean canHostBoat(int x,int y);

    void obtaineBoat(View boat, int xCell, int yCell);
}
