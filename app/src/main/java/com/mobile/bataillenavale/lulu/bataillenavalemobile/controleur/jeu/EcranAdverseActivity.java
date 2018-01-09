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

public class EcranAdverseActivity extends BaseEcranJeu {

    private PlateauJeu plateau;
    private Modele controleurModele;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ecranadverse);
        /*View root = findViewById(R.id.rootadverse);

        root.setOnTouchListener(new OnSwipeTouchListener(this){
            @Override
            public void onSwipeLeft() {
                super.onSwipeLeft();
                System.out.println("swipeleft");

                Intent resumeJoueurActivity = new Intent(EcranAdverseActivity.this, EcranJoueurActivity.class);
                resumeJoueurActivity.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivityIfNeeded(resumeJoueurActivity, 0);

                EcranAdverseActivity.super.onPause();
            }
        });*/

        controleurModele = Modele.getInstance();
        plateau = new PlateauJeu(PlateauJeu.ADVERSE, this, controleurModele.getSizeX(), controleurModele.getSizeY(), this, R.id.tableadverse);

    }

    @Override
    public void swipe(){
        System.out.println("swipeleft");

        Intent resumeJoueurActivity = new Intent(EcranAdverseActivity.this, EcranJoueurActivity.class);
        resumeJoueurActivity.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivityIfNeeded(resumeJoueurActivity, 0);

        EcranAdverseActivity.super.onPause();
    }

}
