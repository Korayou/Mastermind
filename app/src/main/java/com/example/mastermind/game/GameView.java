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
    public GameView(Context context,Saisie saisie,Grille grille) {
        super(context);
        this.saisie=saisie;
        this.grille=grille;
        //on initialise les collections de pions
        initpions();
        this.circle = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);
        this.setBackgroundColor(this.getResources().getColor(R.color.grey));
        LinkedList<Integer> grille = (LinkedList<Integer>) this.grille.getSoumissions();
        System.out.println("test?");
        for (int y=0; y<10;y++) {
            for (int x=0;x<4;x++) {
                /*this.circle.setColor(grille.pop());
                canvas.drawCircle(x*10,y*10, 50, this.circle);
                System.out.println("rond grille");*/
            }
        }
    }

    public void redraw(){
        invalidate();
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
        saisie.setSelection(this.pionsAttaquant);
        saisie.setChoix(this.pionsPasPlaces);

        // on rempli la grille de cases grises
        grille.initGrille(this.pionsPasPlaces);
    }
}
