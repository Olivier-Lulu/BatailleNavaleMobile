package com.mobile.bataillenavale.lulu.bataillenavalemobile.controleur;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.LinearLayout;

import com.mobile.bataillenavale.lulu.bataillenavalemobile.R;
import com.mobile.bataillenavale.lulu.bataillenavalemobile.controleur.communication.MultijoueurMenuActivity;
import com.mobile.bataillenavale.lulu.bataillenavalemobile.controleur.jeu.EcranJoueurActivity;
import com.mobile.bataillenavale.lulu.bataillenavalemobile.controleur.placement.InitPartieActivity;
import com.mobile.bataillenavale.lulu.bataillenavalemobile.modele.FactoryModele;
import com.mobile.bataillenavale.lulu.bataillenavalemobile.modele.Modele;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

public class MenuActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    private void clickContinuer() {
        try (ObjectInputStream ois = new ObjectInputStream(openFileInput("save"))){
            Modele m = (Modele) ois.readObject();
            FactoryModele.set(m);
            deleteFile("save");
            startActivity(new Intent(this, EcranJoueurActivity.class));
            this.finish();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            Toast toastVictoire = Toast.makeText(this, "Erreur fnf: partie non charger", Toast.LENGTH_LONG);
            toastVictoire.show();
        }catch (IOException e){
            System.out.println(e.getMessage());
            Toast toastVictoire = Toast.makeText(this, "Erreur io: partie non charger", Toast.LENGTH_LONG);
            toastVictoire.show();
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
            Toast toastVictoire = Toast.makeText(this, "Erreur cnf: partie non charger", Toast.LENGTH_LONG);
            toastVictoire.show();
        }
    }

    public void clickJouer (View v) {
        LinearLayout conteneur = (LinearLayout) findViewById(R.id.layout_menu);
        conteneur.removeAllViews();

        Button boutonVsIa = new Button(this);
        boutonVsIa.setText("Jouer contre l'ordinateur");
        boutonVsIa.setOnClickListener(lambda -> jouerContreOrdinateur());
        conteneur.addView(boutonVsIa);

        Button boutonVsJoueur = new Button(this);
        boutonVsJoueur.setText("Jouer contre un autre joueur");
        boutonVsJoueur.setOnClickListener(lambda -> jouerContreHumain());
        conteneur.addView(boutonVsJoueur);
    }

    public void clickParametre (View v) {
        startActivity(new Intent(this, ParametresActivity.class));
    }


    public void jouerContreOrdinateur (){
        LinearLayout conteneur = (LinearLayout) findViewById(R.id.layout_menu);
        conteneur.removeAllViews();

        Button boutonVsIaFacile = new Button(this);
        boutonVsIaFacile.setText("Facile");
        boutonVsIaFacile.setOnClickListener(lambda -> ia("IAFacile"));
        conteneur.addView(boutonVsIaFacile);

        Button boutonVsIaMoinsFacile = new Button(this);
        boutonVsIaMoinsFacile.setText("Moins facile");
        boutonVsIaMoinsFacile.setOnClickListener(lambda -> ia("IAMoinsFacile"));
        conteneur.addView(boutonVsIaMoinsFacile);

        String[] fileList = fileList();
        for(String nom:fileList) {
            System.out.println(nom);
            if (nom.equals("save")){
                Button continuer = new Button(this);
                continuer.setText("CONTINUER");
                continuer.setOnClickListener(V -> clickContinuer());
                conteneur.addView(continuer);
                break;
            }
        }
    }

    public void clickQuitter (View v){
        this.finish();
    }

    private void jouerContreHumain() {
        Intent lancement = new Intent(this, MultijoueurMenuActivity.class);
        lancement.putExtra("typeAdversaire", "Humain");
        deleteFile("save");
        startActivity(lancement);
        finish();
    }

    private void ia(String niveau) {
        Intent lancement = new Intent(this, InitPartieActivity.class);
        lancement.putExtra("typeAdversaire", "IA");
        lancement.putExtra("niveauIA", niveau);
        deleteFile("save");
        startActivity(lancement);
        finish();
    }

}
