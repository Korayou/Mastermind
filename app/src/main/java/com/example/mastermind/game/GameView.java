package com.example.mastermind.game;

import android.content.Context;
import android.graphics.Canvas;
import android.view.View;

public class GameView extends View {
    public GameView(Context context,Saisie saisie,Grille grille) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);

    }
}
