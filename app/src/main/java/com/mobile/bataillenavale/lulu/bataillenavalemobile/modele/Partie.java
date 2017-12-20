package com.mobile.bataillenavale.lulu.bataillenavalemobile.modele;

import java.util.Vector;

/**
 * Created by lulu on 11/12/17.
 */

public class Partie {

    private PlateauModele plateauModeleJoueur;
    private PlateauModele plateauModeleAdverse;

    public Partie (int tailleX, int tailleY) {
        plateauModeleJoueur = new PlateauModele(tailleX, tailleY);
        plateauModeleAdverse = new PlateauModele(tailleX, tailleY);
    }

    /*
     * Retourn -1 pour cibler une case qui a deja ete ciblee, 0 pour non touche,
     * 1 pour touche et 2 pour touche coule
     */
    public int tirAdverseTouche(Vector<Integer>cible) {
        return plateauModeleJoueur.touche(cible);
    }

}
