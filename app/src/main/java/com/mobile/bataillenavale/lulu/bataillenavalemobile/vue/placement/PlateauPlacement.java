package com.mobile.bataillenavale.lulu.bataillenavalemobile.vue.placement;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.DragEvent;
import android.view.Gravity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;

import com.mobile.bataillenavale.lulu.bataillenavalemobile.R;
import com.mobile.bataillenavale.lulu.bataillenavalemobile.controleur.placement.ControleurPlacement;

/**
 * Created by Simon on 18/12/2017.
 * Represente le plateau du jeu pendant le placement des bateaux par le joueur
 */


public class PlateauPlacement {
    private RelativeLayout[][] cells;
    private ControleurPlacement controleurPlacement;
    private boolean dejaExec = false;

    public PlateauPlacement(int x, int y, Activity activity, ControleurPlacement controleurPlacement){
        if(x<0 || y<0)
            throw new IllegalArgumentException("taille negative");
        this.controleurPlacement = controleurPlacement;
        TableLayout table = (TableLayout) activity.findViewById(R.id.table);
        cells = new RelativeLayout[x][y];

        //ajout des cellules au tableau
        for(int yi=0;yi<y;yi++) {
            TableRow row = new TableRow(activity.getApplicationContext());
            row.setId(yi);
            row.setGravity(Gravity.FILL);
            for (int xi = 0; xi < x; xi++) {
                RelativeLayout cell = new RelativeLayout(activity.getApplicationContext());
                TableRow.LayoutParams params = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT);
                params.setMargins(1,1,1,1);
                row.addView(cell,xi,params);
                cell.setOnDragListener(new dragBoat(xi,yi));
                cell.setGravity(Gravity.CENTER);
                cell.setBackgroundColor(Color.BLUE);
                cells[xi][yi] = cell;
            }
            table.addView(row,new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.MATCH_PARENT,1f));
        }
    }

    /*
        ajoute une vue a une celulle de cells
     */
    public void addView(int x, int y, View v) {
        //permet d'empecher le redimensionnement des celulles
        if(!dejaExec) {
            for (RelativeLayout[] rs : cells)
                for (RelativeLayout layout : rs) {
                    layout.setMinimumHeight(cells[x][y].getHeight());
                    layout.setMinimumWidth(cells[x][y].getWidth());
                }
            dejaExec = true;
        }

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(cells[x][y].getWidth(),cells[x][y].getHeight());
        cells[x][y].addView(v,params);
    }

    /*
        ajoute une vue sans bloquer le redimensionnement des celulles
     */
    public void addView2(int x, int y, View v) {
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,RelativeLayout.LayoutParams.MATCH_PARENT);
        cells[x][y].addView(v,params);
    }

    /*
        retourne vrai si un cellule n'a pas d'enfant
     */
    public boolean isEmpty(int x, int y){
        return cells[x][y].getChildCount() == 0;
    }

    /*
        suprime une vue d'une cellule
    */
    public void removeView(View v,int x,int y) {
        cells[x][y].removeView(v);
    }

    /*
        donne ou supprime une teinte sombre a une cellule
     */
    public void tint(int xCell, int yCell,boolean assombrire) {
        //recuperation des information sur la cellule
        ColorDrawable draw = (ColorDrawable) cells[xCell][yCell].getBackground();
        int color  = draw.getColor();
        int r = Color.red( color );
        int g = Color.green( color );
        int b = Color.blue( color );

        //calcule de la nouvelle teinte
        if(assombrire) {
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
    }

    /*
        class s'occupant de gerer la couleur des cellules en fonction du bateau
     */
    private class dragBoat implements View.OnDragListener {
        private int x;
        private int y;
        private boolean accept;

        public dragBoat(int x, int y){
            super();
            this.x = x;
            this.y = y;
        }

        public boolean onDrag(View v, DragEvent event) {

            int action = event.getAction();
            View dragData = (View) event.getLocalState();

            switch(action) {

                case DragEvent.ACTION_DRAG_STARTED:
                    //si la case peut accepter le bateau elle prend une couleur verte, sinon rouge
                    if (controleurPlacement.canHostBoat(dragData,x,y)) {
                        v.setBackgroundColor(Color.GREEN);
                        accept = true;
                    }else{
                        v.setBackgroundColor(Color.RED);
                        accept = false;
                    }
                    //on retourne toujours true car on veut etre prevenue de la fin du drag and drop
                    return true;

                case DragEvent.ACTION_DRAG_ENTERED:
                    //donne une teinte sombre a toute les cases qui recevrons le bateau
                    if (accept)
                        controleurPlacement.tint(dragData,x,y,true);
                    return true;

                case DragEvent.ACTION_DRAG_LOCATION:
                    return true;

                case DragEvent.ACTION_DRAG_EXITED:
                    //supprime la teinte sombre donne precedement
                    if (accept)
                        controleurPlacement.tint(dragData,x,y,false);
                    return true;

                case DragEvent.ACTION_DROP:
                    //lance la procedure pour poser un bateau sur le plateau
                    if (accept)
                        controleurPlacement.obtainBoat(dragData,x,y);
                    return true;

                case DragEvent.ACTION_DRAG_ENDED:
                    //redonne la couleur bleu au cellule
                    v.setBackgroundColor(Color.BLUE);
                    return true;

                default:
                    throw new IllegalArgumentException("DragEvent inconnue");
            }
        }
    }

}