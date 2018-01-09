package com.mobile.bataillenavale.lulu.bataillenavalemobile.modele;

import com.mobile.bataillenavale.lulu.bataillenavalemobile.vue.placement.PlateauPlacement;

import java.util.Vector;

/**
 * Created by lulu on 11/12/17.
 */

public class Humain extends Joueur{

    public Humain (int x, int y){
        super(x, y);
    }

    @Override
    public int toucher(int x, int y) {
        return 0;
    }

    @Override
    public Vector<Integer> tirer() {
        return null;
    }

    @Override
    public void reponse(int x, int y, boolean toucher) {

    }

    public void poser (int x, int y, int direction, int type) {
        plateauModele.poser(x, y, direction, type);
    }

    public void remove(int x, int y) {
        plateauModele.supprimerBateau(x,y);
    }
}