package com.mobile.bataillenavale.lulu.bataillenavalemobile.modele;

import java.io.Serializable;

/**
 * Created by simon on 09/01/18.
 * Version du modele des bateaux
 */

public class Bateau implements Serializable{

    public static final int TORPILLEUR = 2;//2 case
    public static final int CONTRE_TORPILLEUR = 3;//3 case
    public static final int CROISEUR = 4;//4 case
    public static final int PORTE_AVION = 5;//5 case

    public static final int HORIZONTAL = 90;
    public static final int VERTICAL = 0;

    private int x = 0;
    private int y = 0;
    private int direction = 0;
    private int type;
    private int pv;

    public Bateau(int type){
        if(type != TORPILLEUR && type != CONTRE_TORPILLEUR && type != CROISEUR && type != PORTE_AVION)
            throw new IllegalArgumentException("type inconnue");
        this.type = type;
        this.pv = type;
    }

    public int getPv() {
        return pv;
    }

    public int getDirection() {
        return direction;
    }

    public int getType() {
        return type;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    /*
     * Parametre les coordonnees du bateau. Appelee lorsqu'un bateauVue est pose pendant
     * l'initialisation de la partie
     */
    public void poser(int x, int y, int direction){
        if(direction != VERTICAL && direction != HORIZONTAL)
            throw new IllegalArgumentException("direction inconnue");

        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    /*
     * Renvoie si le bateau est touche par le tir de coordonnees (x,y)
     * et lui enleve un pv, renvoie faux sinon
     */
    public boolean toucher(int x, int y){
        if(estIci(x,y)){
            pv--;
            return true;
        }else
            return false;
    }

    public boolean couler(){
        return pv == 0;
    }

    /*
     * Permet à la methode toucher(x, y) si le tir a touche le bateau
     */
    public boolean estIci(int x, int y) {
        if(direction == VERTICAL)
            if(this.x == x && this.y <= y && this.y+type > y) {
                return true;
            }else
                return false;
        else
        if(this.y == y && this.x >= x && this.x-type < x) {
            return true;
        }else
            return false;
    }

    /*
     * Renvoie vrai si le bateau this et le bateau en parametre s'intersecte
     */
    public boolean intersect(Bateau bateau) {
       if(this.direction == bateau.direction) {
           if (this.direction == HORIZONTAL){
                if(this.y != bateau.y)
                    return false;
                else
                    return !(this.x - this.type-1 > bateau.x || this.x <= bateau.x - bateau.type-1);

           }else
               if(this.x != bateau.x)
                   return false;
                else
                    return !(this.y + this.type-1 < bateau.y || this.y >= bateau.y + bateau.type-1);
       }else{
           if(this.direction == HORIZONTAL)
               return this.y >= bateau.y && this.y<=bateau.y+bateau.type-1
                       && bateau.x <=this.x && bateau.x >= this.x-this.type-1;
           else
               return bateau.y >= this.y && bateau.y<=this.y+this.type-1
                       && this.x <= bateau.x && this.x >= bateau.x-bateau.type-1;
       }
    }

    /*
     * Renvoie l'abscisse de la proue du bateau
     */
    public int getXFin() {
        if(direction == HORIZONTAL)
            return x-type;
        else
            return x;
    }

    /*
     * Renvoie l'ordonnee de la proue du bateau
     */
    public int getYFin() {
        if(direction == HORIZONTAL)
            return y;
        else
            return y+getType();
    }
}
