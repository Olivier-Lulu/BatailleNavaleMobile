package com.mobile.bataillenavale.lulu.bataillenavalemobile.controleur.jeu;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.mobile.bataillenavale.lulu.bataillenavalemobile.R;
import com.mobile.bataillenavale.lulu.bataillenavalemobile.vue.jeu.PlateauJeu;

import org.jetbrains.annotations.Nullable;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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
        startActivity(new Intent(this,EcranAdverseActivity.class));
    }

    @Override
    public void swipe(){
        System.out.println("swipeleft");

        Intent resumeJoueurActivity = new Intent(EcranJoueurActivity.this, EcranAdverseActivity.class);
        resumeJoueurActivity.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivityIfNeeded(resumeJoueurActivity, 0);

        EcranJoueurActivity.super.onPause();
    }

    @Override
    protected void onDestroy() {
        try (ObjectOutputStream oos = new ObjectOutputStream(openFileOutput("save", MODE_PRIVATE))){
            oos.writeObject(controleurModele);
        }catch(FileNotFoundException e){
            System.out.println(e.getMessage());
            Toast toastVictoire = Toast.makeText(this, "Erreur fnf: partie non sauvegarder", Toast.LENGTH_LONG);
            toastVictoire.show();
        }catch (IOException e){
            System.out.println(e.getMessage());
            Toast toastVictoire = Toast.makeText(this, "Erreur io: partie non sauvegarder", Toast.LENGTH_LONG);
            toastVictoire.show();
        }
        super.onDestroy();
    }
}