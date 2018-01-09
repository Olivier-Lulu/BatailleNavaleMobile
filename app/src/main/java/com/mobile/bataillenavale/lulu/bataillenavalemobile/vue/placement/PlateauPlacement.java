package com.mobile.bataillenavale.lulu.bataillenavalemobile.vue.placement;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.DragEvent;
import android.view.Gravity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;

import com.mobile.bataillenavale.lulu.bataillenavalemobile.R;
import com.mobile.bataillenavale.lulu.bataillenavalemobile.controleur.placement.ControleurPlacement;
import com.mobile.bataillenavale.lulu.bataillenavalemobile.modele.PlateauModele;

/**
 * Created by Simon on 18/12/2017.
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

    public void addView(int x, int y, View v) {
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(cells[x][y].getWidth(),cells[x][y].getHeight());
        //RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
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


    public void addView2(int x, int y, View v) {
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,RelativeLayout.LayoutParams.MATCH_PARENT);
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

    public class dragBoat implements View.OnDragListener {
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

                    //on ne drag qu'un type de truc donc on a juste a tester si la cas est deja pris
                    if (controleurPlacement.canHostBoat(dragData,x,y)) {
                        //la case peut accepter un bateau, ajoute un liserait vert
                        v.setBackgroundColor(Color.GREEN);
                        accept = true;
                    }else{
                        //la case n'accepte pas , ajout d'un liserait rouge
                        v.setBackgroundColor(Color.RED);
                        accept = false;
                    }
                    return true;

                case DragEvent.ACTION_DRAG_ENTERED:
                    if (accept)
                        controleurPlacement.tint(dragData,x,y,true);
                    return true;

                case DragEvent.ACTION_DRAG_LOCATION:
                    return true;

                case DragEvent.ACTION_DRAG_EXITED:
                    if (accept)
                        controleurPlacement.tint(dragData,x,y,false);
                    return true;

                case DragEvent.ACTION_DROP:
                    if (accept)
                        controleurPlacement.obtainBoat(dragData,x,y);
                    return true;

                case DragEvent.ACTION_DRAG_ENDED:
                    v.setBackgroundColor(Color.BLUE);
                    return true;

                default:
                    throw new IllegalArgumentException("DragEvent inconnue");
            }
        }
    }

    public int getXSize () {
        return cells.length;
    }

    public int getYSize () {
        return cells[0].length;
    }



}