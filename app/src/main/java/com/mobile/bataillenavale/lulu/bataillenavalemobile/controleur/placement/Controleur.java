package com.mobile.bataillenavale.lulu.bataillenavalemobile.controleur.placement;

import android.view.View;

/**
 * Created by simon on 20/12/17.
 */

public interface Controleur {

    boolean canHostBoat(View boat,int x,int y);
    void obtainBoat(View boat, int xCell, int yCell);
    void removeBoat(int id);

    void tint(View boat, int xCell, int yCell,boolean enter);
}
