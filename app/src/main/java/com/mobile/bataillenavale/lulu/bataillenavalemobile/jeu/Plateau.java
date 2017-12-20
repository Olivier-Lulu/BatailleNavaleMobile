package com.mobile.bataillenavale.lulu.bataillenavalemobile.jeu;

import android.graphics.Color;
import android.view.Gravity;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;

import com.mobile.bataillenavale.lulu.bataillenavalemobile.R;

/**
 * Created by Simon on 18/12/2017.
 */

public class Plateau {
    private TableLayout table;
    static private int X = 1;
    static private int Y = 2;

    public Plateau(int x, int y, MainActivity activity){
        if(x<0 || y<0)
            throw new IllegalArgumentException("taille negative");
        if(x>99)
            throw new IllegalArgumentException("longeur > 99");
        table =new TableLayout(activity.getApplicationContext());
        //table.setBackgroundColor(Color.MAGENTA);
        for(int yi=0;yi<y;yi++) {
            TableRow row = new TableRow(activity.getApplicationContext());
            row.setId(yi);
            row.setGravity(Gravity.CENTER);
            row.setBackgroundColor(Color.BLACK);
            for (int xi = 0; xi < x; xi++) {
                RelativeLayout cell = new RelativeLayout(activity.getApplicationContext());
                TableRow.LayoutParams params = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT);
                row.addView(cell,xi,params);
                cell.setBackgroundColor(Color.BLUE);
                cell.setOnDragListener(activity.getDragEvent());
                cell.setGravity(Gravity.CENTER);
                //implique que xi < 100
                cell.setId(100*yi+xi);
                cell.setTag(R.id.X,xi);
                cell.setTag(R.id.Y,yi);
            }
            table.addView(row,new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.MATCH_PARENT,1f));
        }
        table.setStretchAllColumns(true);
    }

    public TableLayout getTable(){
        return table;
    }
}
