package com.mobile.bataillenavale.lulu.bataillenavalemobile.vue;

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
 * Classe stockant les informations necessaires a afficher un bateau
 */

public class BateauVue{

    //Type de bateau disponibles
    public static final int TORPILLEUR = 2;
    public static final int CONTRE_TORPILLEUR = 3;
    public static final int CROISEUR = 4;
    public static final int PORTE_AVION = 5;

    public static final int HORIZONTAL = 90;
    public static final int VERTICAL = 0;

    private ImageView complet;
    private ImageView[] parts;
    private int x = -1;
    private int y = -1;
    private int direction = VERTICAL;

    public BateauVue(int type,Activity activity){
        //creer la vue complete du bateau puis celles de ses parties
        switch(type){
            case TORPILLEUR:
                complet = new ImageView(activity.getApplicationContext());
                complet.setImageResource(R.drawable.torpilleur);

                parts = new ImageView[2];

                parts[0] = new ImageView(activity.getApplicationContext());
                parts[0].setImageResource(R.drawable.torpilleurp1);

                parts[1] = new ImageView(activity.getApplicationContext());
                parts[1].setImageResource(R.drawable.torpilleurp2);
                break;

            case CONTRE_TORPILLEUR:
                complet = new ImageView(activity.getApplicationContext());
                complet.setImageResource(R.drawable.contre);

                parts = new ImageView[3];

                parts[0] = new ImageView(activity.getApplicationContext());
                parts[0].setImageResource(R.drawable.contre1);


                parts[1] = new ImageView(activity.getApplicationContext());
                parts[1].setImageResource(R.drawable.contre2);


                parts[2] = new ImageView(activity.getApplicationContext());
                parts[2].setImageResource(R.drawable.contre3);

                break;
            case CROISEUR:
                complet = new ImageView(activity.getApplicationContext());
                complet.setImageResource(R.drawable.croiseur);

                parts = new ImageView[4];

                parts[0] = new ImageView(activity.getApplicationContext());
                parts[0].setImageResource(R.drawable.croiseur1);


                parts[1] = new ImageView(activity.getApplicationContext());
                parts[1].setImageResource(R.drawable.croiseur2);

                parts[2] = new ImageView(activity.getApplicationContext());
                parts[2].setImageResource(R.drawable.croiseur3);


                parts[3] = new ImageView(activity.getApplicationContext());
                parts[3].setImageResource(R.drawable.croiseur4);

                break;
            case PORTE_AVION:
                complet = new ImageView(activity.getApplicationContext());
                complet.setImageResource(R.drawable.porteavion);

                parts = new ImageView[5];

                parts[0] = new ImageView(activity.getApplicationContext());
                parts[0].setImageResource(R.drawable.porteavion1);

                parts[1] = new ImageView(activity.getApplicationContext());
                parts[1].setImageResource(R.drawable.porteavion2);

                parts[2] = new ImageView(activity.getApplicationContext());
                parts[2].setImageResource(R.drawable.porteavion3);

                parts[3] = new ImageView(activity.getApplicationContext());
                parts[3].setImageResource(R.drawable.porteavion4);

                parts[4] = new ImageView(activity.getApplicationContext());
                parts[4].setImageResource(R.drawable.porteavion5);

                break;
            default:
                throw new IllegalArgumentException("unknown ship type") ;
        }
    }

    public BateauVue(int type,int id, Activity activity,ControleurPlacement controleurPlacement){
        this(type,activity);
        complet.setTag(R.id.BoatID,id);//type

        //ajout de la possiblite de bouger les bateaux
        complet.setOnLongClickListener(
                v -> {
                    ClipData dragData = ClipData.newPlainText("","");
                    View.DragShadowBuilder myShadow = new testShadow(v);
                    v.startDrag(dragData,myShadow,v,0 );
                    return true;
                });

        //ajout de la possibilite de renvoyer les bateaux dans le pool a partir des parties
        for(ImageView partie: parts)
            partie.setOnLongClickListener(
                    v -> {
                        controleurPlacement.removeBoat(id);
                        return true;
                    });
    }

    /*
        retourne l'image complete
     */
    public ImageView getComplet(){
        return complet;
    }

    /*
        retourne la n-ieme partie du bateau
     */
    public ImageView getParts(int n) {
        return parts[n];
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    /*
        tourne le bateau dans la direction donnee
     */
    public void setDirection(int direction){
        if(direction != HORIZONTAL && direction != VERTICAL)
            throw new IllegalArgumentException("direction inconnue");
        for(ImageView v: parts)
            v.setRotation(direction);
        complet.setRotation(direction);
        this.direction = direction;
    }

    /*
        tourne le bateau a 90°
     */
    public void rotate(){
        if(direction == HORIZONTAL)
            setDirection(VERTICAL);
        else
            setDirection(HORIZONTAL);
    }

    public int getSize() {
        return parts.length;
    }

    public void setCoord(int x, int y){
        this.x = x;
        this.y = y;
    }

    //retourne vrai si le bateau est pose sur le plateau
    public boolean isOnBoard() {
        return x > -1;
    }

    public int getDirection() {
        return direction;
    }

    /*
    classe permettant d'avoir une ombre de la meme taille et dans la meme direction que l'ImageView

    adpatee de la reponse trouvee ici:
        https://stackoverflow.com/questions/17049938/how-to-drag-a-rotated-dragshadow
     */
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
