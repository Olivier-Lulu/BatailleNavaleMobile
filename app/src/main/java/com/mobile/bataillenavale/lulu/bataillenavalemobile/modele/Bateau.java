package com.mobile.bataillenavale.lulu.bataillenavalemobile.modele;

import java.io.Serializable;

/**
 * Created by simon on 09/01/18.
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

    public void poser(int x, int y, int direction){
        if(direction != VERTICAL && direction != HORIZONTAL)
            throw new IllegalArgumentException("direction inconnue");

        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    public boolean toucher(int x, int y){
        if(direction == VERTICAL)
            if(this.x == x && this.y <= y && this.y+type > y) {
                pv--;
                return true;
            }else
                return false;
        else
            if(this.y == y && this.x >= x && this.x-type < x) {
                pv--;
                return true;
            }else
                return false;
    }

    public boolean couler(){
        return pv == 0;
    }

    public boolean estIci(int x, int y) {
        return (this.x==x && this.y==y);
    }

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
}
