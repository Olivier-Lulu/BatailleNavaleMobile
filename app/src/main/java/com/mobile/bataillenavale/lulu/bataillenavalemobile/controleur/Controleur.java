package com.mobile.bataillenavale.lulu.bataillenavalemobile.controleur;

import android.view.View;

/**
 * Created by simon on 20/12/17.
 */

public interface Controleur {
    boolean canHoastBoat(int x,int y);

    void obtaineBoat(View boat, int xCell, int yCell);
}
