package com.mobile.bataillenavale.lulu.bataillenavalemobile.vue.placement;

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
import com.mobile.bataillenavale.lulu.bataillenavalemobile.controleur.placement.InitPartieActivity;
import com.mobile.bataillenavale.lulu.bataillenavalemobile.controleur.placement.ControleurPlacement;

/**
 * Created by Simon on 18/12/2017.
 */

public class PlateauVue implements Parcelable {
    private RelativeLayout[][] cells;
    private ControleurPlacement controleurPlacement;
    private boolean dejaExec = false;

    public PlateauVue(int x, int y, InitPartieActivity activity, ControleurPlacement controleurPlacement){
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

    protected PlateauVue(Parcel in) {
        dejaExec = in.readByte() != 0;
    }

    public static final Creator<PlateauVue> CREATOR = new Creator<PlateauVue>() {
        @Override
        public PlateauVue createFromParcel(Parcel in) {
            return new PlateauVue(in);
        }

        @Override
        public PlateauVue[] newArray(int size) {
            return new PlateauVue[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

        parcel.writeByte((byte) (dejaExec ? 1 : 0));
    }

    public class dragBoat implements View.OnDragListener {

        public boolean onDrag(View v, DragEvent event) {

            int action = event.getAction();

            View dragData = (View) event.getLocalState();

            switch(action) {

                case DragEvent.ACTION_DRAG_STARTED:

                    //on ne drag qu'un type de truc donc on a juste a tester si la cas est deja pris
                    if (controleurPlacement.canHostBoat(dragData,(int)v.getTag(R.id.X),(int)v.getTag(R.id.Y))) {
                        //la case peut accepter un bateau, ajoute un liserait vert
                        v.setBackgroundColor(Color.GREEN);
                        return true;
                    }else{
                        //la case n'accepte pas , ajout d'un liserait rouge
                        v.setBackgroundColor(Color.RED);
                        return false;
                    }

                case DragEvent.ACTION_DRAG_ENTERED:
                    controleurPlacement.tint(dragData,(int)v.getTag(R.id.X),(int)v.getTag(R.id.Y),true);
                    return true;

                case DragEvent.ACTION_DRAG_LOCATION:
                    return true;

                case DragEvent.ACTION_DRAG_EXITED:
                    controleurPlacement.tint(dragData,(int)v.getTag(R.id.X),(int)v.getTag(R.id.Y),false);
                    return true;

                case DragEvent.ACTION_DROP:
                    controleurPlacement.obtainBoat(dragData,(int)v.getTag(R.id.X),(int)v.getTag(R.id.Y));
                    allBlue();
                    return true;

                case DragEvent.ACTION_DRAG_ENDED:
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