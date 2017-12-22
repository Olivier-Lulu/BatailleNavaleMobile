package com.mobile.bataillenavale.lulu.bataillenavalemobile.controleur;

import android.app.Activity;
import android.content.ClipData;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.mobile.bataillenavale.lulu.bataillenavalemobile.R;
import com.mobile.bataillenavale.lulu.bataillenavalemobile.vue.PlateauVue;

public class InitPartieActivity extends Activity implements Controleur {
    private PlateauVue p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initpartie);
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
        p = new PlateauVue(5,5,this,this);
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
