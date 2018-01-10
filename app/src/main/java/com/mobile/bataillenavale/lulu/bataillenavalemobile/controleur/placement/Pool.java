package com.mobile.bataillenavale.lulu.bataillenavalemobile.controleur.placement;

import android.app.Activity;
import android.content.Intent;
import android.util.SparseArray;
import android.widget.Button;
import android.widget.LinearLayout;

import com.mobile.bataillenavale.lulu.bataillenavalemobile.R;
import com.mobile.bataillenavale.lulu.bataillenavalemobile.controleur.jeu.EcranAdverseActivity;
import com.mobile.bataillenavale.lulu.bataillenavalemobile.modele.Bateau;
import com.mobile.bataillenavale.lulu.bataillenavalemobile.vue.BateauVue;

import java.util.List;


/**
 * Created by Simon on 01/01/2018.
 */

public class Pool {
    private SparseArray<BateauVue> bateaux;
    private LinearLayout pool;
    private Button finish = null;
    private InitPartieActivity initialiseur;

    public Pool(int nbTorpilleur, int nbContreTorpilleur, int nbCroiseur, int nbPorteAvion, Activity activity, ControleurPlacement controleurPlacement, List<Bateau> bateauxPlateau){
        int nbBateau = 0;
        bateaux = new SparseArray<>();
        pool = (LinearLayout) activity.findViewById(R.id.pool);
        initialiseur = (InitPartieActivity) activity;

        //on comence par poser les bateaudeja sur le plateau
        for(Bateau bateau: bateauxPlateau){
            LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
            param.setMargins(5,5,5,5);
            BateauVue b =  new BateauVue(bateau.getType(), nbBateau, activity, controleurPlacement);
            bateaux.put(nbBateau,b);
            nbBateau++;
            b.setDirection(bateau.getDirection());
            initialiseur.putBoat(b,bateau.getX(),bateau.getY());

            //on baisse le nombre de bateau du type que l'on vient de poser
            switch(bateau.getType()){
                case Bateau.TORPILLEUR:
                    nbTorpilleur--;
                    break;
                case Bateau.CONTRE_TORPILLEUR:
                    nbContreTorpilleur--;
                    break;
                case Bateau.CROISEUR:
                    nbCroiseur--;
                    break;
                case Bateau.PORTE_AVION:
                    nbPorteAvion--;
                    break;
            }
        }

        /*
            on creer les torpilleurs dans le pool
         */
        for(int i=0;i<nbTorpilleur;++i) {
            LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
            param.setMargins(5,5,5,5);
            BateauVue bateau =  new BateauVue(BateauVue.TORPILLEUR, nbBateau, activity, controleurPlacement);
            bateaux.put(nbBateau,bateau);
            pool.addView(bateau.getComplet(),param);
            nbBateau++;
        }

        /*
            on creer les contre-torpilleurs dans le pool
         */
        for(int i=0;i<nbContreTorpilleur;++i) {
            LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
            param.setMargins(5,5,5,5);
            BateauVue bateau =  new BateauVue(BateauVue.CONTRE_TORPILLEUR, nbBateau, activity, controleurPlacement);
            bateaux.put(nbBateau,bateau);
            pool.addView(bateau.getComplet(),param);
            nbBateau++;
        }

        /*
            on creer les croiseurs dans le pool
         */
        for(int i=0;i<nbCroiseur;++i) {
            LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
            param.setMargins(5,5,5,5);
            BateauVue bateau =  new BateauVue(BateauVue.CROISEUR, nbBateau, activity, controleurPlacement);
            bateaux.put(nbBateau,bateau);
            pool.addView(bateau.getComplet(),param);
            nbBateau++;
        }

        /*
            on creer les porte-avions dans le pool
         */
        for(int i=0;i<nbPorteAvion;++i) {
            LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
            param.setMargins(5,5,5,5);
            BateauVue bateau =  new BateauVue(BateauVue.PORTE_AVION, nbBateau, activity, controleurPlacement);
            bateaux.put(nbBateau,bateau);
            pool.addView(bateau.getComplet(),param);
            nbBateau++;
        }

        //ajout du bouton permetant de tourner les bateaux
        Button rotate = (Button) activity.findViewById(R.id.rotate);
        rotate.setOnClickListener(v -> rotate());
    }

    /*
        retourne le bateau ayant pour id key.
     */
    public BateauVue getBoat(int key){
        return bateaux.get(key);
    }

    /*
        tourne de 90° tout les pbateau encors dans le pool
     */
    private void rotate(){
        for(int key = 0;key<bateaux.size();key++)
            if(!bateaux.get(key).isOnBoard())
               bateaux.get(key).rotate();
    }

    /*
        repose un bateaux dans le pool
    */
    public void returnPool(int id) {
        //on enleve le bouton finish si il est resent
        if(finish != null)
            pool.removeView(finish);

        BateauVue bateau = bateaux.get(id);
        bateau.setCoord(-1,-1);
        pool.addView(bateau.getComplet());
    }

    /*
        retourne vrais si le pool est vide
     */
    public boolean isEmpty() {
        for(int key = 0;key<bateaux.size();key++)
            if(!bateaux.get(key).isOnBoard())
                return false;
        return true;
    }
    /*
        ajoute un bouton permetant de passer du placement des bateaux au jeu
     */
    public void addFinishButton(Activity activity) {
        Button b = new Button(activity);
        b.setText("Pret !");
        b.setOnClickListener(v -> clickStart());
        finish = b;
        pool.addView(finish);
    }

    /*
        lance le jeu et fini le placement
     */
    public void clickStart () {
        initialiseur.startActivity(new Intent(initialiseur,EcranAdverseActivity.class));
        initialiseur.finish();
    }

}
