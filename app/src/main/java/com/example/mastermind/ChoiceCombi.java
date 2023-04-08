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
        boolean bot = getIntent().getBooleanExtra("bot", false);
        if setContentView(new ChoiceCombi(this, new Saisie()));
    }

    public void letsplay()
}