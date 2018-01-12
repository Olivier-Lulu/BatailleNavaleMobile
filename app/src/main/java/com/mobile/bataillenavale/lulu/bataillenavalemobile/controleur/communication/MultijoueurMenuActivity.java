package com.mobile.bataillenavale.lulu.bataillenavalemobile.controleur.communication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.mobile.bataillenavale.lulu.bataillenavalemobile.R;
import com.mobile.bataillenavale.lulu.bataillenavalemobile.controleur.MenuActivity;

/**
 * Created by Cyril on 08/01/2018.
 */

public class MultijoueurMenuActivity extends MultijoueurActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menumulti);
    }

    public void clickMultijoueurClient(View view) {
        //startActivity(new Intent(this, MultijoueurClientActivity.class));
        Toast infoNonImplemente = Toast.makeText(this, "Pas encore implémenté", Toast.LENGTH_SHORT);
        infoNonImplemente.show();
    }
    public void clickMultijoueurServeur(View view) {
        //startActivity(new Intent(this, MultijoueurServeurActivity.class));
        Toast infoNonImplemente = Toast.makeText(this, "Pas encore implémenté", Toast.LENGTH_SHORT);
        infoNonImplemente.show();
    }

    public void clickRetour (View v){
        startActivity(new Intent(this, MenuActivity.class));
        finish();
    }

}
