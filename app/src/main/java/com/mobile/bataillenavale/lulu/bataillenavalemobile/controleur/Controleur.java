package com.mobile.bataillenavale.lulu.bataillenavalemobile.controleur;

/**
 * Created by simon on 20/12/17.
 */

public interface Controleur {
    boolean canHoastBoat(int x,int y);

    void obtaineBoat(CharSequence boatId, int xCell, int yCell);
}
