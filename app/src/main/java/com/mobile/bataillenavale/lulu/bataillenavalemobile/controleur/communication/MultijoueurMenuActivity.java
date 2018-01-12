package com.mobile.bataillenavale.lulu.bataillenavalemobile.controleur.communication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.mobile.bataillenavale.lulu.bataillenavalemobile.R;

/**
 * Created by Cyril on 08/01/2018.
 */

public class MultijoueurMenuActivity extends MultijoueurActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menumulti);
    }

    public void clickMultijoueurClient(View view) {
        startActivity(new Intent(this, MultijoueurClientActivity.class));
    }
    public void clickMultijoueurServeur(View view) {
        startActivity(new Intent(this, MultijoueurServeurActivity.class));
    }

    public void clickRetour (View v){
        this.finish();
    }

}
