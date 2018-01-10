package com.mobile.bataillenavale.lulu.bataillenavalemobile.vue.jeu;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.DragEvent;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;

import com.mobile.bataillenavale.lulu.bataillenavalemobile.R;
import com.mobile.bataillenavale.lulu.bataillenavalemobile.controleur.GestureListener;
import com.mobile.bataillenavale.lulu.bataillenavalemobile.controleur.OnSwipeTouchListener;
import com.mobile.bataillenavale.lulu.bataillenavalemobile.controleur.jeu.BaseEcranJeu;
import com.mobile.bataillenavale.lulu.bataillenavalemobile.controleur.jeu.EcranAdverseActivity;
import com.mobile.bataillenavale.lulu.bataillenavalemobile.modele.Bateau;
import com.mobile.bataillenavale.lulu.bataillenavalemobile.vue.BateauVue;

import java.util.List;
import java.util.Vector;

import static com.mobile.bataillenavale.lulu.bataillenavalemobile.vue.BateauVue.VERTICAL;

/**
 * Created by Simon on 18/12/2017.
 */

public class PlateauJeu {

    public static final int JOUEUR = 1;
    public static final int ADVERSE = 2;

    private RelativeLayout[][] cells;
    private boolean dejaExec = false;

    private Vector<Integer> cible = null;

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
                cell.setTag(R.id.X,xi);
                cell.setTag(R.id.Y,yi);

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
                                    if (cible == null) {
                                        cible = new Vector<Integer>();
                                        cible.add(0, xFinal);
                                        cible.add(1, yFinal);
                                        ((EcranAdverseActivity) controleur).tour(cible);
                                    }
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

    public void resetCible () { cible = null; }

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

    public void addView(int x, int y, View v) {
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(cells[x][y].getWidth(),cells[x][y].getHeight());
        if(!dejaExec) {
            for (RelativeLayout[] rs : cells)
                for (RelativeLayout layout : rs) {
                    layout.setMinimumHeight(cells[x][y].getHeight());
                    layout.setMinimumWidth(cells[x][y].getWidth());
                }
            dejaExec = true;
        }
        cells[x][y].addView(v,params);
    }

    public boolean isEmpty(int x, int y){
        return cells[x][y].getChildCount() == 0;
    }

    public void removeView(View v,int x,int y) {
        cells[x][y].removeView(v);
    }

    public void tint(int xCell, int yCell,boolean enter) {
        ColorDrawable draw = (ColorDrawable) cells[xCell][yCell].getBackground();
        int color  = draw.getColor();
        int r = Color.red( color );
        int g = Color.green( color );
        int b = Color.blue( color );
        if(enter) {
            if (r != 0)
                r -= 100;
            if (g != 0)
                g -= 100;
            if (b != 0)
                b -= 100;
        }else {
            if (r != 0)
                r += 100;
            if (g != 0)
                g += 100;
            if (b != 0)
                b += 100;
        }
        cells[xCell][yCell].setBackgroundColor(Color.rgb(r,g,b));
        cells[xCell][yCell].invalidate();
    }

    /*
    remet tout les backgrounds a bleu
     */
    private void allBlue(){
        for(RelativeLayout[] rs: cells)
            for(RelativeLayout layout: rs)
                layout.setBackgroundColor(Color.BLUE);
    }

    public int getXSize () {
        return cells.length;
    }

    public int getYSize () {
        return cells[0].length;
    }

    public void activerGrille(){
        for (RelativeLayout[] cellRow : cells)
            for (RelativeLayout cell : cellRow)
                cell.setEnabled(true);
    }

    public void desactiverGrille() {
        for (RelativeLayout[] cellRow : cells)
            for (RelativeLayout cell : cellRow)
                cell.setEnabled(false);
    }

    public void tintCellWhite(int x, int y) {
        cells[x][y].setBackgroundColor(Color.WHITE);
    }

    public void tintCellBoom(int x, int y) {
        cells[x][y].setBackgroundColor(Color.RED);
    }
}