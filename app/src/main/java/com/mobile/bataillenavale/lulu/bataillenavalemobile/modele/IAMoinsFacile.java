package com.mobile.bataillenavale.lulu.bataillenavalemobile.modele;


import java.util.Vector;

/**
 * Created by simon on 08/01/18.
 */

public class IAMoinsFacile extends Joueur {
    private Vector<Integer> dernierCoup = null;

    public IAMoinsFacile(int tailleX, int tailleY, int nbTorpilleur, int nbContreTorpilleur, int nbCroiseur, int nbPorteAvion){
        super(tailleX, tailleY);
        Runnable run = new initPlateau(plateauModele,nbTorpilleur,nbContreTorpilleur,nbCroiseur,nbPorteAvion,tailleX,tailleY);
        Thread t = new Thread(run);
        t.start();
    }

    /*
     * Construit un vecteur de tir selon la strategie de l'IA facile :
     *  les coordonnees sont choisies aleatoirement sauf si le dernier coup avait touche,
     *  auquel cas l'IA visera aleatoirement autour du tir precedent
     */
    @Override
    public Vector<Integer> tirer(){
        int x;
        int y;
        if(dernierCoup != null) {
            x = dernierCoup.get(0);
            y = dernierCoup.get(1);
            if (getTirType(x,y) == 2) {
                double direction = Math.random();
                if (direction > 0.75) {
                    //haut
                    if (y > 0 && getTirType(x,y - 1) == 0) {
                        dernierCoup = new Vector<>();
                        dernierCoup.add(0, x);
                        dernierCoup.add(1, y - 1);
                        return dernierCoup;
                    }
                } else if (direction > 0.5) {
                    //droite
                    if (x + 1 < plateauModele.getSizeX() && getTirType(x + 1,y) == 0) {
                        dernierCoup = new Vector<>();
                        dernierCoup.add(0, x + 1);
                        dernierCoup.add(1, y);
                        return dernierCoup;
                    }
                } else if (direction > 0.25) {
                    //bas
                    if (y + 1 < plateauModele.getSizeY() && getTirType(x,y + 1) == 0) {
                        dernierCoup = new Vector<>();
                        dernierCoup.add(0, x);
                        dernierCoup.add(1, y + 1);
                        return dernierCoup;
                    }
                } else {
                    //gauche
                    if (x > plateauModele.getSizeX() && getTirType(x - 1,y) == 0) {
                        dernierCoup = new Vector<>();
                        dernierCoup.add(0, x - 1);
                        dernierCoup.add(1, y);
                        return dernierCoup;
                    }
                }
            }
        }
        do {
            x = (int) (Math.random() * plateauModele.getSizeX());
            y = (int) (Math.random() * plateauModele.getSizeY());
        }while(getTirType(x,y) != 0);
        dernierCoup = new Vector<>();
        dernierCoup.add(0,x);
        dernierCoup.add(1,y);
        return dernierCoup;
    }

    @Override
    public void invaliderCase(int x, int y, int type) {
        super.invaliderCase(x, y, type);
    }

    /*
     * Positionne les bateaux de l'IA.
     * Execute dans un thread a part pour etre effectue en meme temps que la placement du
     *  joueur et pour eviter que cette operation qui peut etre assez longue ne soit arretee
     *  par le systeme avant d'avoir termine
     */
    private class initPlateau implements Runnable{
        private PlateauModele plateau;
        private int nbTorpilleur;
        private int nbContreTorpilleur;
        private int nbCroiseur;
        private int nbPorteAvion;
        private int sizeX;
        private int sizeY;

        public initPlateau(PlateauModele plateau,int nbTorpilleur, int nbContreTorpilleur, int nbCroiseur, int nbPorteAvion, int sizeX, int sizeY){
            this.plateau = plateau;
            this.nbTorpilleur = nbTorpilleur;
            this.nbContreTorpilleur=nbContreTorpilleur;
            this.nbCroiseur=nbCroiseur;
            this.nbPorteAvion = nbPorteAvion;
            this.sizeX = sizeX;
            this.sizeY = sizeY;
        }

        @Override
        public void run() {
            final int tentativeMax = 5;
            int x ;
            int y ;
            double direction;
            boolean reussi;
            do {
                reussi = true;
                plateau.clear();
                for (int i = 0; i < nbPorteAvion; i++) {
                    if(reussi) {
                        int nbTentative = 0;
                        do {
                            direction = Math.random();
                            if (direction < 0.5) {
                                //Vertical
                                x = (int) (Math.random() * sizeX);
                                y = (int) (Math.random() * (sizeY - 5));
                                direction = Bateau.VERTICAL;
                            } else {
                                //Horizontal
                                x = (int) (4 + Math.random() * (sizeX - 5));
                                y = (int) (Math.random() * (sizeY - 5));
                                direction = Bateau.HORIZONTAL;
                            }
                            reussi = plateau.poser(x, y, (int) direction, Bateau.PORTE_AVION);
                        } while (!reussi && nbTentative++ < tentativeMax);
                    }else
                        break;
                }
                if(reussi)
                    for (int i = 0; i < nbCroiseur; i++) {
                        int nbTentative = 0;
                        do {
                            direction = Math.random();
                            if (direction < 0.5) {
                                //Vertical
                                x = (int) (Math.random() * sizeX);
                                y = (int) (Math.random() * (sizeY - 4));
                                direction = Bateau.VERTICAL;
                            } else {
                                //Horizontal
                                x = (int) (3 + Math.random() * (sizeX - 4));
                                y = (int) (Math.random() * (sizeY - 4));
                                direction = Bateau.HORIZONTAL;
                            }
                            reussi = plateau.poser(x, y, (int) direction, Bateau.CROISEUR);
                        } while (!reussi && nbTentative++ < tentativeMax);
                    }
                if(reussi)
                    for (int i = 0; i < nbContreTorpilleur; i++) {
                        int nbTentative = 0;
                        do {
                            direction = Math.random();
                            if (direction < 0.5) {
                                //Vertical
                                x = (int) (Math.random() * sizeX);
                                y = (int) (Math.random() * (sizeY - 3));
                                direction = Bateau.VERTICAL;
                            } else {
                                //Horizontal
                                x = (int) (2 + Math.random() * (sizeX - 3));
                                y = (int) (Math.random() * (sizeY - 3));
                                direction = Bateau.HORIZONTAL;
                            }
                            reussi = plateau.poser(x, y, (int) direction, Bateau.CONTRE_TORPILLEUR);
                        } while (!reussi && nbTentative++ < tentativeMax);
                    }
                if(reussi)
                    for (int i = 0; i < nbTorpilleur; i++) {
                        int nbTentative = 0;
                        do {
                            direction = Math.random();
                            if (direction < 0.5) {
                                //Vertical
                                x = (int) (Math.random() * sizeX);
                                y = (int) (Math.random() * (sizeY - 2));
                                direction = Bateau.VERTICAL;
                            } else {
                                //Horizontal
                                x = (int) (1 + Math.random() * (sizeX - 2));
                                y = (int) (Math.random() * (sizeY - 2));
                                direction = Bateau.HORIZONTAL;
                            }
                            reussi = plateau.poser(x, y, (int) direction, Bateau.TORPILLEUR);
                        } while (!reussi && nbTentative++ < tentativeMax);
                    }
            }while(!reussi);
        }
    }
}
