package com.example.mastermind.game;

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

import java.util.Collection;
import java.util.LinkedList;

public class GameView extends View {

    private Saisie saisie;
    private Grille grille;
    private Paint circle;
    private Bitmap cancelBtn;
    private Bitmap backBtn;
    private Bitmap validBtn;
    public GameView(GameActivity context, Saisie saisie, Grille grille) {
        super(context);
        this.saisie=saisie;
        this.grille=grille;
        this.setOnTouchListener(new TouchListener(context));
        //state indique true si le joueur soumet une combinaison ou false si elle est noté
        this.circle = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);
        this.setBackgroundColor(this.getResources().getColor(R.color.grey));
        if(this.grille!=null) {
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
        }
        // affichage de la zone de saisie
        //copie de la zone de saisie
        Integer[] saisie = this.saisie.getSelection();
        for (int i=0;i<4;i++){
            this.circle.setColor(saisie[i]);
            canvas.drawCircle((i*this.getWidth()/5+this.getWidth()/5),this.getHeight()-this.getHeight()*2/9, this.getWidth()/14, this.circle);
        }

        // affichage des couleurs choisissables
        //copie des couleurs dispos
        LinkedList<Integer> couleurs = new LinkedList<Integer>();
        couleurs.addAll(this.saisie.getChoix());
        for (int i=0;i<this.saisie.getChoix().size();i++){
            this.circle.setColor(couleurs.pop());
            canvas.drawCircle((i*this.getWidth()*2/13+this.getWidth()/8),this.getHeight()-this.getHeight()/7, this.getWidth()/16, this.circle);
        }

        // bouton valider
        validBtn = decodeSampledBitmapFromResource(getResources(), R.drawable.valid_button, getWidth()/13, getWidth()/13); // version img
        canvas.drawBitmap(validBtn, this.getWidth()/2+(this.getWidth()/11)*2-getWidth()/13, this.getHeight()/2+this.getHeight()*2/5, null);
        // bouton retour
        backBtn = decodeSampledBitmapFromResource(getResources(), R.drawable.back_button, getWidth()/13, getWidth()/13);
        canvas.drawBitmap(backBtn, this.getWidth()/2-getWidth()/13, this.getHeight()/2+this.getHeight()*2/5, null);
        // bouton annuler
        cancelBtn = decodeSampledBitmapFromResource(getResources(), R.drawable.cancel_button, getWidth()/13, getWidth()/13);
        canvas.drawBitmap(cancelBtn, this.getWidth()/2-(getWidth()/11)*2-getWidth()/13, this.getHeight()/2+this.getHeight()*2/5, null);
    }

    public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {
            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            while ((halfHeight / inSampleSize) >= reqHeight && (halfWidth / inSampleSize) >= reqWidth) {
                inSampleSize *= 3;
            }
        }

        return inSampleSize;
    }
    public Bitmap decodeSampledBitmapFromResource(Resources res, int resId, int width, int height) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);
        options.inSampleSize = calculateInSampleSize(options, width, height);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }
}
