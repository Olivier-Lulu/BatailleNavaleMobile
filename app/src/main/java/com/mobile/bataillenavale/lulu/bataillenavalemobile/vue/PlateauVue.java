package com.mobile.bataillenavale.lulu.bataillenavalemobile.vue;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.DragEvent;
import android.view.Gravity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.mobile.bataillenavale.lulu.bataillenavalemobile.R;
import com.mobile.bataillenavale.lulu.bataillenavalemobile.controleur.InitPartieActivity;
import com.mobile.bataillenavale.lulu.bataillenavalemobile.controleur.Controleur;

/**
 * Created by Simon on 18/12/2017.
 */

public class PlateauVue {
    private RelativeLayout[][] cells;
    private Controleur controleur;

    public PlateauVue(int x, int y, InitPartieActivity activity, Controleur controleur){
        if(x<0 || y<0)
            throw new IllegalArgumentException("taille negative");
        if(x>99)
            throw new IllegalArgumentException("longeur > 99");
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
                /*TextView tv = new TextView(activity.getApplicationContext());
                tv.setText(xi+","+yi);
                cell.addView(tv);*/
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
        // TODO fair en sorte qu'on le fasse qu'une fois
        //du cheat bien degeu mais bon
        for(RelativeLayout[] rs: cells)
            for(RelativeLayout layout: rs){
                layout.setMinimumHeight(cells[x][y].getHeight());
                layout.setMinimumWidth(cells[x][y].getWidth());
            }
        //RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        cells[x][y].addView(v,params);
    }

    public boolean isEmpty(int x, int y){
        return cells[x][y].getChildCount() == 0;
    }

    public class dragBoat implements View.OnDragListener {

        public boolean onDrag(View v, DragEvent event) {

            final int action = event.getAction();

            switch(action) {

                case DragEvent.ACTION_DRAG_STARTED:

                    //on ne drag qu'un type de truc donc on a juste a tester si la cas est deja pris
                    if (controleur.canHostBoat((int)v.getTag(R.id.X),(int)v.getTag(R.id.Y))) {
                        //la case peut accepter un bateau, ajoute un liserait vert
                        v.setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
                        v.invalidate();
                        return true;
                    }else{
                        //la case n'accepte pas , ajout d'un liserait rouge
                        v.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
                        v.invalidate();
                        return false;
                    }

                case DragEvent.ACTION_DRAG_ENTERED:
                    //TODO ajout changement en vert plus foncer pour marquer que l'on est bien dans la case
                    v.setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
                    v.invalidate();
                    return true;

                case DragEvent.ACTION_DRAG_LOCATION:
                    return true;

                case DragEvent.ACTION_DRAG_EXITED:
                    v.setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
                    v.invalidate();
                    return true;

                case DragEvent.ACTION_DROP:
                    View dragData = (View) event.getLocalState();
                    controleur.obtaineBoat(dragData,(int)v.getTag(R.id.X),(int)v.getTag(R.id.Y));
                    return true;

                case DragEvent.ACTION_DRAG_ENDED:
                    v.setBackgroundTintList(null);
                    v.invalidate();
                    return true;

                default:
                    throw new IllegalArgumentException("DragEvent inconnue");
            }
        }
    }
}