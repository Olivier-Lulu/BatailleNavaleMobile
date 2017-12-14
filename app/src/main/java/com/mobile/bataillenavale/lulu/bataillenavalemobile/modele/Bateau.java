package com.mobile.bataillenavale.lulu.bataillenavalemobile.modele;

import java.util.Vector;

/**
 * Created by lulu on 11/12/17.
 */

public class Bateau {

    private int taille;
    private Vector<Integer>[] positions;
    private String nom;

    public Bateau (int taille, Vector<Integer>[] positions, String nom){
        this.taille = taille;
        this.positions = positions;
        this.nom = nom;
    }

}
