package com.mobile.bataillenavale.lulu.bataillenavalemobile.modele;


import java.util.Vector;

/**
 * Created by simon on 08/01/18.
 */

public class IA implements Joueur {
    private Boolean[][] monPlauteau;
    private int[][] adverse;
    //0 non decouvert
    //1 toucher
    //2 couler
    private Vector<Integer> dernierCoup = null;

    public IA(int tailleX, int tailleY, int nbTorpilleur, int nbContreTorpilleur, int nbCroiseur, int nbPorteAvion){
        monPlauteau = new Boolean[tailleX][tailleY];
        adverse = new int[tailleX][tailleY];
        boolean ok = true;
        /*do{
            for(nbPorteAvion)
        }while(ok);
        */
    }

    public boolean toucher(int x, int y){
        if(monPlauteau[x][y]) {
            monPlauteau[x][y] = false;
            return true;
        }
        return false;
    }

    public Vector<Integer> tirer(){
        int x;
        int y;
        if(dernierCoup != null) {
            x = dernierCoup.get(0);
            y = dernierCoup.get(1);
            if (adverse[x][y] == 1) {
                double direction = Math.random();
                if (direction > 0.75) {
                    //haut
                    if (y > 0 && adverse[x][y - 1] == 0) {
                        dernierCoup = new Vector<>();
                        dernierCoup.add(0, x);
                        dernierCoup.add(1, y - 1);
                        return dernierCoup;
                    }
                } else if (direction > 0.5) {
                    //droite
                    if (x + 1 < adverse.length && adverse[x + 1][y] == 0) {
                        dernierCoup = new Vector<>();
                        dernierCoup.add(0, x + 1);
                        dernierCoup.add(1, y);
                        return dernierCoup;
                    }
                } else if (direction > 0.25) {
                    //bas
                    if (y + 1 < adverse[0].length && adverse[x][y + 1] == 0) {
                        dernierCoup = new Vector<>();
                        dernierCoup.add(0, x);
                        dernierCoup.add(1, y + 1);
                        return dernierCoup;
                    }
                } else {
                    //gauche
                    if (x > adverse.length && adverse[x - 1][y] == 0) {
                        dernierCoup = new Vector<>();
                        dernierCoup.add(0, x - 1);
                        dernierCoup.add(1, y);
                        return dernierCoup;
                    }
                }
            }
        }
        do {
            x = (int) (Math.random() * adverse.length);
            y = (int) (Math.random() * adverse[0].length);
        }while(adverse[x][y] != 0);
        dernierCoup = new Vector<>();
        dernierCoup.add(0,x);
        dernierCoup.add(1,y);
        return dernierCoup;
    }

    public void reponse(int x, int y, boolean toucher){
        if (toucher)
            adverse[x][y] = 1;
        else
            adverse[x][y] = 2;
    }
}
