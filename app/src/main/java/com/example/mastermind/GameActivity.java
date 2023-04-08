package com.example.mastermind;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;

import com.example.mastermind.game.Bot;
import com.example.mastermind.game.GameView;
import com.example.mastermind.game.Grille;
import com.example.mastermind.game.Saisie;

public class GameActivity extends Activity {
    private Integer[] pionsAttaquant;
    private Integer[] pionsDefenseur;
    private Integer pionVide;
    private Saisie saisie;
    private Grille grille;
    private boolean bot;
    private Bot theBot;
    private boolean state;
    private View view;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.bot = getIntent().getBooleanExtra("bot", false);
        this.state=true;
        this.saisie=new Saisie();
        this.grille=new Grille();
        initpions();
        this.theBot=new Bot(this.pionsAttaquant, this.pionsDefenseur, this.pionVide);
        if(!this.bot){
            //ChoiceCombi
        }
        this.view=new GameView(this,this.saisie, this.grille);
        setContentView(view);
    }

    //Change l'état de soumission à notation après qu'une combinaision ai été soumise puis inversement
    public void changeState() {
        if(!this.bot) {
            if (!this.state) {
                this.saisie.setChoix(this.pionsAttaquant);
                this.grille.addNotation(this.saisie.getSelection());
                victoire();
                this.saisie.initSelection(this.getResources().getColor(R.color.pionVide));
                this.view.invalidate();
                this.state = !this.state;
            } else if (this.state && this.saisie.getSizeSelection() == 4) {
                this.saisie.setChoix(this.pionsDefenseur);
                this.grille.addSoumission(this.saisie.getSelection());
                this.saisie.initSelection(this.getResources().getColor(R.color.pionVide));
                this.view.invalidate();
                this.state = !this.state;
            }
        } else if (this.state && this.saisie.getSizeSelection() == 4) {
            Integer[] combi = this.saisie.getSelection();
            this.grille.addSoumission(combi);
            this.saisie.initSelection(this.getResources().getColor(R.color.pionVide));
            //On fait noter la combinaison au Bot
            this.grille.addNotation(this.theBot.notation((combi)));
            victoire();
            this.view.invalidate();
        }
    }

    public boolean getState() {
        return this.state;
    }

    //ajoute une nouvelle couleur pour la séléction à soumettre
    public void addChoix(int choix){
        this.saisie.addSelection(choix);
        this.view.invalidate();
    }

    public void removePion() {
        this.saisie.removeSelection(this.getResources().getColor(R.color.pionVide));
        this.view.invalidate();
    }

    public void clearChoix() {
        this.saisie.initSelection(this.getResources().getColor(R.color.pionVide));
        this.view.invalidate();
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
        this.saisie.setChoix(this.pionsAttaquant);
        this.saisie.initSelection(this.getResources().getColor(R.color.pionVide));

        // on rempli la grille de cases grises
        this.grille.initGrille(this.pionVide);
    }
}