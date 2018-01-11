package com.mobile.bataillenavale.lulu.bataillenavalemobile.modele;

import java.io.Serializable;
import java.util.List;
import java.util.Vector;

/**
 * Created by simon on 08/01/18.
 */

public abstract class Joueur implements Serializable {
    protected PlateauModele plateauModele;
    protected int[][] grilleTir;

    public Joueur (int x, int y){
        plateauModele = new PlateauModele(x, y);
        grilleTir = new int[x][y];
        for (int i = 0; i<x; i++)
            for (int j = 0; j<y; j++)
                grilleTir[i][j]=0;
    }

    public int toucher(int x, int y){
        return plateauModele.toucher(x, y);
    }

    abstract public Vector<Integer> tirer();

    abstract public void reponse(int x, int y, boolean toucher);

    public int getSizeX (){
        return plateauModele.getSizeX();
    }

    public int getSizeY (){
        return plateauModele.getSizeY();
    }

    public List<Bateau> getListeBateaux () {
        return plateauModele.getListeBateaux();
    }

    public boolean tirEstValide (int x, int y){
        return grilleTir[x][y]==0;
    }

    public void invaliderCase (int x, int y, int type){
        grilleTir[x][y]=type;
    }

    public boolean perdu () {
        return plateauModele.perdu();
    }

    public Bateau getBateau(int x, int y) {
        return plateauModele.getBateau(x,y);
    }

    public int getTirType(int i, int j) {
        return grilleTir[i][j];
    }
}
