package com.mobile.bataillenavale.lulu.bataillenavalemobile.vue.jeu;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;

import com.mobile.bataillenavale.lulu.bataillenavalemobile.R;
import com.mobile.bataillenavale.lulu.bataillenavalemobile.controleur.jeu.BaseEcranJeu;
import com.mobile.bataillenavale.lulu.bataillenavalemobile.controleur.jeu.EcranAdverseActivity;
import com.mobile.bataillenavale.lulu.bataillenavalemobile.modele.Bateau;
import com.mobile.bataillenavale.lulu.bataillenavalemobile.vue.BateauVue;

import java.util.List;
import java.util.Vector;

import static com.mobile.bataillenavale.lulu.bataillenavalemobile.vue.BateauVue.VERTICAL;

/**
 * Created by Simon on 18/12/2017.
 * Represente le plateau du jeu pour les ecrans de controle du joueur
 */

public class PlateauJeu {

    public static final int JOUEUR = 1;
    public static final int ADVERSE = 2;

    private RelativeLayout[][] cells;

    /*
     * Constructeur de PlateauJeu specifique a l'ecran ou le joueur vise
     */
    public PlateauJeu(int typePlateau, BaseEcranJeu controleur, int x, int y, Activity activity, int id){
        if(x<0 || y<0)
            throw new IllegalArgumentException("taille negative");
        TableLayout table = (TableLayout) activity.findViewById(id);
        cells = new RelativeLayout[x][y];
        for(int yi=0;yi<y;yi++) {
            TableRow row = new TableRow(activity.getApplicationContext());
            row.setId(yi);
            row.setGravity(Gravity.FILL);
            for (int xi = 0; xi < x; xi++) {
                RelativeLayout cell = new RelativeLayout(activity.getApplicationContext());
                TableRow.LayoutParams params = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT);
                params.setMargins(1,1,1,1);
                row.addView(cell,xi,params);
                cell.setGravity(Gravity.CENTER);
                cell.setBackgroundColor(Color.BLUE);

                final int xFinal = xi;
                final int yFinal = yi;

                if (typePlateau == ADVERSE) {
                    cell.setOnTouchListener(new View.OnTouchListener() {
                        private int historicalX;

                        @Override
                        public boolean onTouch(View view, MotionEvent motionEvent) {
                            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                                historicalX = (int)motionEvent.getX();
                            }
                            if (motionEvent.getAction() == MotionEvent.ACTION_UP){
                                int deltaX = historicalX - (int)motionEvent.getX();
                                if (Math.abs(deltaX) >= 100) {
                                    controleur.swipe();
                                } else {
                                    Vector <Integer> cible = new Vector<Integer>();
                                    cible.add(0, xFinal);
                                    cible.add(1, yFinal);
                                    ((EcranAdverseActivity) controleur).tour(cible);
                                }
                            }
                            return true;
                        }
                    });
                } else {
                    cell.setOnTouchListener(new View.OnTouchListener() {
                        private int historicalX;

                        @Override
                        public boolean onTouch(View view, MotionEvent motionEvent) {
                            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                                historicalX = (int)motionEvent.getX();
                            }
                            if (motionEvent.getAction() == MotionEvent.ACTION_UP){
                                int deltaX = historicalX - (int)motionEvent.getX();
                                if (Math.abs(deltaX) >= 100) {
                                    controleur.swipe();
                                }
                            }
                            return true;
                        }
                    });
                }

                cells[xi][yi] = cell;
            }
            table.addView(row,new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.MATCH_PARENT,1f));
        }

    }

    /*
     * Constructeur de PlateauJeu specifique a l'ecran ou le joueur voit ses bateaux
     */
    public PlateauJeu (int typePlateau, BaseEcranJeu controleur, int x, int y, Activity activity, int id, List<Bateau> listeBateaux){
        this(typePlateau, controleur, x, y, activity, id);

        for (Bateau curseur : listeBateaux) {
            int type = curseur.getType();
            int direction = curseur.getDirection();
            int coordX = curseur.getX();
            int coordY = curseur.getY();
            BateauVue bateauCourant = new BateauVue(type,  activity);
            if (direction == VERTICAL){
                bateauCourant.setDirection(BateauVue.VERTICAL);
                for (int yBateau = 0; yBateau < type; yBateau++)
                    cells[coordX][coordY + yBateau].addView(bateauCourant.getParts(yBateau));
            }else {
                bateauCourant.setDirection(BateauVue.HORIZONTAL);
                for (int xBateau = 0; xBateau < type; xBateau++)
                    cells[coordX - xBateau][coordY].addView(bateauCourant.getParts(xBateau));
            }
        }
    }

    public int getXSize () {
        return cells.length;
    }

    public int getYSize () {
        return cells[0].length;
    }

    /*
     * Permet d'activer les detections d'interaction du joueur sur la grille
     */
    public void activerGrille(){
        for (RelativeLayout[] cellRow : cells)
            for (RelativeLayout cell : cellRow)
                cell.setEnabled(true);
    }

    /*
     * Permet d'activer les detections d'interaction du joueur sur la grille
     */
    public void desactiverGrille() {
        for (RelativeLayout[] cellRow : cells)
            for (RelativeLayout cell : cellRow)
                cell.setEnabled(false);
    }

    /*
     * Colore la case en blanc. Appelee si cette case est vide lorsqu'on lui tire dessus
     */
    public void tintCellWhite(int x, int y) {
        cells[x][y].setBackgroundColor(Color.WHITE);
    }

    /*
     * Colore la case en rouge. Appelee si cette case contient un bateau lorsqu'on lui
     * tire dessus
     */
    public void tintCellBoom(int x, int y) {
        cells[x][y].setBackgroundColor(Color.RED);
    }

    /*
     * Colore la case en brun. Appelee si le tir sur cette case coule un bateau
     */
    public void tintCellKaboom(int x, int y) {
        cells[x][y].setBackgroundColor(Color.rgb(100,0,0));
    }
}