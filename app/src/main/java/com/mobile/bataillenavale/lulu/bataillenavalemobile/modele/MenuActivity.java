package com.mobile.bataillenavale.lulu.bataillenavalemobile.modele;

import android.app.Activity;
import android.app.Fragment;
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
        startActivity(new Intent(this, MenuActivity.class));
    }

    public void clickParametre (View v) {
    }

    public void clickQuitter (View v) {
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
