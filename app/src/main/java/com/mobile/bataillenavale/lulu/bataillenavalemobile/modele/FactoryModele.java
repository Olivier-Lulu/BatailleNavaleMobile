package com.mobile.bataillenavale.lulu.bataillenavalemobile.modele;

/**
 * Created by simon on 11/01/18.
 */

public class FactoryModele
{
    private static Modele instance = null;

    /*
    initialise et retourne l'instance de modele
    Ne devrait etre appelee qu'une seule fois
    */
    public static synchronized Modele getInstanceInit(int tailleX, int tailleY, int nbTorpilleur, int nbContreTorpilleur, int nbCroiseur, int nbPorteAvion, String niveauIA) {
        if(instance == null)
            instance = new Modele(tailleX, tailleY, nbTorpilleur, nbContreTorpilleur, nbCroiseur, nbPorteAvion, niveauIA);
        return instance;
    }

    /*
    *  retourne l'instance de modele
    */
    public static Modele getInstance() {
        return instance;
    }


    public static void set(Modele m) {
        if(m != null)
            instance = m;
    }

    public static void detruireModele (){
        instance = null;
    }


    private FactoryModele() {
    }

}
