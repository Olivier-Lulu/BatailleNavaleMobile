package com.mobile.bataillenavale.lulu.bataillenavalemobile.controleur.jeu;

import android.content.Intent;
import android.os.Bundle;

import com.mobile.bataillenavale.lulu.bataillenavalemobile.R;
import com.mobile.bataillenavale.lulu.bataillenavalemobile.vue.SlideAnimation;
import com.mobile.bataillenavale.lulu.bataillenavalemobile.vue.jeu.PlateauJeu;

import org.jetbrains.annotations.Nullable;

import java.util.Vector;

/**
 * Created by lulu on 08/01/18.
 */

public class EcranAdverseActivity extends BaseEcranJeu {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ecranadverse);
        plateau = new PlateauJeu(PlateauJeu.ADVERSE, this, controleurModele.getSizeX(), controleurModele.getSizeY(), this, R.id.tableadverse);
        controleurModele.setTableauJeu(this);
    }

    /*
     * Passage vers l'affichage du côté joueur
     */
    @Override
    public void swipe(){
        Intent resumeJoueurActivity = new Intent(EcranAdverseActivity.this, EcranJoueurActivity.class);
        resumeJoueurActivity.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivityIfNeeded(resumeJoueurActivity, 0);
        SlideAnimation.slideOutToLeft(this, findViewById(R.id.rootadverse));
        onPause();
    }

    public void activer (){
        plateau.activerGrille();
    }

    public void desactiver (){
        plateau.desactiverGrille();
    }

    /*
     * Methode invoquee lors de la detection d'un clique
     */
    public void tour (Vector<Integer>cible){
        controleurModele.tour(cible.elementAt(0), cible.elementAt(1));
    }

    @Override
    protected void onResume() {
        super.onResume();
        SlideAnimation.slideInFromLeft(this, findViewById(R.id.rootadverse));
        int sizeX = plateau.getXSize();
        int sizeY = plateau.getYSize();

        for (int i=0; i<sizeX; i++){
            for (int j=0; j<sizeY; j++){
                switch (controleurModele.getHumainTirType(i,j)){
                    case 3 :
                        plateau.tintCellKaboom(i,j);
                        break;
                    case 2 :
                        plateau.tintCellBoom(i,j);
                        break;
                    case 1:
                        plateau.tintCellWhite(i,j);
                        break;
                }
            }
        }
    }
}