package com.example.mastermind.game;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

import com.example.mastermind.R;

import java.util.Collection;
import java.util.LinkedList;

public class GameView extends View {

    private Collection<Integer> pionsAttaquant;
    private Collection<Integer> pionsDefenseur;
    private Collection<Integer> pionsPasPlaces;
    private Saisie saisie;
    private Grille grille;
    private Paint circle;
    private boolean state;
    public GameView(Context context,Saisie saisie,Grille grille) {
        super(context);
        this.saisie=saisie;
        this.grille=grille;
        this.setOnTouchListener(new TouchListener(this));
        //on initialise les collections de pions
        initpions();
        //state indique si le joueur soumet une combinaison ou false si elle est noté
        this.state=true;
        this.circle = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);
        this.setBackgroundColor(this.getResources().getColor(R.color.grey));
        //affichage des anciennes soumissions
        LinkedList<Integer> grille = new LinkedList<Integer>();
        grille.addAll(this.grille.getSoumissions());
        System.out.println(grille.size());
        for (int y=0; y<10;y++) {
            for (int x=0;x<4;x++) {
                this.circle.setColor(grille.pop());
                //TODO: coordonnées propres
                canvas.drawCircle(( x*this.getWidth()/8+(this.getWidth()*21/68)),(y*this.getWidth()/8+this.getWidth()/10), this.getWidth()/17, this.circle);
            }
        }
        // affichage de la zone de saisie
        LinkedList<Integer> saisie = new LinkedList<Integer>();
        saisie.addAll(this.saisie.getSelection());
        for (int i=0;i<this.saisie.getSelection().size();i++){
            this.circle.setColor(saisie.pop());
            //TODO: coordonnées propres (encore)
            canvas.drawCircle((i*this.getWidth()/5+this.getWidth()/5),this.getWidth()*58/40, this.getWidth()/12, this.circle);
        }

        // affichage des couleurs choisissables
        LinkedList<Integer> couleurs = new LinkedList<Integer>();
        couleurs.addAll(this.saisie.getChoix());
        System.out.println(couleurs.size());
        for (int i=0;i<this.saisie.getChoix().size();i++){
            this.circle.setColor(couleurs.pop());
            //TODO: coordonnées propres (encore)
            canvas.drawCircle((i*this.getWidth()*2/13+this.getWidth()/8),this.getWidth()*689/420, this.getWidth()/15, this.circle);
        }
        //Test pour valider
        this.circle.setColor(this.getResources().getColor(R.color.green));
        canvas.drawCircle((this.getWidth()/2),this.getHeight()-this.getWidth()/9, this.getWidth()/10, this.circle);
    }

    public void changeState() {
        if (this.saisie.getSizeSelection() == 4) {
            /*this.state = !this.state;
            if (this.state) {
                this.saisie.setChoix(this.pionsAttaquant);
            } else if (!this.state) {
                this.saisie.setChoix(this.pionsDefenseur);
            }*/
            this.grille.addSoumission(this.saisie.getSelection());
            this.saisie.initSelection(this.getResources().getColor(R.color.pionVide));
            this.invalidate();
        }
    }

    //Soumet une nouvelle couleur pour la séléction à soumettre
    public void addChoix(int choix){
        this.saisie.addSelection(choix);
        this.invalidate();
    }
    //initialise les collections de pions
    public void initpions(){
        //on initialise les pions
        this.pionsPasPlaces = new LinkedList<Integer>();
        for (int i=0;i<4;i++){
            this.pionsPasPlaces.add(this.getResources().getColor(R.color.pionVide));
        }

        this.pionsDefenseur = new LinkedList<Integer>();
        this.pionsDefenseur.add(this.getResources().getColor(R.color.white));
        this.pionsDefenseur.add(this.getResources().getColor(R.color.black));

        this.pionsAttaquant = new LinkedList<Integer>();
        this.pionsAttaquant.add(this.getResources().getColor(R.color.pink));
        this.pionsAttaquant.add(this.getResources().getColor(R.color.purple));
        this.pionsAttaquant.add(this.getResources().getColor(R.color.blue));
        this.pionsAttaquant.add(this.getResources().getColor(R.color.green));
        this.pionsAttaquant.add(this.getResources().getColor(R.color.yellow));
        this.pionsAttaquant.add(this.getResources().getColor(R.color.white));

        // on inisialise la saisie
        saisie.setChoix(this.pionsAttaquant);
        saisie.initSelection(this.getResources().getColor(R.color.pionVide));

        // on rempli la grille de cases grises
        grille.initGrille(this.pionsPasPlaces);
    }
}
