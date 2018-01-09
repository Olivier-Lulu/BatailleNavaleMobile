package com.mobile.bataillenavale.lulu.bataillenavalemobile.controleur.placement;

import android.app.Activity;
import android.content.Intent;
import android.util.SparseArray;
import android.widget.Button;
import android.widget.LinearLayout;

import com.mobile.bataillenavale.lulu.bataillenavalemobile.R;
import com.mobile.bataillenavale.lulu.bataillenavalemobile.controleur.jeu.EcranAdverseActivity;
import com.mobile.bataillenavale.lulu.bataillenavalemobile.modele.Humain;
import com.mobile.bataillenavale.lulu.bataillenavalemobile.vue.BateauVue;


/**
 * Created by Simon on 01/01/2018.
 */

public class Pool {
    private SparseArray<BateauVue> bateaux;
    private LinearLayout pool;
    private Button finish = null;
    private InitPartieActivity initialiseur;

    public Pool(int nbTorpilleur, int nbContreTorpilleur, int nbCroiseur, int nbPorteAvion, Activity activity, ControleurPlacement controleurPlacement){
        int nbBateau = 0;
        bateaux = new SparseArray<>();
        pool = (LinearLayout) activity.findViewById(R.id.pool);

        for(int i=0;i<nbTorpilleur;++i) {
            LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
            param.setMargins(5,5,5,5);
            BateauVue bateau =  new BateauVue(BateauVue.TORPILLEUR, nbBateau, activity, controleurPlacement);
            bateaux.put(nbBateau,bateau);
            pool.addView(bateau.getComplet(),param);
            nbBateau++;
        }

        for(int i=0;i<nbContreTorpilleur;++i) {
            LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
            param.setMargins(5,5,5,5);
            BateauVue bateau =  new BateauVue(BateauVue.CONTRE_TORPILLEUR, nbBateau, activity, controleurPlacement);
            bateaux.put(nbBateau,bateau);
            pool.addView(bateau.getComplet(),param);
            nbBateau++;
        }

        for(int i=0;i<nbCroiseur;++i) {
            LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
            param.setMargins(5,5,5,5);
            BateauVue bateau =  new BateauVue(BateauVue.CROISEUR, nbBateau, activity, controleurPlacement);
            bateaux.put(nbBateau,bateau);
            pool.addView(bateau.getComplet(),param);
            nbBateau++;
        }

        for(int i=0;i<nbPorteAvion;++i) {
            LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
            param.setMargins(5,5,5,5);
            BateauVue bateau =  new BateauVue(BateauVue.PORTE_AVION, nbBateau, activity, controleurPlacement);
            bateaux.put(nbBateau,bateau);
            pool.addView(bateau.getComplet(),param);
            nbBateau++;
        }

        Button rotate = (Button) activity.findViewById(R.id.rotate);
        rotate.setOnClickListener(v -> rotate());

        initialiseur = (InitPartieActivity) activity;
    }

    public BateauVue getBoat(int key){
        return bateaux.get(key);
    }

    private void rotate(){
        for(int key = 0;key<bateaux.size();key++)
            if(!bateaux.get(key).isOnBoard())
               bateaux.get(key).rotate();
    }

    public void returnPool(int id) {
        if(finish != null)
            pool.removeView(finish);
        BateauVue bateau = bateaux.get(id);
        bateau.setCoord(-1,-1);
        pool.addView(bateau.getComplet());
    }

    public boolean isEmpty() {
        for(int key = 0;key<bateaux.size();key++)
            if(!bateaux.get(key).isOnBoard())
                return false;
        return true;
    }

    public void addFinishButton(Activity activity) {
        Button b = new Button(activity);
        b.setText("Pret !");
        b.setOnClickListener(v -> clickStart());
        finish = b;
        pool.addView(finish);
    }

    public void clickStart () {
        initialiseur.startActivity(new Intent(initialiseur,EcranAdverseActivity.class));
    }

}
