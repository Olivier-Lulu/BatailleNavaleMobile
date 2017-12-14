package com.mobile.bataillenavale.lulu.bataillenavalemobile.modele;

import java.util.LinkedList;

/**
 * Created by lulu on 11/12/17.
 */

public class Plateau {
    private boolean[] surface;
    private LinkedList<Bateau> bateaux;

    public boolean touche() {
        return false;
    };

    public boolean toucheCoule() {
        return false;
    };

}
