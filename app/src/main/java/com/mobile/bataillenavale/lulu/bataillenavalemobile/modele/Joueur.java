package com.mobile.bataillenavale.lulu.bataillenavalemobile.modele;

import java.util.Vector;

/**
 * Created by simon on 08/01/18.
 */

public abstract class Joueur {
    protected PlateauModele plateauModele = new PlateauModele();

    public int toucher(int x, int y){
        return plateauModele.toucher(x, y);
    }

    abstract public Vector<Integer> tirer();

    abstract public void reponse(int x, int y, boolean toucher);
}
