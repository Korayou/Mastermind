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
import android.widget.LinearLayout;

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
    private LinearLayout rootView;
    public EndView(Context context, Grille grille, Integer[] combiWin, LinearLayout rootView) {
        super(context);
        this.combiWin=combiWin;
        this.grille=grille;
        this.circle = new Paint();
        this.rootView = rootView;
    }

    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);
        //affichage des anciennes soumissions
        //copie des soumissions
        LinkedList<Integer> grille = new LinkedList<Integer>();
        grille.addAll(this.grille.getSoumissions());
        for (int y = 0; y < 10; y++) {
            for (int x = 0; x < 4; x++) {
                this.circle.setColor(grille.pop());
                canvas.drawCircle((x * this.getWidth() / 8 + (this.getWidth() * 21 / 68)), (y * this.rootView.getHeight() / 14 + this.rootView.getHeight() / 21), this.getWidth() / 17, this.circle);
            }
        }
        LinkedList<Integer> notation = new LinkedList<Integer>();
        notation.addAll(this.grille.getNotations());
        for(int y=0; y<10; y++) {
            for(int x=0; x<2; x++) { // colonne gauche
                this.circle.setColor(notation.pop());
                canvas.drawCircle((x*this.getWidth()/11+(this.getWidth()/11)),(y*this.rootView.getHeight()/14+this.rootView.getHeight()/21), this.getWidth()/26, this.circle);
            }
            for(int x=0; x<2; x++) { // colonne droite
                this.circle.setColor(notation.pop());
                canvas.drawCircle((x*this.getWidth()/11+(this.getWidth()*4/5)),(y*this.rootView.getHeight()/14+this.rootView.getHeight()/21), this.getWidth()/26, this.circle);
            }
        }
        // affichage de la zone de saisie
        //copie de la zone de saisie
        for (int i=0;i<4;i++){
            this.circle.setColor(this.combiWin[i]);
            canvas.drawCircle((i*this.getWidth()/5+this.getWidth()/5),this.rootView.getHeight()*7/9, this.getWidth()/14, this.circle);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int desiredWidth = this.getWidth();
        int desiredHeight = this.rootView.getHeight()*7/9+this.getWidth()/5+this.rootView.getWidth()/7;

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int width;
        int height;

        if (widthMode == MeasureSpec.EXACTLY) {
            width = widthSize;
        } else if (widthMode == MeasureSpec.AT_MOST) {
            width = Math.min(desiredWidth, widthSize);
        } else {
            width = desiredWidth;
        }

        if (heightMode == MeasureSpec.EXACTLY) {
            height = heightSize;
        } else if (heightMode == MeasureSpec.AT_MOST) {
            height = Math.min(desiredHeight, heightSize);
        } else {
            height = desiredHeight;
        }
        setMeasuredDimension(width, height);
        System.out.println("Voulu : "+width+" par "+height);
    }

}
