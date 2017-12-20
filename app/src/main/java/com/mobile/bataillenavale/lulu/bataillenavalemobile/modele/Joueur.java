package com.mobile.bataillenavale.lulu.bataillenavalemobile.modele;

import com.mobile.bataillenavale.lulu.bataillenavalemobile.controleur.JeuActivity;

import java.util.Vector;

/**
 * Created by lulu on 11/12/17.
 */

public class Joueur {

    private int id;
    private JeuActivity controleurJeuActivity;
    private PlateauModele plateauModeleJoueur;
    private PlateauModele plateauModeleAdverse;

    public Joueur(int tailleX, int tailleY, JeuActivity controleur) {
        plateauModeleJoueur = new PlateauModele(tailleX, tailleY);
        plateauModeleAdverse = new PlateauModele(tailleX, tailleY);
        controleurJeuActivity = controleur;
    }

    public int getId() {
        return id;
    }

    /*
     * Retourn -1 pour cibler une case qui a deja ete ciblee, 0 pour non touche,
     * 1 pour touche et 2 pour touche coule
     */
    public int recevoirTir(Vector<Integer>cible) {
        return plateauModeleJoueur.touche(cible);
    }

    public int tirer(Vector<Integer>cible) {
        return controleurJeuActivity.tir(this, cible);
    }

}
