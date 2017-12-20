package com.mobile.bataillenavale.lulu.bataillenavalemobile.controleur;

import android.app.Activity;
import android.os.Bundle;

import com.mobile.bataillenavale.lulu.bataillenavalemobile.modele.Joueur;

import java.util.Vector;

/**
 * Created by lulu on 20/12/17.
 */

public class JeuActivity extends Activity {

    private Joueur joueur1;
    private Joueur joueur2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public int tir(Joueur joueurCourant, Vector<Integer>cible){
        if (joueurCourant.getId() == joueur1.getId())
            return joueur2.recevoirTir(cible);
        else
            return joueur1.recevoirTir(cible);
    }

}
