package com.mobile.bataillenavale.lulu.bataillenavalemobile.controleur.jeu;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.mobile.bataillenavale.lulu.bataillenavalemobile.R;
import com.mobile.bataillenavale.lulu.bataillenavalemobile.vue.SlideAnimation;
import com.mobile.bataillenavale.lulu.bataillenavalemobile.vue.jeu.PlateauJeu;

import org.jetbrains.annotations.Nullable;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * Created by lulu on 08/01/18.
 */

public class EcranJoueurActivity extends BaseEcranJeu {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ecranjoueur);
        plateau = new PlateauJeu(PlateauJeu.JOUEUR, this, controleurModele.getSizeX(), controleurModele.getSizeY(), this, R.id.tablejoueur,controleurModele.getListeBateaux());
        controleurModele.setAffichageJoueur(this);
        startActivity(new Intent(this, EcranAdverseActivity.class));
    }

    /*
     * Passage vers l'affichage du côté adverse
     */
    @Override
    public void swipe(){
        Intent resumeJoueurActivity = new Intent(EcranJoueurActivity.this, EcranAdverseActivity.class);
        resumeJoueurActivity.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivityIfNeeded(resumeJoueurActivity, 0);
        SlideAnimation.slideOutToRight(this, findViewById(R.id.rootjoueur));
        onPause();
    }

    /*
     * Appelee a la fin d'une partie pour effacer la sauvegarde de la partie courante
     */
    public void partieFinie (){
        deleteFile("save");
    }

    @Override
    protected void onPause() {
        try (ObjectOutputStream oos = new ObjectOutputStream(openFileOutput("save", MODE_PRIVATE))) {
            oos.writeObject(controleurModele);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            Toast toast = Toast.makeText(this, "Erreur fnf: partie non sauvegarder", Toast.LENGTH_LONG);
            toast.show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast toast = Toast.makeText(this, "Erreur io: partie non sauvegarder", Toast.LENGTH_LONG);
            toast.show();
        }

        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        SlideAnimation.slideInFromRight(this, findViewById(R.id.rootjoueur));
        int sizeX = plateau.getXSize();
        int sizeY = plateau.getYSize();

        for (int i=0; i<sizeX; i++){
            for (int j=0; j<sizeY; j++){
                switch (controleurModele.getJ2TirType(i,j)){
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