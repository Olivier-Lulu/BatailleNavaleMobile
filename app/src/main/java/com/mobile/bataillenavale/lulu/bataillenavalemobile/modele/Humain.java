package com.mobile.bataillenavale.lulu.bataillenavalemobile.modele;

import com.mobile.bataillenavale.lulu.bataillenavalemobile.vue.placement.PlateauPlacement;

import java.util.Vector;

/**
 * Created by lulu on 11/12/17.
 * Super classe des joueurs
 */

public class Humain extends Joueur{

    public Humain (int x, int y){
        super(x, y);
    }

    /*
     * La fonction tirer de l'humain n'est pas utilisee car c'est la detection du click
     * qui declenche le tir.
     */
    @Override
    public Vector<Integer> tirer() {
        return null;
    }

    /*
     * Ajoute un bateau au modele du joueur
     */
    public void poser (int x, int y, int direction, int type) {
        plateauModele.poser(x, y, direction, type);
    }

    public void remove(int x, int y) {
        plateauModele.supprimerBateau(x,y);
    }


}