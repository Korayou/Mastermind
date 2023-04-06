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
        //state indique true si le joueur soumet une combinaison ou false si elle est noté
        this.state=true;
        this.circle = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);
        this.setBackgroundColor(this.getResources().getColor(R.color.grey));
        //affichage des anciennes soumissions
        //copie des soumissions
        LinkedList<Integer> grille = new LinkedList<Integer>();
        grille.addAll(this.grille.getSoumissions());
        System.out.println(grille.size());
        for (int y=0; y<10;y++) {
            for (int x=0;x<4;x++) {
                this.circle.setColor(grille.pop());
                //TODO: coordonnées propres
                canvas.drawCircle(( x*this.getWidth()/8+(this.getWidth()*21/68)),(y*this.getHeight()/14+this.getHeight()/21), this.getWidth()/17, this.circle);
            }
        }
        // affichage de la zone de saisie
        //copie de la zone de saisie
        LinkedList<Integer> saisie = new LinkedList<Integer>();
        saisie.addAll(this.saisie.getSelection());
        for (int i=0;i<this.saisie.getSelection().size();i++){
            this.circle.setColor(saisie.pop());
            //TODO: coordonnées propres (encore)
            canvas.drawCircle((i*this.getWidth()/5+this.getWidth()/5),this.getHeight()-this.getHeight()*2/9, this.getWidth()/14, this.circle);
        }

        // affichage des couleurs choisissables
        //copie des couleurs dispos
        LinkedList<Integer> couleurs = new LinkedList<Integer>();
        couleurs.addAll(this.saisie.getChoix());
        System.out.println(couleurs.size());
        for (int i=0;i<this.saisie.getChoix().size();i++){
            this.circle.setColor(couleurs.pop());
            //TODO: coordonnées propres (encore)
            canvas.drawCircle((i*this.getWidth()*2/13+this.getWidth()/8),this.getHeight()-this.getHeight()/7, this.getWidth()/16, this.circle);
        }
        //TODO: ajout des colonnes de notation
        LinkedList<Integer> notation = new LinkedList<Integer>();
        notation.addAll(this.grille.getNotations());
        for(int y=0; y<10; y++) { // colonne gauche
            for(int x=0; x<2; x++) {
                this.circle.setColor(notation.pop());
                canvas.drawCircle((x*this.getWidth()/11+(this.getWidth()/11)),(y*this.getHeight()/14+getHeight()/21), this.getWidth()/26, this.circle);
            }
        }
        for(int y=0; y<10; y++) { // colonne droite
            for(int x=0; x<2; x++) {
                this.circle.setColor(notation.pop());
                canvas.drawCircle((x*this.getWidth()/11+(this.getWidth()*4/5)),(y*this.getHeight()/14+getHeight()/21), this.getWidth()/26, this.circle);
            }
        }

        //TODO: ajout des boutons
        //Test de bouton valider
        this.circle.setColor(this.getResources().getColor(R.color.green));
        canvas.drawCircle((this.getWidth()/2)+(getWidth()/11)*2,this.getHeight()-this.getHeight()/16, this.getWidth()/13, this.circle);
        // bouton retour
        this.circle.setColor(this.getResources().getColor(R.color.blue));
        canvas.drawCircle((this.getWidth()/2), this.getHeight()-this.getHeight()/16, this.getWidth()/13, this.circle);
        // bouton annuler
        this.circle.setColor(this.getResources().getColor(R.color.red));
        canvas.drawCircle((this.getWidth()/2)-(getWidth()/11)*2, this.getHeight()-this.getHeight()/16, this.getWidth()/13, this.circle);
    }

    //Change l'état de soumission à notation après qu'une combinaision ai été soumise puis inversement
    public void changeState() {
        if (this.saisie.getSizeSelection() == 4) {
            //partie à décommenter pour acceder à la notation
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

    //ajoute une nouvelle couleur pour la séléction à soumettre
    public void addChoix(int choix){
        this.saisie.addSelection(choix);
        this.invalidate();
    }
    //initialise les collections de pions et remplie saisie et grille de pions vides
    public void initpions(){
        //on initialise les pions
        //on créer une ligne de 4 pions gris représentants une ligne de pions vides
        this.pionsPasPlaces = new LinkedList<Integer>();
        for (int i=0;i<4;i++){
            this.pionsPasPlaces.add(this.getResources().getColor(R.color.pionVide));
        }

        //Le défenseur a des pions noirs et blancs
        this.pionsDefenseur = new LinkedList<Integer>();
        this.pionsDefenseur.add(this.getResources().getColor(R.color.white));
        this.pionsDefenseur.add(this.getResources().getColor(R.color.black));

        //L'attaquant a des pions de couleurs
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
