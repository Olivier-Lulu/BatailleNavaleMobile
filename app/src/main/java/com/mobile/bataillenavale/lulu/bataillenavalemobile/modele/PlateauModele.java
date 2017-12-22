package com.mobile.bataillenavale.lulu.bataillenavalemobile.modele;

import java.util.LinkedList;
import java.util.Vector;

/**
 * Created by lulu on 11/12/17.
 */

public class PlateauModele {

    private boolean[][] surfacePendantJeu;
    private boolean[][] surfaceInitPartie;
    private LinkedList<Bateau> bateaux;

    public PlateauModele(int tailleX, int tailleY){
        surfacePendantJeu = new boolean[tailleX][tailleY];
        surfaceInitPartie = new boolean[tailleX][tailleY];
        bateaux = new LinkedList<Bateau>();
        for (int x=0; x<tailleX; x++)
            for (int y=0; y<tailleY; y++){
                surfaceInitPartie[x][y] = true;
                surfacePendantJeu[x][y] = false;
            }
    }

    /*
     * Retourn -1 pour cibler une case qui a deja ete ciblee, 0 pour non touche,
     * 1 pour touche et 2 pour touche coule
     */
    public int touche(Vector<Integer> cible) {
        if (surfacePendantJeu[cible.elementAt(0)][cible.elementAt(0)])
            return -1;
        for (Bateau bateauCourant : bateaux)
            if (bateauCourant.touche(cible))
                if (bateauCourant.toucheCoule())
                    return 2;
                else
                    return 1;
        return 0;
    }

    public boolean caseEstLibre(int coordX, int coordY){
        return surfaceInitPartie[coordX][coordY];
    }
}
