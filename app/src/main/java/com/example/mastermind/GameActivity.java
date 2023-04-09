package com.example.mastermind;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mastermind.end.EndView;
import com.example.mastermind.game.Bot;
import com.example.mastermind.game.GameView;
import com.example.mastermind.game.Grille;
import com.example.mastermind.game.Saisie;
import com.example.mastermind.game.TapListener;

public class GameActivity extends Activity implements SaisieActivity  {
    private Integer[] pionsAttaquant;
    private Integer[] pionsDefenseur;
    private Integer[] combiGagnante;
    private Integer pionVide;
    private Saisie saisie;
    private Grille grille;
    private boolean bot;
    private Bot theBot;
    private boolean emptyPion;
    private boolean state;
    private View view;
    private LinearLayout rootView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.bot = getIntent().getBooleanExtra("bot", false);
        this.emptyPion = getIntent().getBooleanExtra("pionVide", false);
        this.state=true;
        this.saisie=new Saisie();
        this.grille=new Grille();
        initpions();
        if (this.bot){
            this.theBot=new Bot(this.pionsAttaquant, this.pionsDefenseur, this.pionVide);
            this.combiGagnante=this.theBot.getCollectionWin();
        } else if(!this.bot){
            Intent choiceCombi = new Intent(this, ChoiceCombi.class);
            int[] tabpions;
            if(emptyPion) {
                tabpions=new int[7];
            } else {
                tabpions=new int[6];
            }
            for (int i=0;i<this.pionsAttaquant.length;i++) {
                tabpions[i] = this.pionsAttaquant[i];
            }
            choiceCombi.putExtra("emptyPion", emptyPion);
            choiceCombi.putExtra("pions", tabpions);
            startActivityForResult(choiceCombi, 1);
        }
        this.view=new GameView(this,this,this.saisie, this.grille);
        this.view.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        setContentView(R.layout.activity_game);
        this.rootView = findViewById(R.id.layout);
        this.rootView.addView(this.view);
        this.rootView.setOnTouchListener(new TapListener(this));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            if(resultCode == Activity.RESULT_OK){
                int[] tab = data.getIntArrayExtra("choix");
                if(emptyPion) {
                    this.combiGagnante=new Integer[7];
                } else {
                    this.combiGagnante=new Integer[6];
                }
                for (int i=0;i<tab.length;i++) {
                    this.combiGagnante[i] = tab[i];
                }
            }
            if(resultCode == Activity.RESULT_CANCELED) {
                this.finish();
            }
        }
    }

    public int getNbrPion() {
        return this.saisie.getSizeChoix();
    }

    //Change l'état de soumission à notation après qu'une combinaision ai été soumise puis inversement
    public void changeState() {
        if(!this.bot) {
            if (!this.state) {
                this.saisie.setChoix(this.pionsAttaquant);
                this.grille.addNotation(this.saisie.getSelection());
                if(end()){return;}
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
            if(end()){return;}
            this.view.invalidate();
        }
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

    public boolean end (){
        Integer[] lastNotation = this.grille.getLastNotation();
        int nbWin=0;
        for (int i=0;i<4;i++){
            if (lastNotation[i]==this.pionsDefenseur[1]){
                nbWin++;
            }
        }
        if(nbWin==4) {
            System.out.println("WIN");
            victoire(true);
            return true;
        } else if (this.grille.getSizeSubs()==10){
            System.out.println("LOSE");
            victoire(false);
            return true;
        } else {
            return false;
        }
    }

    public void victoire(boolean gagne){
        for (int i=0;i<4;i++){
            this.saisie.addSelection(this.combiGagnante[i]);
        }
        EndView lastview=new EndView(this, this.grille, this.combiGagnante, this.rootView);
        this.view=lastview;
        this.rootView.removeAllViews();
        this.view.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        this.view.setBackgroundColor(this.getResources().getColor(R.color.grey));
        this.rootView.addView(this.view);

        TextView textView = new TextView(this);
        textView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        textView.setGravity(Gravity.CENTER);
        if (gagne){
            textView.setText("Victoire de l'attaquant en "+this.grille.getSizeSubs()+" coups");
        } else {
            textView.setText("Victoire du défenseur");
        }
        this.rootView.addView(textView);
        System.out.println(textView.getX() + " : " + textView.getY());
        this.rootView.invalidate();
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
        if(emptyPion) {
            this.pionsAttaquant = new Integer[7];
        } else {
            this.pionsAttaquant = new Integer[6];
        }
        this.pionsAttaquant[0]=this.getResources().getColor(R.color.pink);
        this.pionsAttaquant[1]=this.getResources().getColor(R.color.purple);
        this.pionsAttaquant[2]=this.getResources().getColor(R.color.blue);
        this.pionsAttaquant[3]=this.getResources().getColor(R.color.green);
        this.pionsAttaquant[4]=this.getResources().getColor(R.color.yellow);
        this.pionsAttaquant[5]=this.getResources().getColor(R.color.white);
        if(emptyPion) {
            this.pionsAttaquant[6]=this.getResources().getColor(R.color.vide);
        }

        // on inisialise la saisie
        this.saisie.setChoix(this.pionsAttaquant);
        this.saisie.initSelection(this.getResources().getColor(R.color.pionVide));

        // on rempli la grille de cases grises
        this.grille.initGrille(this.pionVide);
    }
}