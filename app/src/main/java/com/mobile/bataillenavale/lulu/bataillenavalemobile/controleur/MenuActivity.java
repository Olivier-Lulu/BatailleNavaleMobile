package com.mobile.bataillenavale.lulu.bataillenavalemobile.controleur;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.mobile.bataillenavale.lulu.bataillenavalemobile.R;
import com.mobile.bataillenavale.lulu.bataillenavalemobile.controleur.communication.MultijoueurMenuActivity;
import com.mobile.bataillenavale.lulu.bataillenavalemobile.controleur.jeu.EcranJoueurActivity;
import com.mobile.bataillenavale.lulu.bataillenavalemobile.controleur.placement.InitPartieActivity;
import com.mobile.bataillenavale.lulu.bataillenavalemobile.modele.Modele;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

public class MenuActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    @Override
    protected void onResume() {
        String[] fileList = fileList();
        for(String nom:fileList) {
            if (nom.equals("save")){
                Button continuer = new Button(this);
                continuer.setText("CONTINUER");
                continuer.setOnClickListener(V -> clickContinuer());
                break;
            }
        }
        super.onResume();
    }

    private void clickContinuer() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("save"))){
            Modele m = (Modele) ois.readObject();
            Modele.set(m);
            deleteFile("save");
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
        startActivity(new Intent(this, EcranJoueurActivity.class));
        this.finish();
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

    public void clickMultijoueurMenu(View view) {
        startActivity(new Intent(this, MultijoueurMenuActivity.class));
    }
}
