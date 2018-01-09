package com.mobile.bataillenavale.lulu.bataillenavalemobile.controleur.placement;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.mobile.bataillenavale.lulu.bataillenavalemobile.R;
import com.mobile.bataillenavale.lulu.bataillenavalemobile.modele.Humain;
import com.mobile.bataillenavale.lulu.bataillenavalemobile.vue.placement.BateauVue;
import com.mobile.bataillenavale.lulu.bataillenavalemobile.vue.placement.PlateauVue;

public class InitPartieActivity extends Activity implements Controleur {
    private PlateauVue p;
    private Pool pool;
    private int x;
    private int y;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initpartie);

        SharedPreferences option = getSharedPreferences(getString(R.string.preference_file_initPartie), Context.MODE_PRIVATE);

        x = option.getInt(getString(R.string.largeurPlateau), 0);
        y = option.getInt(getString(R.string.hauteurPlateau),0);

        int nbTorpilleur = option.getInt(getString(R.string.nbBateau2), 0);
        int nbContreTorpilleur = option.getInt(getString(R.string.nbBateau3), 0);
        int nbCroiseur = option.getInt(getString(R.string.nbBateau4), 0);
        int nbPorteAvion = option.getInt(getString(R.string.nbBateau5), 0);

        pool = new Pool(nbTorpilleur,nbContreTorpilleur,nbCroiseur,nbPorteAvion,this,this);
        p = new PlateauVue(x,y,this,this);
    }

    /*
    definie si la case x,y peut accepter la tete du bateau boat
     */
    @Override
    public boolean canHostBoat(View boat,int x, int y) {
        int id = (int) boat.getTag(R.id.BoatID);
        int size = pool.getBoat(id).getSize();
        int direction = pool.getBoat(id).getDirection();
        if(direction == BateauVue.HORIZONTAL) {
            if (x+1 - size < 0)
                return false;
            for(int i = x;i> x-size;--i)
                if(!p.isEmpty(i,y))
                    return false;
            return true;
        }else{
            if (y + size > this.y)
                return false;
            for(int i = y;i< y+size;++i)
                if(!p.isEmpty(x,i))
                    return false;
            return true;
        }
    }

    /*
    ajoute un bateau au plateau, en partant des coordonée xCell,yCell
     */
    @Override
    public void obtainBoat(View boat, int xCell, int yCell) {
        ViewGroup parent = (ViewGroup) boat.getParent();
        parent.removeView(boat);
        int id = (int) boat.getTag(R.id.BoatID);
        BateauVue b = pool.getBoat(id);
        int size = b.getSize();
        int direction = b.getDirection();
        b.setCoord(xCell,yCell);
        if(direction == BateauVue.HORIZONTAL)
            for(int x = xCell; x>xCell-size;x--)
                p.addView(x,yCell,b.getParts(xCell-x));
        else
            for(int y = yCell; y<yCell+size;y++)
                p.addView(xCell,y,b.getParts(y-yCell));

        if(pool.isEmpty()) {
            Humain humain = new Humain(new PlateauVue(p.getXSize(), p.getYSize(), this, this), p);
            pool.addFinishButton(this, humain);
        }
    }

    /*
    supprime un bateau du plateau
     */
    @Override
    public void removeBoat(int id) {
        BateauVue b = pool.getBoat(id);
        if(b.getDirection() == BateauVue.HORIZONTAL)
            for(int i = 0;i<b.getSize();i++)
                p.removeView(b.getParts(i),b.getX()-i,b.getY());
        else
            for(int i = 0;i<b.getSize();i++)
                p.removeView(b.getParts(i),b.getX(),b.getY()+i);
        pool.returnPool(id);
    }

    /*
    donne une tinte sombre aux au cases sous le bateau
     */
    @Override
    public void tint(View boat, int xCell, int yCell,boolean enter){
        int id = (int) boat.getTag(R.id.BoatID);
        BateauVue b = pool.getBoat(id);
        int size = b.getSize();
        int direction = b.getDirection();
        b.setCoord(xCell,yCell);
        if(direction == BateauVue.HORIZONTAL)
            for(int x = xCell; x>xCell-size;x--)
                p.tint(x,yCell,enter);
        else
            for(int y = yCell; y<yCell+size;y++)
                p.tint(xCell,y,enter);
    }

}
