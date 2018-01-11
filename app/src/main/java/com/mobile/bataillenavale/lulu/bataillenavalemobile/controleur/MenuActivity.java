package com.mobile.bataillenavale.lulu.bataillenavalemobile.controleur;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.mobile.bataillenavale.lulu.bataillenavalemobile.R;
import com.mobile.bataillenavale.lulu.bataillenavalemobile.controleur.communication.MultijoueurMenuActivity;
import com.mobile.bataillenavale.lulu.bataillenavalemobile.controleur.placement.InitPartieActivity;

public class MenuActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
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

    public void clickQuitter (View v){
        this.finish();
    }

    public void jouerContreOrdinateur (){
        LinearLayout conteneur = (LinearLayout) findViewById(R.id.layout_menu);
        conteneur.removeAllViews();

        Button boutonVsIaFacile = new Button(this);
        boutonVsIaFacile.setText("Facile");
        boutonVsIaFacile.setOnClickListener(lambda -> iaFacile());
        conteneur.addView(boutonVsIaFacile);

        Button boutonVsIaMoinsFacile = new Button(this);
        boutonVsIaMoinsFacile.setText("Moins facile");
        boutonVsIaMoinsFacile.setOnClickListener(lambda -> iaMoinsFacile());
        conteneur.addView(boutonVsIaMoinsFacile);
    }

    private void jouerContreHumain() {
        Intent lancement = new Intent(this, MultijoueurMenuActivity.class);
        lancement.putExtra("typeAdversaire", "Humain");
        startActivity(lancement);
        finish();
    }

    private void iaFacile() {
        Intent lancement = new Intent(this, InitPartieActivity.class);
        lancement.putExtra("typeAdversaire", "IA");
        lancement.putExtra("niveauIA", "IAFacile");
        startActivity(lancement);
        finish();
    }

    private void iaMoinsFacile() {
        Intent lancement = new Intent(this, InitPartieActivity.class);
        lancement.putExtra("typeAdversaire", "IA");
        lancement.putExtra("niveauIA", "IAMoinsFacile");
        startActivity(lancement);
        finish();
    }

}
