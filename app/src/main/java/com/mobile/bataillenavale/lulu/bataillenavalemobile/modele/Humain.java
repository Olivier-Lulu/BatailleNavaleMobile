package com.mobile.bataillenavale.lulu.bataillenavalemobile.modele;

import com.mobile.bataillenavale.lulu.bataillenavalemobile.controleur.partie.JeuActivity;
import com.mobile.bataillenavale.lulu.bataillenavalemobile.controleur.placement.Controleur;
import com.mobile.bataillenavale.lulu.bataillenavalemobile.controleur.placement.InitPartieActivity;
import com.mobile.bataillenavale.lulu.bataillenavalemobile.vue.placement.PlateauVue;

import java.util.Vector;

/**
 * Created by lulu on 11/12/17.
 */

public class Humain implements Joueur{

    private int id;
    private PlateauVue plateauModeleJoueur;
    private PlateauVue plateauModeleAdverse;

    public Humain(){

    }

    public Humain(PlateauVue plateauAdverse, PlateauVue plateauJoueur) {
        plateauModeleJoueur = plateauJoueur;
        plateauModeleAdverse = plateauAdverse;
    }

    @Override
    public boolean toucher(int x, int y) {
        return false;
    }

    @Override
    public Vector<Integer> tirer() {
        return null;
    }

    @Override
    public void reponse(int x, int y, boolean toucher) {

    }
}