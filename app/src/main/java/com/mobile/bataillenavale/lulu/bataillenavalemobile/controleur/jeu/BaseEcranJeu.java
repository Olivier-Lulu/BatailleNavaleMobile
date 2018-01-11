package com.mobile.bataillenavale.lulu.bataillenavalemobile.controleur.jeu;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import com.mobile.bataillenavale.lulu.bataillenavalemobile.R;
import com.mobile.bataillenavale.lulu.bataillenavalemobile.modele.FactoryModele;
import com.mobile.bataillenavale.lulu.bataillenavalemobile.modele.Modele;
import com.mobile.bataillenavale.lulu.bataillenavalemobile.vue.jeu.PlateauJeu;

import org.jetbrains.annotations.Nullable;

/**
 * Created by lulu on 08/01/18.
 */

public abstract class BaseEcranJeu extends Activity {

    protected PlateauJeu plateau;
    protected Modele controleurModele;
    protected boolean fini = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        controleurModele = FactoryModele.getInstance();
    }

    @Override
    public void finish() {
        super.finish();
    }

    protected void overrideTransitionEnter(){
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
    }

    protected void overrideTransitionExit(){
        overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
    }

    public abstract void swipe();

    public void cibleVide(int x, int y) {
        plateau.tintCellWhite(x, y);
    }

    public void cibleTouche(int x, int y) {
        plateau.tintCellBoom(x,y);
    }

    public void toast(Toast toast) {
        fini = true;
        toast.show();
    }

    public void cibleCouler(int x, int y, int xFin, int yFin) {
        if(x == xFin)
            for(int yi = y;yi<yFin;yi++)
                plateau.tintCellKaboom(x,yi);
        else
            for(int xi = x;xi<xFin;xi++)
                plateau.tintCellKaboom(xi,y);
    }
}
