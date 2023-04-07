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

    private Integer[] pionsAttaquant;
    private Integer[] pionsDefenseur;
    private Integer pionVide;
    private Saisie saisie;
    private Grille grille;
    private boolean bot;
    private Bot theBot;
    private Paint circle;
    private boolean state;
    public GameView(Context context,Saisie saisie,Grille grille, boolean bot) {
        super(context);
        this.saisie=saisie;
        this.grille=grille;
        this.bot=bot;
        initpions();
        this.theBot=new Bot(this.pionsAttaquant, this.pionsDefenseur, this.pionVide);
        this.setOnTouchListener(new TouchListener(this));
        //on initialise les collections de pions
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
        for (int y=0; y<10;y++) {
            for (int x=0;x<4;x++) {
                this.circle.setColor(grille.pop());
                //TODO: coordonnées propres
                canvas.drawCircle(( x*this.getWidth()/8+(this.getWidth()*21/68)),(y*this.getHeight()/14+this.getHeight()/21), this.getWidth()/17, this.circle);
            }
        }
        // affichage de la zone de saisie
        //copie de la zone de saisie
        Integer[] saisie = this.saisie.getSelection();
        for (int i=0;i<4;i++){
            this.circle.setColor(saisie[i]);
            //TODO: coordonnées propres (encore)
            canvas.drawCircle((i*this.getWidth()/5+this.getWidth()/5),this.getHeight()-this.getHeight()*2/9, this.getWidth()/14, this.circle);
        }

        // affichage des couleurs choisissables
        //copie des couleurs dispos
        LinkedList<Integer> couleurs = new LinkedList<Integer>();
        couleurs.addAll(this.saisie.getChoix());
        for (int i=0;i<this.saisie.getChoix().size();i++){
            this.circle.setColor(couleurs.pop());
            //TODO: coordonnées propres (encore)
            canvas.drawCircle((i*this.getWidth()*2/13+this.getWidth()/8),this.getHeight()-this.getHeight()/7, this.getWidth()/16, this.circle);
        }
        //TODO: ajout des colonnes de notation
        LinkedList<Integer> notation = new LinkedList<Integer>();
        notation.addAll(this.grille.getNotations());
        for(int y=0; y<10; y++) {
            for(int x=0; x<2; x++) { // colonne gauche
                this.circle.setColor(notation.pop());
                canvas.drawCircle((x*this.getWidth()/11+(this.getWidth()/11)),(y*this.getHeight()/14+getHeight()/21), this.getWidth()/26, this.circle);
            }
            for(int x=0; x<2; x++) { // colonne droite
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
        if(!this.bot) {
            if (!this.state) {
                this.saisie.setChoix(this.pionsAttaquant);
                this.grille.addNotation(this.saisie.getSelection());
                victoire();
                this.saisie.initSelection(this.getResources().getColor(R.color.pionVide));
                this.invalidate();
                this.state = !this.state;
            } else if (this.state && this.saisie.getSizeSelection() == 4) {
                this.saisie.setChoix(this.pionsDefenseur);
                this.grille.addSoumission(this.saisie.getSelection());
                this.saisie.initSelection(this.getResources().getColor(R.color.pionVide));
                invalidate();
                this.state = !this.state;
            }
        } else if (this.state && this.saisie.getSizeSelection() == 4) {
            Integer[] combi = this.saisie.getSelection();
            this.grille.addSoumission(combi);
            this.saisie.initSelection(this.getResources().getColor(R.color.pionVide));
            //On fait noter la combinaison au Bot
            this.grille.addNotation(this.theBot.notation((combi)));
            victoire();
            invalidate();
        }
    }

    public boolean getState() {
        return this.state;
    }

    //ajoute une nouvelle couleur pour la séléction à soumettre
    public void addChoix(int choix){
        this.saisie.addSelection(choix);
        this.invalidate();
    }

    /*public void removeChoix() {
        this.saisie.removeSelection(this.getResources().getColor(R.color.pionVide));
        this.invalidate();
    }*/

    public void clearChoix() {
        this.saisie.initSelection(this.getResources().getColor(R.color.pionVide));
        this.invalidate();
    }

    public boolean victoire (){
        Integer[] lastNotation = this.grille.getLastNotation();
        int nbWin=0;
        for (int i=0;i<4;i++){
            if (lastNotation[i]==this.pionsDefenseur[1]){
                nbWin++;
            }
        }
        if(nbWin==4) {
            System.out.println("WIN");
            return true;
        } else {
            System.out.println("LOSE");
            return false;
        }
    }

    //initialise les collections de pions et remplie saisie et grille de pions vides
    public void initpions(){
        //on initialise les pions
        //on créer une ligne de 4 pions gris représentants une ligne de pions vides
        this.pionVide = this.getResources().getColor(R.color.grey);

        //Le défenseur a des pions noirs et blancs
        this.pionsDefenseur = new Integer[2];
        this.pionsDefenseur[0]=this.getResources().getColor(R.color.white);
        this.pionsDefenseur[1]=this.getResources().getColor(R.color.black);

        //L'attaquant a des pions de couleurs
        this.pionsAttaquant = new Integer[6];
        this.pionsAttaquant[0]=this.getResources().getColor(R.color.pink);
        this.pionsAttaquant[1]=this.getResources().getColor(R.color.purple);
        this.pionsAttaquant[2]=this.getResources().getColor(R.color.blue);
        this.pionsAttaquant[3]=this.getResources().getColor(R.color.green);
        this.pionsAttaquant[4]=this.getResources().getColor(R.color.yellow);
        this.pionsAttaquant[5]=this.getResources().getColor(R.color.white);

        // on inisialise la saisie
        saisie.setChoix(this.pionsAttaquant);
        saisie.initSelection(this.getResources().getColor(R.color.pionVide));

        // on rempli la grille de cases grises
        grille.initGrille(this.pionVide);
    }
}
