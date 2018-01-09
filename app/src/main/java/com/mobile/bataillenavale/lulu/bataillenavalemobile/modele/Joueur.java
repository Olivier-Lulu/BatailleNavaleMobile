package com.mobile.bataillenavale.lulu.bataillenavalemobile.modele;

import java.util.List;
import java.util.Vector;

/**
 * Created by simon on 08/01/18.
 */

public abstract class Joueur {
    protected PlateauModele plateauModele;

    public Joueur (int x, int y){
        plateauModele = new PlateauModele(x, y);
    }

    public int toucher(int x, int y){
        return plateauModele.toucher(x, y);
    }

    abstract public Vector<Integer> tirer();

    abstract public void reponse(int x, int y, boolean toucher);

    public int getSizeX (){
        return plateauModele.getSizeX();
    }

    public int getSizeY (){
        return plateauModele.getSizeY();
    }

    public List<Bateau> getListeBateaux () {
        return plateauModele.getListeBateaux();
    }
}
