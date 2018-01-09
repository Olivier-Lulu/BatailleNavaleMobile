package com.mobile.bataillenavale.lulu.bataillenavalemobile.modele;

/**
 * Created by simon on 09/01/18.
 */

public class Modele {
    private static Modele instance = null;
    private Humain humain;
    private Joueur j2;

    /*
    initialise et retourne l'instance de modele
    Ne devrait etre appeler qu'une seul fois
     */
    public static synchronized Modele getInstanceInit(int tailleX, int tailleY, int nbTorpilleur, int nbContreTorpilleur, int nbCroiseur, int nbPorteAvion) {
        if(instance == null)
            instance = new Modele(tailleX, tailleY, nbTorpilleur, nbContreTorpilleur, nbCroiseur, nbPorteAvion);
        return instance;
    }

    /*
    *  retourne l'instance de modele
    */
    public static Modele getInstance() {
        return instance;
    }

    private Modele(int tailleX, int tailleY, int nbTorpilleur, int nbContreTorpilleur, int nbCroiseur, int nbPorteAvion) {
        humain = new Humain();
        j2 = new IA(tailleX, tailleY, nbTorpilleur, nbContreTorpilleur, nbCroiseur, nbPorteAvion);
    }

    public Humain getHumain(){
        return humain;
    }
}
