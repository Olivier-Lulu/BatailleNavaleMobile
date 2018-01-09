package com.mobile.bataillenavale.lulu.bataillenavalemobile.vue.placement;

import android.app.Activity;
import android.content.ClipData;
import android.graphics.Canvas;
import android.graphics.Point;
import android.view.View;
import android.widget.ImageView;

import com.mobile.bataillenavale.lulu.bataillenavalemobile.R;
import com.mobile.bataillenavale.lulu.bataillenavalemobile.controleur.placement.ControleurPlacement;

/**
 * Created by lulu on 20/12/17.
 */

public class BateauVue{

    public static final int TORPILLEUR = 2;//2 case
    public static final int CONTRE_TORPILLEUR = 3;//3 case
    public static final int CROISEUR = 4;//4 case
    public static final int PORTE_AVION = 5;//5 case

    public static final int HORIZONTAL = 90;
    public static final int VERTICAL = 0;
    
    private ImageView complet;
    private ImageView[] parts;
    private int id;
    private ControleurPlacement controleurPlacement;
    private int x = -1;
    private int y = -1;
    private int direction = VERTICAL;

    public BateauVue(int type,int id, Activity activity,ControleurPlacement controleurPlacement){
        this.controleurPlacement = controleurPlacement;
        this.id = id;
        switch(type){
            case TORPILLEUR:
                complet = new ImageView(activity.getApplicationContext());
                complet.setImageResource(R.drawable.torpilleur);
                complet.setTag(R.id.BoatID,id);//type
                complet.setOnLongClickListener(
                        v -> {
                            ClipData dragData = ClipData.newPlainText("","");
                            View.DragShadowBuilder myShadow = new testShadow(v);
                            v.startDrag(dragData,myShadow,v,0 );
                            return true;
                        });

                parts = new ImageView[2];

                parts[0] = new ImageView(activity.getApplicationContext());
                parts[0].setImageResource(R.drawable.torpilleurp1);
                parts[0].setOnLongClickListener(
                        v -> {
                            remove();
                            return true;
                        });

                parts[1] = new ImageView(activity.getApplicationContext());
                parts[1].setImageResource(R.drawable.torpilleurp2);
                parts[1].setOnLongClickListener(
                        v -> {
                            remove();
                            return true;
                        });
                break;
            case CONTRE_TORPILLEUR:
                complet = new ImageView(activity.getApplicationContext());
                complet.setImageResource(R.drawable.contre);
                complet.setTag(R.id.BoatID,id);//type
                complet.setOnLongClickListener(
                        v -> {
                            ClipData dragData = ClipData.newPlainText("","");
                            View.DragShadowBuilder myShadow = new testShadow(v);
                            v.startDrag(dragData,myShadow,v,0 );
                            return true;
                        });

                parts = new ImageView[3];

                parts[0] = new ImageView(activity.getApplicationContext());
                parts[0].setImageResource(R.drawable.contre1);
                parts[0].setOnLongClickListener(
                        v -> {
                            remove();
                            return true;
                        });

                parts[1] = new ImageView(activity.getApplicationContext());
                parts[1].setImageResource(R.drawable.contre2);
                parts[1].setOnLongClickListener(
                        v -> {
                            remove();
                            return true;
                        });

                parts[2] = new ImageView(activity.getApplicationContext());
                parts[2].setImageResource(R.drawable.contre3);
                parts[2].setOnLongClickListener(
                        v -> {
                            remove();
                            return true;
                        });
                break;
            case CROISEUR:
                complet = new ImageView(activity.getApplicationContext());
                complet.setImageResource(R.drawable.croiseur);
                complet.setTag(R.id.BoatID,id);//type
                complet.setOnLongClickListener(
                        v -> {
                            ClipData dragData = ClipData.newPlainText("","");
                            View.DragShadowBuilder myShadow = new testShadow(v);
                            v.startDrag(dragData,myShadow,v,0 );
                            return true;
                        });

                parts = new ImageView[4];

                parts[0] = new ImageView(activity.getApplicationContext());
                parts[0].setImageResource(R.drawable.croiseur1);
                parts[0].setOnLongClickListener(
                        v -> {
                            remove();
                            return true;
                        });

                parts[1] = new ImageView(activity.getApplicationContext());
                parts[1].setImageResource(R.drawable.croiseur2);
                parts[1].setOnLongClickListener(
                        v -> {
                            remove();
                            return true;
                        });

                parts[2] = new ImageView(activity.getApplicationContext());
                parts[2].setImageResource(R.drawable.croiseur3);
                parts[2].setOnLongClickListener(
                        v -> {
                            remove();
                            return true;
                        });

                parts[3] = new ImageView(activity.getApplicationContext());
                parts[3].setImageResource(R.drawable.croiseur4);
                parts[3].setOnLongClickListener(
                        v -> {
                            remove();
                            return true;
                        });
                break;
            case PORTE_AVION:
                complet = new ImageView(activity.getApplicationContext());
                complet.setImageResource(R.drawable.porteavion);
                complet.setTag(R.id.BoatID,id);//type
                complet.setOnLongClickListener(
                        v -> {
                            ClipData dragData = ClipData.newPlainText("","");
                            View.DragShadowBuilder myShadow = new testShadow(v);
                            v.startDrag(dragData,myShadow,v,0 );
                            return true;
                        });

                parts = new ImageView[5];

                parts[0] = new ImageView(activity.getApplicationContext());
                parts[0].setImageResource(R.drawable.porteavion1);
                parts[0].setOnLongClickListener(
                        v -> {
                            remove();
                            return true;
                        });

                parts[1] = new ImageView(activity.getApplicationContext());
                parts[1].setImageResource(R.drawable.porteavion2);
                parts[1].setOnLongClickListener(
                        v -> {
                            remove();
                            return true;
                        });

                parts[2] = new ImageView(activity.getApplicationContext());
                parts[2].setImageResource(R.drawable.porteavion3);
                parts[2].setOnLongClickListener(
                        v -> {
                            remove();
                            return true;
                        });

                parts[3] = new ImageView(activity.getApplicationContext());
                parts[3].setImageResource(R.drawable.porteavion4);
                parts[3].setOnLongClickListener(
                        v -> {
                            remove();
                            return true;
                        });

                parts[4] = new ImageView(activity.getApplicationContext());
                parts[4].setImageResource(R.drawable.porteavion5);
                parts[4].setOnLongClickListener(
                        v -> {
                            remove();
                            return true;
                        });
                break;
            default:
                throw new IllegalArgumentException("unknown ship type") ;
        }
    }

