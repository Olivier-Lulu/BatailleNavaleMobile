package com.mobile.bataillenavale.lulu.bataillenavalemobile.jeu;

/**
 * Created by simon on 20/12/17.
 */

interface Controleur {
    boolean canHoastBoat(int x,int y);

    void obtaineBoat(CharSequence boatId, int xCell, int yCell);
}
