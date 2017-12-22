package com.mobile.bataillenavale.lulu.bataillenavalemobile.controleur;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.mobile.bataillenavale.lulu.bataillenavalemobile.R;

/**
 * Created by lulu on 21/12/17.
 */

public class ParametresActivity extends Activity {

    private EditText largeurPlateau;
    private EditText hauteurPlateau;

    private EditText nbBateau2champ;
    private EditText nbBateau3champ;
    private EditText nbBateau4champ;
    private EditText nbBateau5champ;

    private SharedPreferences sharedPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parametres);

        largeurPlateau = (EditText) findViewById(R.id.largeurPlateauVal);
        hauteurPlateau = (EditText) findViewById(R.id.haueurPlateauVal);

        nbBateau2champ = (EditText) findViewById(R.id.nbBateau2);
        nbBateau3champ = (EditText) findViewById(R.id.nbBateau3);
        nbBateau4champ = (EditText) findViewById(R.id.nbBateau4);
        nbBateau5champ = (EditText) findViewById(R.id.nbBateau5);

        sharedPrefs = getSharedPreferences(getString(R.string.preference_file_initPartie), Context.MODE_PRIVATE);

        largeurPlateau.setText("" + sharedPrefs.getInt(getString(R.string.largeurPlateau), 5));
        hauteurPlateau.setText("" + sharedPrefs.getInt(getString(R.string.hauteurPlateau), 5));

        nbBateau2champ.setText("" + sharedPrefs.getInt(getString(R.string.nbBateau2), 1));
        nbBateau3champ.setText("" + sharedPrefs.getInt(getString(R.string.nbBateau3), 1));
        nbBateau4champ.setText("" + sharedPrefs.getInt(getString(R.string.nbBateau4), 1));
        nbBateau5champ.setText("" + sharedPrefs.getInt(getString(R.string.nbBateau5), 1));
    }

    public void clickValider (View v){
        SharedPreferences.Editor editor = sharedPrefs.edit();

        editor.putInt(getString(R.string.largeurPlateau), Integer.parseInt(largeurPlateau.getText().toString()));
        editor.putInt(getString(R.string.hauteurPlateau), Integer.parseInt(hauteurPlateau.getText().toString()));

        editor.putInt(getString(R.string.nbBateau2), Integer.parseInt(nbBateau2champ.getText().toString()));
        editor.putInt(getString(R.string.nbBateau3), Integer.parseInt(nbBateau3champ.getText().toString()));
        editor.putInt(getString(R.string.nbBateau4), Integer.parseInt(nbBateau4champ.getText().toString()));
        editor.putInt(getString(R.string.nbBateau5), Integer.parseInt(nbBateau5champ.getText().toString()));
        editor.commit();

        this.finish();
    }

    public void clickRetour (View v){
        this.finish();
    }

}
