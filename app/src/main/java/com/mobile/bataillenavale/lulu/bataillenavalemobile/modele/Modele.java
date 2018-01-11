package com.mobile.bataillenavale.lulu.bataillenavalemobile.modele;

import android.content.Intent;
import android.widget.Toast;

import com.mobile.bataillenavale.lulu.bataillenavalemobile.controleur.MenuActivity;
import com.mobile.bataillenavale.lulu.bataillenavalemobile.controleur.jeu.EcranAdverseActivity;
import com.mobile.bataillenavale.lulu.bataillenavalemobile.controleur.jeu.EcranJoueurActivity;

import java.io.Serializable;
import java.util.List;
import java.util.Vector;

/**
 * Created by simon on 09/01/18.
 */

public class Modele implements Serializable{

    private Humain humain;
    private Joueur j2;
    private transient EcranAdverseActivity tableauDeJeu;
    private transient EcranJoueurActivity affichageJoueur;

    public void setTableauJeu(EcranAdverseActivity tableau) {
        if (tableauDeJeu == null)
            tableauDeJeu = tableau;
    }

    public void setAffichageJoueur(EcranJoueurActivity tableau) {
        if (affichageJoueur == null)
            affichageJoueur = tableau;
    }

    protected Modele(int tailleX, int tailleY, int nbTorpilleur, int nbContreTorpilleur, int nbCroiseur, int nbPorteAvion, String niveauIA) {
        humain = new Humain(tailleX, tailleY);
        switch (niveauIA) {
            case "IAFacile" :
                j2 = new IAFacile(tailleX, tailleY, nbTorpilleur, nbContreTorpilleur, nbCroiseur, nbPorteAvion);
            case "IAMoinsFacile":
                j2 = new IAMoinsFacile(tailleX, tailleY, nbTorpilleur, nbContreTorpilleur, nbCroiseur, nbPorteAvion);
            default:
                j2 = new IAFacile(tailleX, tailleY, nbTorpilleur, nbContreTorpilleur, nbCroiseur, nbPorteAvion);
        }
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
            else if (toucher == 2) {
                Bateau couler = j2.getBateau(x,y);
                tableauDeJeu.cibleCouler(couler.getX(), couler.getY(),couler.getXFin(),couler.getYFin());
            }else
                tableauDeJeu.cibleVide(x, y);

            if (j2.perdu()){
                Toast toastVictoire = Toast.makeText(tableauDeJeu, "Victoire !", Toast.LENGTH_LONG);
                tableauDeJeu.toast(toastVictoire);
                tableauDeJeu.finish();
                affichageJoueur.partieFinie();
                affichageJoueur.finish();
                tableauDeJeu.startActivity(new Intent(tableauDeJeu,MenuActivity.class));
                FactoryModele.detruireModele();
                return;
            }

            Vector<Integer> tirJ2 = j2.tirer();
            int xj2 = tirJ2.elementAt(0);
            int yj2 = tirJ2.elementAt(1);

            toucher = humain.toucher(xj2, yj2);
            if (toucher == 1) {
                affichageJoueur.cibleTouche(xj2, yj2);
                j2.reponse(xj2,yj2,true);
            }else if (toucher == 2) {
                Bateau couler = humain.getBateau(xj2,yj2);
                affichageJoueur.cibleCouler(couler.getX(), couler.getY(),couler.getXFin(),couler.getYFin());
                j2.reponse(xj2,yj2,true);
            }else {
                affichageJoueur.cibleVide(xj2, yj2);
                j2.reponse(xj2,yj2,false);
            }

            if (humain.perdu()){
                Toast toastDefaite = Toast.makeText(tableauDeJeu, "Défaite...", Toast.LENGTH_LONG);
                tableauDeJeu.toast(toastDefaite);
                tableauDeJeu.finish();
                affichageJoueur.partieFinie();
                affichageJoueur.finish();
                tableauDeJeu.startActivity(new Intent(tableauDeJeu,MenuActivity.class));
                FactoryModele.detruireModele();
                return;
            }

            tableauDeJeu.activer();
        }else{
            Toast toastCaseInvalide = Toast.makeText(tableauDeJeu, "Cible invalide", Toast.LENGTH_SHORT);
            tableauDeJeu.toast(toastCaseInvalide);
            tableauDeJeu.activer();
        }
    }

    public void remove(int x, int y) {
        humain.remove(x,y);
    }

}
