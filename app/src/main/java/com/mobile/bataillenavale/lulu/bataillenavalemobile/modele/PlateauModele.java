package com.mobile.bataillenavale.lulu.bataillenavalemobile.modele;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by lulu on 08/01/18.
 */

public class PlateauModele implements Serializable {
    private int sizeX;
    private int sizeY;
    private LinkedList<Bateau> bateaux;

    public PlateauModele (int x, int y){
        sizeX = x;
        sizeY = y;
        bateaux = new LinkedList<Bateau>();
    }

    /*
     * Teste si le tir en (x,y) touche et renvoie le type de la touche
     */
    public int toucher (int x, int y){
        for (Bateau curseur : bateaux)
            if (curseur.toucher(x, y))
                if (curseur.couler())
                    return 2;
                else
                    return 1;
        return 0;
    }

    /*
     * Enregistre un nouveau bateau dans sur le modele du plateau
     */
    public boolean poser (int x, int y, int direction, int type){
        Bateau nouveauBateau = new Bateau(type);
        nouveauBateau.poser(x, y, direction);
        for (Bateau curseur : bateaux)
            if(curseur.intersect(nouveauBateau))
                return false;
        bateaux.add(nouveauBateau);
        return true;
    }

    /*
     * Supprime le bateau qui etait sur la case (x,y)
     */
    public void supprimerBateau (int x, int y){
        Bateau aSupprimer = null;
        for (Bateau curseur : bateaux)
            if (curseur.estIci(x, y)){
                aSupprimer = curseur;
                break;
            }
        if(aSupprimer != null)
            bateaux.remove(aSupprimer);
    }

    public int getSizeX() {
        return sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }

    public List<Bateau> getListeBateaux() {
        return bateaux;
    }

    public void clear() {
        bateaux.clear();
    }

    public boolean perdu() {
        for (Bateau curseur : bateaux)
            if (curseur.getPv() > 0)
                return false;
        return true;
    }

    public Bateau getBateau(int x, int y) {
        for (Bateau curseur : bateaux)
            if (curseur.estIci(x,y))
                return curseur;
        return null;
    }

}
