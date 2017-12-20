package com.mobile.bataillenavale.lulu.bataillenavalemobile.modele;

import java.util.LinkedList;
import java.util.Vector;

/**
 * Created by lulu on 11/12/17.
 */

public class PlateauModele {
    private boolean[][] surface;
    private LinkedList<Bateau> bateaux;

    public PlateauModele(int tailleX, int tailleY){
        surface = new boolean[tailleX][tailleY];
        bateaux = new LinkedList<Bateau>();
    }

    /*
     * Retourn -1 pour cibler une case qui a deja ete ciblee, 0 pour non touche,
     * 1 pour touche et 2 pour touche coule
     */
    public int touche(Vector<Integer> cible) {
        if (surface[cible.elementAt(0)][cible.elementAt(0)])
            return -1;
        for (Bateau bateauCourant : bateaux)
            if (bateauCourant.touche(cible))
                if (bateauCourant.toucheCoule())
                    return 2;
                else
                    return 1;
        return 0;
    }
}
