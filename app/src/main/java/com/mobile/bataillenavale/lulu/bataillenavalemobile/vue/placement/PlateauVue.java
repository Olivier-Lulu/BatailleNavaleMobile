package com.mobile.bataillenavale.lulu.bataillenavalemobile.vue.placement;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.DragEvent;
import android.view.Gravity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;

import com.mobile.bataillenavale.lulu.bataillenavalemobile.R;
import com.mobile.bataillenavale.lulu.bataillenavalemobile.controleur.placement.InitPartieActivity;
import com.mobile.bataillenavale.lulu.bataillenavalemobile.controleur.placement.Controleur;

/**
 * Created by Simon on 18/12/2017.
 */

public class PlateauVue {
    private RelativeLayout[][] cells;
    private Controleur controleur;
    private boolean dejaExec = false;

    public PlateauVue(int x, int y, InitPartieActivity activity, Controleur controleur){
        if(x<0 || y<0)
            throw new IllegalArgumentException("taille negative");
        this.controleur = controleur;
        TableLayout table = (TableLayout) activity.findViewById(R.id.table);
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
                cell.setOnDragListener(new dragBoat());
                cell.setGravity(Gravity.CENTER);
                cell.setBackgroundColor(Color.BLUE);
                cell.setId(View.generateViewId());
                cell.setTag(R.id.X,xi);
                cell.setTag(R.id.Y,yi);
                cells[xi][yi] = cell;
            }
            table.addView(row,new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.MATCH_PARENT,1f));
        }
    }

    public void addView(int x, int y, View v) {
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(cells[x][y].getWidth(),cells[x][y].getHeight());
        //du cheat bien degeu mais bon
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

    public void removeView(View v) {
        for(RelativeLayout[] rs: cells)
            for(RelativeLayout layout: rs)
                layout.removeView(v);
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

    public class dragBoat implements View.OnDragListener {

        public boolean onDrag(View v, DragEvent event) {

            int action = event.getAction();

            View dragData = (View) event.getLocalState();

            switch(action) {

                case DragEvent.ACTION_DRAG_STARTED:

                    //on ne drag qu'un type de truc donc on a juste a tester si la cas est deja pris
                    if (controleur.canHostBoat(dragData,(int)v.getTag(R.id.X),(int)v.getTag(R.id.Y))) {
                        //la case peut accepter un bateau, ajoute un liserait vert
                        v.setBackgroundColor(Color.GREEN);
                        v.invalidate();
                        return true;
                    }else{
                        //la case n'accepte pas , ajout d'un liserait rouge
                        v.setBackgroundColor(Color.RED);
                        v.invalidate();
                        return false;
                    }

                case DragEvent.ACTION_DRAG_ENTERED:
                    controleur.tint(dragData,(int)v.getTag(R.id.X),(int)v.getTag(R.id.Y),true);
                    return true;

                case DragEvent.ACTION_DRAG_LOCATION:
                    return true;

                case DragEvent.ACTION_DRAG_EXITED:
                    controleur.tint(dragData,(int)v.getTag(R.id.X),(int)v.getTag(R.id.Y),false);
                    return true;

                case DragEvent.ACTION_DROP:
                    controleur.obtaineBoat(dragData,(int)v.getTag(R.id.X),(int)v.getTag(R.id.Y));
                    return true;

                case DragEvent.ACTION_DRAG_ENDED:
                    v.setBackgroundColor(Color.BLUE);
                    v.invalidate();
                    return true;

                default:
                    throw new IllegalArgumentException("DragEvent inconnue");
            }
        }
    }
}