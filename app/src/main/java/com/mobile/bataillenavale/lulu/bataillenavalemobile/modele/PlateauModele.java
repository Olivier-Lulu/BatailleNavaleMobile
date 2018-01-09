package com.mobile.bataillenavale.lulu.bataillenavalemobile.modele;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by lulu on 08/01/18.
 */

public class PlateauModele {
    private int sizeX;
    private int sizeY;
    private List<Bateau> bateaux;

    public PlateauModele (){
        bateaux = new LinkedList<Bateau>();
    }

    public int  toucher (int x, int y){
        for (Bateau curseur : bateaux)
            if (curseur.toucher(x, y))
                if (curseur.couler())
                    return 2;
                else
                    return 1;
        return 0;
    }

    public void poser (int x, int y, int direction, int type){
        Bateau nouveauBateau = new Bateau(type);
        nouveauBateau.poser(x, y, direction);
        bateaux.add(nouveauBateau);
    }

    public void supprimerBateau (int x, int y){
        for (Bateau curseur : bateaux)
            if (curseur.estIci(x, y))
                bateaux.remove(curseur);
    }
}
