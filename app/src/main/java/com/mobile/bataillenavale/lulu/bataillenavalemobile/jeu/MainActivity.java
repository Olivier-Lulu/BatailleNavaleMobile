package com.mobile.bataillenavale.lulu.bataillenavalemobile.jeu;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.mobile.bataillenavale.lulu.bataillenavalemobile.R;

public class MainActivity extends Activity implements Controleur {
    private Plateau p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.plateau2);
        final View star = findViewById(R.id.star);
        star.setTag("STAR");
        star.setOnLongClickListener(new View.OnLongClickListener() {

            // Defines the one method for the interface, which is called when the View is long-clicked
            public boolean onLongClick(View v) {
                ClipData dragData = ClipData.newPlainText("","");
                View.DragShadowBuilder myShadow = new View.DragShadowBuilder(star);
                v.startDrag(dragData,myShadow,v,0 );
                return true;
            }

        });
        p = new Plateau(5,5,this,this);
    }

    @Override
    public boolean canHostBoat(int x, int y) {
        return p.isEmpty(x,y);
    }

    @Override
    public void obtaineBoat(View boat, int xCell, int yCell) {
        RelativeLayout parent = (RelativeLayout) boat.getParent();
        parent.removeView(boat);
        p.addView(xCell,yCell,boat);
    }
}
