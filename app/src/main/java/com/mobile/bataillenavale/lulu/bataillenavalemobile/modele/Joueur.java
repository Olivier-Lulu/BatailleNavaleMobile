package com.mobile.bataillenavale.lulu.bataillenavalemobile.modele;

import java.util.Vector;

/**
 * Created by simon on 08/01/18.
 */

public interface Joueur {

    boolean toucher(int x, int y);

    Vector<Integer> tirer();

    void reponse(int x, int y, boolean toucher);
}
