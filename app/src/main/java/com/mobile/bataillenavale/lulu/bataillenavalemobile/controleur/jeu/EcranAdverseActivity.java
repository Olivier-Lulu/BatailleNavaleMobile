package com.mobile.bataillenavale.lulu.bataillenavalemobile.controleur.jeu;

import android.content.Intent;
import android.os.Bundle;

import com.mobile.bataillenavale.lulu.bataillenavalemobile.R;
import com.mobile.bataillenavale.lulu.bataillenavalemobile.modele.Modele;
import com.mobile.bataillenavale.lulu.bataillenavalemobile.vue.jeu.PlateauJeu;

import org.jetbrains.annotations.Nullable;

import java.util.Vector;

/**
 * Created by lulu on 08/01/18.
 */

public class EcranAdverseActivity extends BaseEcranJeu {

    private Modele controleurModele;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ecranadverse);
        controleurModele = Modele.getInstance();
        plateau = new PlateauJeu(PlateauJeu.ADVERSE, this, controleurModele.getSizeX(), controleurModele.getSizeY(), this, R.id.tableadverse);
        controleurModele.setTableauJeu(this);
    }

    @Override
    public void swipe(){
        System.out.println("swipeleft");

        Intent resumeJoueurActivity = new Intent(EcranAdverseActivity.this, EcranJoueurActivity.class);
        resumeJoueurActivity.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivityIfNeeded(resumeJoueurActivity, 0);

        EcranAdverseActivity.super.onPause();
    }

    public void activer (){
        plateau.activerGrille();
    }

    public void desactiver (){
        plateau.desactiverGrille();
    }

    public void tour (Vector<Integer>cible){
        controleurModele.tour(cible.elementAt(0), cible.elementAt(1));
    }

}