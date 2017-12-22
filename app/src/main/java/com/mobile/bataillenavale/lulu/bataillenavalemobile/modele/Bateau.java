package com.mobile.bataillenavale.lulu.bataillenavalemobile.modele;

import android.view.View;

import java.util.Vector;

/**
 * Created by lulu on 11/12/17.
 */

public class Bateau {

    private int id;
    private int taille;
    private int PVrestants;
    private Vector<Integer>[] positions;
    private String nom;

    public Bateau (int taille, Vector<Integer>[] position, String nom){
        this.taille = taille;
        this.positions = position;
        this.nom = nom;
        PVrestants = taille;
    }

    public boolean touche(Vector<Integer> cible) {
        for (Vector<Integer> positionCourante : positions)
            if (positionCourante.equals(cible)) {
                PVrestants --;
                return true;
            }
        return false;
    }

    public boolean toucheCoule () {
        return (PVrestants == 0);
    }

}
