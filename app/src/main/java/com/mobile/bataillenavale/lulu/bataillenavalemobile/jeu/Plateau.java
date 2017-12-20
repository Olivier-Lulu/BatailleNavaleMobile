package com.mobile.bataillenavale.lulu.bataillenavalemobile.jeu;

import android.content.ClipData;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.DragEvent;
import android.view.Gravity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;

import com.mobile.bataillenavale.lulu.bataillenavalemobile.R;

/**
 * Created by Simon on 18/12/2017.
 */

public class Plateau {
    private View[][] cells;
    private Controleur controleur;

    public Plateau(int x, int y, MainActivity activity,Controleur controleur){
        if(x<0 || y<0)
            throw new IllegalArgumentException("taille negative");
        if(x>99)
            throw new IllegalArgumentException("longeur > 99");
        this.controleur = controleur;
        TableLayout table = (TableLayout) activity.findViewById(R.id.table);
        cells = new View[x][y];
        for(int yi=0;yi<y;yi++) {
            TableRow row = new TableRow(activity.getApplicationContext());
            row.setId(yi);
            row.setGravity(Gravity.CENTER);
            for (int xi = 0; xi < x; xi++) {
                RelativeLayout cell = new RelativeLayout(activity.getApplicationContext());
                TableRow.LayoutParams params = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT);
                row.addView(cell,xi,params);
                cell.setOnDragListener(new dragBoat());
                cell.setGravity(Gravity.CENTER);
                cell.setBackgroundColor(Color.TRANSPARENT);
                //implique que xi < 100
                cell.setId(100*yi+xi);
                cell.setTag(R.id.X,xi);
                cell.setTag(R.id.Y,yi);
                cells[xi][yi] = cell;
            }
            table.addView(row,new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.MATCH_PARENT,1f));
        }
        table.invalidate();
    }

    public class dragBoat implements View.OnDragListener {

        public boolean onDrag(View v, DragEvent event) {

            final int action = event.getAction();

            switch(action) {

                case DragEvent.ACTION_DRAG_STARTED:

                    //on ne drag qu'un type de truc donc on a juste a tester si la cas est deja pris
                    if (controleur.canHoastBoat((int)v.getTag(R.id.X),(int)v.getTag(R.id.Y))) {
                        //la case peut accepter un bateau, ajoute un liserait vert
                        //v.setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
                        v.setBackgroundColor(Color.GREEN);
                        v.invalidate();
                        return true;
                    }else{
                        //la case n'accepte pas , ajout d'un liserait rouge
                        v.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
                        v.invalidate();
                        return false;
                    }

                case DragEvent.ACTION_DRAG_ENTERED:
                    // ajout changement en vert plus foncer pour marquer que l'on est bien dans la case
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
                    ClipData.Item item = event.getClipData().getItemAt(0);
                    CharSequence dragData = item.getText();
                    controleur.obtaineBoat(dragData,(int)v.getTag(R.id.X),(int)v.getTag(R.id.X));
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
