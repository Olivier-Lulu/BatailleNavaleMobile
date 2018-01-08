package com.mobile.bataillenavale.lulu.bataillenavalemobile.modele;


import java.util.Vector;

/**
 * Created by simon on 08/01/18.
 */

public class IA implements Joueur {
    Boolean[][] monPlauteau;
    Boolean[][] adverse;
    Vector<Integer> dernierCoup;
    int derniereDirection;

    public boolean toucher(int x, int y){
        if(monPlauteau[x][y]) {
            monPlauteau[x][y] = false;
            return true;
        }
        return false;
    }

    public Vector<Integer> tirer(){
        int x = dernierCoup.get(0);
        int y = dernierCoup.get(1);
        if(adverse[x][y]){
            if(Math.random()<0.5){

            }else{

            }
        }
        return null;
    }

    public void reponse(int x, int y, boolean toucher){
        adverse[x][y] = toucher;
    }
}
