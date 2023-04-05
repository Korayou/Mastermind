package com.example.mastermind;

import android.app.Activity;
import android.os.Bundle;

import com.example.mastermind.game.GameView;
import com.example.mastermind.game.Grille;
import com.example.mastermind.game.Saisie;

public class HotSeatActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Saisie saisie = new Saisie();
        Grille grille = new Grille();
        GameView vuePartie = new GameView(this, saisie, grille);
        setContentView(vuePartie);
        new SuiviPartie(vuePartie);

    }
}