    public ImageView getComplet(){
        return complet;
    }

    public ImageView getParts(int n) {
        return parts[n];
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public void setDirection(int direction){
        if(direction != HORIZONTAL && direction != VERTICAL)
            throw new IllegalArgumentException("direction inconnue");
        for(ImageView v: parts)
            v.setRotation(direction);
        complet.setRotation(direction);
        this.direction = direction;
    }

    public void rotate(){
        if(direction == HORIZONTAL)
            setDirection(VERTICAL);
        else
            setDirection(HORIZONTAL);
    }

    private void remove(){
        controleurPlacement.removeBoat(id);
    }

    public int getSize() {
        return parts.length;
    }

    public void setCoord(int x, int y){
        this.x = x;
        this.y = y;
    }

    public boolean isOnBoard() {
        return x > -1;
    }

    public int getDirection() {
        return direction;
    }

    private class testShadow extends View.DragShadowBuilder{
        private int width;
        private int height;
        private float scaleX;
        private float scaleY;
        private float rotation;
        private int viewWidth;
        private int viewHeight;


        public testShadow(View view){
            super(view);
            scaleX = view.getScaleX();
            scaleY = view.getScaleY();
            rotation = view.getRotation();
            viewHeight = view.getHeight();
            viewWidth = view.getWidth();

            double rotationRad = Math.toRadians(view.getRotation());
            int w = (int) (view.getWidth() * view.getScaleX());
            int h = (int) (view.getHeight() * view.getScaleY());
            double s = Math.abs(Math.sin(rotationRad));
            double c = Math.abs(Math.cos(rotationRad));
            width = (int) (w * c + h * s);
            height = (int) (w * s + h * c);
        }

        @Override
        public void onDrawShadow(Canvas canvas) {
            canvas.scale(scaleX, scaleY, width / 2,
                    height / 2);
            canvas.rotate(rotation, width / 2, height / 2);
            canvas.translate((width - viewWidth) / 2,
                    (height - viewHeight) / 2);
            super.onDrawShadow(canvas);
        }

        @Override
        public void onProvideShadowMetrics(Point shadowSize,
                                           Point shadowTouchPoint) {
            shadowSize.set(width, height);
            shadowTouchPoint.set(shadowSize.x / 2, shadowSize.y / 2);
        }
    }
}
