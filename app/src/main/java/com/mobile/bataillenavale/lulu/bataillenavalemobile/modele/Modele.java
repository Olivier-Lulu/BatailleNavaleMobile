package com.mobile.bataillenavale.lulu.bataillenavalemobile.modele;

import android.content.Intent;
import android.widget.Toast;

import com.mobile.bataillenavale.lulu.bataillenavalemobile.controleur.MenuActivity;
import com.mobile.bataillenavale.lulu.bataillenavalemobile.controleur.jeu.EcranAdverseActivity;
import com.mobile.bataillenavale.lulu.bataillenavalemobile.controleur.jeu.EcranJoueurActivity;

import java.util.List;
import java.util.Vector;

/**
 * Created by simon on 09/01/18.
 */

public class Modele {

    private static Modele instance = null;
    private Humain humain;
    private Joueur j2;
    private EcranAdverseActivity tableauDeJeu;
    private EcranJoueurActivity affichageJoueur;

    /*
    initialise et retourne l'instance de modele
    Ne devrait etre appeler qu'une seule fois
     */
    public static synchronized Modele getInstanceInit(int tailleX, int tailleY, int nbTorpilleur, int nbContreTorpilleur, int nbCroiseur, int nbPorteAvion) {
        if(instance == null)
            instance = new Modele(tailleX, tailleY, nbTorpilleur, nbContreTorpilleur, nbCroiseur, nbPorteAvion);
        return instance;
    }

    public void setTableauJeu(EcranAdverseActivity tableau) {
        if (tableauDeJeu == null)
            tableauDeJeu = tableau;
    }

    public void setAffichageJoueur(EcranJoueurActivity tableau) {
        if (affichageJoueur == null)
            affichageJoueur = tableau;
    }
    /*
    *  retourne l'instance de modele
    */
    public static Modele getInstance() {
        return instance;
    }

    private Modele(int tailleX, int tailleY, int nbTorpilleur, int nbContreTorpilleur, int nbCroiseur, int nbPorteAvion) {
        humain = new Humain(tailleX, tailleY);
        j2 = new IA(tailleX, tailleY, nbTorpilleur, nbContreTorpilleur, nbCroiseur, nbPorteAvion);
    }

    public Humain getHumain(){
        return humain;
    }

    public void poser (int x, int y, int direction, int type){
        humain.poser(x, y, direction, type);
    }

    public int getSizeX() {
        return humain.getSizeX();
    }

    public int getSizeY() {
        return humain.getSizeY();
    }

    public List<Bateau> getListeBateaux() {
        return humain.getListeBateaux();
    }

    public void tour(int x, int y){
        tableauDeJeu.desactiver();

        if (humain.tirEstValide(x, y)) {

            humain.invaliderCase(x, y);
            int toucher = j2.toucher(x, y);
            if ( toucher == 1)
                tableauDeJeu.cibleTouche(x, y);
            else if (toucher == 2)
                tableauDeJeu.cibleTouche(x, y);
            else
                tableauDeJeu.cibleVide(x, y);

            if (j2.perdu()){
                Toast toastVictoire = Toast.makeText(tableauDeJeu, "Victoire !", Toast.LENGTH_LONG);
                tableauDeJeu.toast(toastVictoire);
                tableauDeJeu.finish();
                affichageJoueur.finish();
                tableauDeJeu.startActivity(new Intent(tableauDeJeu,MenuActivity.class));
                detruireModele();
                return;
            }

            //verif victoire
            Vector<Integer> tirJ2 = j2.tirer();
            int xj2 = tirJ2.elementAt(0);
            int yj2 = tirJ2.elementAt(1);

            toucher = humain.toucher(xj2, yj2);
            if ( toucher == 1) {
                affichageJoueur.cibleTouche(xj2, yj2);
                j2.reponse(xj2,yj2,true);
            }else if (toucher == 2) {
                affichageJoueur.cibleTouche(xj2, yj2);
                j2.reponse(xj2,yj2,true);
            }else {
                affichageJoueur.cibleVide(xj2, yj2);
                j2.reponse(xj2,yj2,false);
            }

            if (humain.perdu()){
                Toast toastDefaite = Toast.makeText(tableauDeJeu, "Défaite...", Toast.LENGTH_LONG);
                tableauDeJeu.toast(toastDefaite);
                tableauDeJeu.finish();
                affichageJoueur.finish();
                tableauDeJeu.startActivity(new Intent(tableauDeJeu,MenuActivity.class));
                detruireModele();
                return;
            }

            //verif victoire
            tableauDeJeu.resetCible();
            tableauDeJeu.activer();
        }else{
            Toast toastCaseInvalide = Toast.makeText(tableauDeJeu, "Cible invalide", Toast.LENGTH_SHORT);
            tableauDeJeu.toast(toastCaseInvalide);
            tableauDeJeu.resetCible();
            tableauDeJeu.activer();
        }
    }

    public void detruireModele (){
        instance = null;
    }

    public void remove(int x, int y) {
        humain.remove(x,y);
    }
}
