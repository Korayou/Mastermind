package com.example.mastermind;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.mastermind.game.GameView;
import com.example.mastermind.game.Saisie;

public class ChoiceCombi extends Activity  implements SaisieActivity {
    private Saisie saisie;
    private Integer[] combiGagnante;
    private Integer[] pions;
    private View view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int[] tab = getIntent().getIntArrayExtra("pions");
        this.pions=new Integer[6];
        for (int i=0;i<tab.length-1;i++) {
            this.pions[i] = tab[i];
        }
        this.saisie=new Saisie();
        this.saisie.initSelection(this.getResources().getColor(R.color.pionVide));
        this.saisie.setChoix(this.pions);
        this.view=new GameView(this,this,this.saisie, null);
    }

    public void changeState(){
        Intent returnIntent = new Intent();
        returnIntent.putExtra("choix",this.saisie.getSelection());
        setResult(Activity.RESULT_OK,returnIntent);
        finish();
    }

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
}