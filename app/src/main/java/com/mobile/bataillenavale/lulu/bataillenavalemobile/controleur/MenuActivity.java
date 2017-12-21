package com.mobile.bataillenavale.lulu.bataillenavalemobile.controleur;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.mobile.bataillenavale.lulu.bataillenavalemobile.R;

public class MenuActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void clickJouer (View v) {
        startActivity(new Intent(this, InitPartieActivity.class));
        this.finish();
    }

    public void clickParametre (View v) {
        startActivity(new Intent(this, ParametresActivity.class));
    }

    public void clickQuitter (View v){
        this.finish();
    }

}
