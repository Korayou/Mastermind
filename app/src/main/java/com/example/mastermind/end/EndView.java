package com.example.mastermind.end;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

import com.example.mastermind.GameActivity;
import com.example.mastermind.R;
import com.example.mastermind.SaisieActivity;
import com.example.mastermind.game.Grille;
import com.example.mastermind.game.Saisie;

import java.util.Collection;
import java.util.LinkedList;

public class EndView extends View {

    private Integer[] combiWin;
    private Grille grille;
    private Paint circle;
    private int nbcoups;
    public EndView(Context context, Grille grille, Integer[] combiWin) {
        super(context);
        this.combiWin=combiWin;
        this.grille=grille;
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
        for (int y = 0; y < 10; y++) {
            for (int x = 0; x < 4; x++) {
                this.circle.setColor(grille.pop());
                canvas.drawCircle((x * this.getWidth() / 8 + (this.getWidth() * 21 / 68)), (y * this.getHeight() / 14 + this.getHeight() / 21), this.getWidth() / 17, this.circle);
            }
        }
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
        // affichage de la zone de saisie
        //copie de la zone de saisie
        for (int i=0;i<4;i++){
            this.circle.setColor(this.combiWin[i]);
            canvas.drawCircle((i*this.getWidth()/5+this.getWidth()/5),this.getHeight()-this.getHeight()*2/9, this.getWidth()/14, this.circle);
        }
    }
}
