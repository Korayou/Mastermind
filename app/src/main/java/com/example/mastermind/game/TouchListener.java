package com.example.mastermind.game;

import android.view.MotionEvent;
import android.view.View;

import com.example.mastermind.GameActivity;
import com.example.mastermind.SaisieActivity;

public class TouchListener implements View.OnTouchListener{
    private SaisieActivity context;
    public TouchListener(SaisieActivity context) {
        this.context=context;
    }
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int action = event.getActionMasked();
        Float x = event.getX();
        Float y = event.getY();

        if (action==MotionEvent.ACTION_UP)
        {
            //Surveille quel bouton de couleur est choisi
            if (v.getHeight()-v.getHeight()/7-v.getWidth()/16<y && y<v.getHeight()-v.getHeight()/7+v.getWidth()/16){
                if(this.context.getNbrPion() == 6 || this.context.getNbrPion() == 2) {
                    if (v.getWidth() / 8 - v.getWidth() / 15 < x && x < v.getWidth() / 8 + v.getWidth() / 15) {
                        this.context.addChoix(0);
                    } else if (v.getWidth() * 2 / 13 + v.getWidth() / 8 - v.getWidth() / 15 < x && x < v.getWidth() * 2 / 13 + v.getWidth() / 8 + v.getWidth() / 15) {
                        this.context.addChoix(1);
                    } else if (2 * v.getWidth() * 2 / 13 + v.getWidth() / 8 - v.getWidth() / 15 < x && x < 2 * v.getWidth() * 2 / 13 + v.getWidth() / 8 + v.getWidth() / 15) {
                        this.context.addChoix(2);
                    } else if (3 * v.getWidth() * 2 / 13 + v.getWidth() / 8 - v.getWidth() / 15 < x && x < 3 * v.getWidth() * 2 / 13 + v.getWidth() / 8 + v.getWidth() / 15) {
                        this.context.addChoix(3);
                    } else if (4 * v.getWidth() * 2 / 13 + v.getWidth() / 8 - v.getWidth() / 15 < x && x < 4 * v.getWidth() * 2 / 13 + v.getWidth() / 8 + v.getWidth() / 15) {
                        this.context.addChoix(4);
                    } else if (5 * v.getWidth() * 2 / 13 + v.getWidth() / 8 - v.getWidth() / 15 < x && x < 5 * v.getWidth() * 2 / 13 + v.getWidth() / 8 + v.getWidth() / 15) {
                        this.context.addChoix(5);
                    }
                } else if(this.context.getNbrPion() == 7) {
                    if (v.getWidth() / 8 - v.getWidth() / 15 < x && x < v.getWidth() / 8 + v.getWidth() / 15) {
                        this.context.addChoix(0);
                    } else if (v.getWidth() * 2 / 14 + v.getWidth() / 14 - v.getWidth() / 18 < x && x < v.getWidth() * 2 / 14 + v.getWidth() / 14 + v.getWidth() / 18) {
                        this.context.addChoix(1);
                    } else if (2 * v.getWidth() * 2 / 14 + v.getWidth() / 14 - v.getWidth() / 18 < x && x < 2 * v.getWidth() * 2 / 14 + v.getWidth() / 14 + v.getWidth() / 18) {
                        this.context.addChoix(2);
                    } else if (3 * v.getWidth() * 2 / 14 + v.getWidth() / 14 - v.getWidth() / 18 < x && x < 3 * v.getWidth() * 2 / 14 + v.getWidth() / 14 + v.getWidth() / 18) {
                        this.context.addChoix(3);
                    } else if (4 * v.getWidth() * 2 / 14 + v.getWidth() / 14 - v.getWidth() / 18 < x && x < 4 * v.getWidth() * 2 / 14 + v.getWidth() / 14 + v.getWidth() / 18) {
                        this.context.addChoix(4);
                    } else if (5 * v.getWidth() * 2 / 14 + v.getWidth() / 14 - v.getWidth() / 18 < x && x < 5 * v.getWidth() * 2 / 14 + v.getWidth() / 14 + v.getWidth() / 18) {
                        this.context.addChoix(5);
                    } else if(6 * v.getWidth() * 2 / 14 + v.getWidth() / 14 - v.getWidth() / 18 < x && x < 6 * v.getWidth() * 2 / 14 + v.getWidth() / 14 + v.getWidth() / 18) {
                        this.context.addChoix(6);
                    }
                }

            // surveille si un bouton de controle est cliqué
            } else if (v.getHeight()-v.getWidth()/16-v.getWidth()/13<y && y<v.getHeight()-v.getHeight()/16+v.getWidth()/13){
                if(v.getWidth()/2+(v.getWidth()/11)*2-v.getWidth()/13<x && x<v.getWidth()/2+(v.getWidth()/11)*2+v.getWidth()/13) { // soumettre
                    this.context.changeState();
                } else if (v.getWidth()/2-v.getWidth()/13<x && x<v.getWidth()/2+v.getWidth()/13) { // retour
                    this.context.removePion();
                } else if (v.getWidth()/2-(v.getWidth()/11)*2-v.getWidth()/13<x && x<v.getWidth()/2-(v.getWidth()/11)*2+v.getWidth()/13) { // annuler
                    this.context.clearChoix();
                }
            }
        }
        return true;
    }
}
