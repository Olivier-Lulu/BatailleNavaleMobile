package com.mobile.bataillenavale.lulu.bataillenavalemobile.modele;

/**
 * Created by lulu on 11/12/17.
 */

public class Partie {

    private Plateau plateauJoueur;
    private Plateau plateauAdverse;

    public Partie () {
        plateauJoueur = new Plateau();
        plateauAdverse = new Plateau();
    }

}
