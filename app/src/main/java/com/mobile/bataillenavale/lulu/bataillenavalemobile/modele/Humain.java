package com.mobile.bataillenavale.lulu.bataillenavalemobile.modele;

import com.mobile.bataillenavale.lulu.bataillenavalemobile.vue.placement.PlateauVue;

import java.util.Vector;

/**
 * Created by lulu on 11/12/17.
 */

public class Humain extends Joueur{

    private int id;
    private PlateauVue plateauModeleJoueur;
    private PlateauVue plateauModeleAdverse;
    private PlateauModele plateauModele;


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

    public void poser (int x, int y, int direction, int type) {
        plateauModele.poser(x, y, direction, type);
    }
}