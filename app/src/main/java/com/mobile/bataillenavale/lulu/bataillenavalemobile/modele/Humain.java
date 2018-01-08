package com.mobile.bataillenavale.lulu.bataillenavalemobile.modele;

import com.mobile.bataillenavale.lulu.bataillenavalemobile.controleur.partie.JeuActivity;
import com.mobile.bataillenavale.lulu.bataillenavalemobile.controleur.placement.Controleur;
import com.mobile.bataillenavale.lulu.bataillenavalemobile.controleur.placement.InitPartieActivity;
import com.mobile.bataillenavale.lulu.bataillenavalemobile.vue.placement.PlateauVue;

import java.util.Vector;

/**
 * Created by lulu on 11/12/17.
 */

public class Humain {

    private int id;
    private PlateauVue plateauModeleJoueur;
    private PlateauVue plateauModeleAdverse;

    public Humain(PlateauVue plateauAdverse, PlateauVue plateauJoueur) {
        plateauModeleJoueur = plateauJoueur;
        plateauModeleAdverse = plateauAdverse;
    }

}