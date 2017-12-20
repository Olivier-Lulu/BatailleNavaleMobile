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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.plateau2);
        final View star = findViewById(R.id.star);
        star.setTag("STAR");
        star.setOnLongClickListener(new View.OnLongClickListener() {

            // Defines the one method for the interface, which is called when the View is long-clicked
            public boolean onLongClick(View v) {

                // Create a new ClipData.
                // This is done in two steps to provide clarity. The convenience method
                // ClipData.newPlainText() can create a plain text ClipData in one step.

                // Create a new ClipData.Item from the ImageView object's tag
                // ClipData.Item item = new ClipData.Item((String)v.getTag());

                // Create a new ClipData using the tag as a label, the plain text MIME type, and
                // the already-created item. This will create a new ClipDescription object within the
                // ClipData, and set its MIME type entry to "text/plain"
                ClipData dragData = ClipData.newPlainText(String.valueOf(v.getId()), String.valueOf(v.getId()));

                // Instantiates the drag shadow builder.
                View.DragShadowBuilder myShadow = new View.DragShadowBuilder(star);

                // Starts the drag

                v.startDrag(dragData,  // the data to be dragged
                        myShadow,  // the drag shadow builder
                        null,      // no need to use local data
                        0          // flags (not currently used, set to 0)
                );
                return true;
            }

        });
        Plateau p = new Plateau(5,5,this,this);
    }

    @Override
    public boolean canHoastBoat(int x, int y) {
        return false;
    }

    @Override
    public void obtaineBoat(CharSequence boatId, int xCell, int yCell) {

    }
}
