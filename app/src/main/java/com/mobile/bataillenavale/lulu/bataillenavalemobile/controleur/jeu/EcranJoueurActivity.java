package com.mobile.bataillenavale.lulu.bataillenavalemobile.controleur.jeu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.mobile.bataillenavale.lulu.bataillenavalemobile.R;
import com.mobile.bataillenavale.lulu.bataillenavalemobile.controleur.OnSwipeTouchListener;
import com.mobile.bataillenavale.lulu.bataillenavalemobile.modele.Modele;
import com.mobile.bataillenavale.lulu.bataillenavalemobile.vue.jeu.PlateauJeu;

import org.jetbrains.annotations.Nullable;

/**
 * Created by lulu on 08/01/18.
 */

public class EcranJoueurActivity extends BaseEcranJeu {

    private PlateauJeu plateau;
    private Modele controleurModele;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ecranjoueur);
        View root = findViewById(R.id.tablejoueur);

        root.setOnTouchListener(new OnSwipeTouchListener(this){
            @Override
            public void onSwipeRight() {
                super.onSwipeLeft();
                System.out.println("swiperight");

                Intent resumeAdverseActivity = new Intent(EcranJoueurActivity.this, EcranAdverseActivity.class);
                resumeAdverseActivity.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivityIfNeeded(resumeAdverseActivity, 0);

                EcranJoueurActivity.super.onPause();
            }
        });

        controleurModele = Modele.getInstance();
        plateau = new PlateauJeu(controleurModele.getSizeX(), controleurModele.getSizeY(), this, R.id.tablejoueur,controleurModele.getListeBateaux());
    }
}
