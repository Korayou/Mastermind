package com.example.mastermind.game;

import android.view.MotionEvent;
import android.view.View;

public class TouchListener implements View.OnTouchListener{
    private GameView view;
    private int i = 0;
    public TouchListener(GameView view) {
        this.view=view;
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
                if(v.getWidth()/8-v.getWidth()/15<x && x<v.getWidth()/8+v.getWidth()/15){
                    this.view.addChoix(0);
                } else if(v.getWidth()*2/13+v.getWidth()/8-v.getWidth()/15<x && x<v.getWidth()*2/13+v.getWidth()/8+v.getWidth()/15){
                    this.view.addChoix(1);
                    if(!this.view.getState()) {
                        i++;
                    }
                } else if(2*v.getWidth()*2/13+v.getWidth()/8-v.getWidth()/15<x && x<2*v.getWidth()*2/13+v.getWidth()/8+v.getWidth()/15) {
                    this.view.addChoix(2);
                } else if(3*v.getWidth()*2/13+v.getWidth()/8-v.getWidth()/15<x && x<3*v.getWidth()*2/13+v.getWidth()/8+v.getWidth()/15){
                    this.view.addChoix(3);
                } else if(4*v.getWidth()*2/13+v.getWidth()/8-v.getWidth()/15<x && x<4*v.getWidth()*2/13+v.getWidth()/8+v.getWidth()/15) {
                    this.view.addChoix(4);
                } else if(5*v.getWidth()*2/13+v.getWidth()/8-v.getWidth()/15<x && x<5*v.getWidth()*2/13+v.getWidth()/8+v.getWidth()/15){
                    this.view.addChoix(5);
                }
            // surveille si un bouton de controle est cliqué
            } else if (v.getHeight()-v.getWidth()/16-v.getWidth()/13<y && y<v.getHeight()-v.getHeight()/16+v.getWidth()/13){
                if(v.getWidth()/2+(v.getWidth()/11)*2-v.getWidth()/13<x && x<v.getWidth()/2+(v.getWidth()/11)*2+v.getWidth()/13) { // soumettre
                    if(!this.view.getState()) {
                        this.view.conditionVictoire(i);
                    }
                    this.view.changeState();
                    i = 0;
                } else if (v.getWidth()/2-v.getWidth()/13<x && x<v.getWidth()/2+v.getWidth()/13) { // retour
                    //this.view.removeChoix();
                } else if (v.getWidth()/2-(v.getWidth()/11)*2-v.getWidth()/13<x && x<v.getWidth()/2-(v.getWidth()/11)*2+v.getWidth()/13) { // annuler
                    this.view.clearChoix();
                }
            }
        }
        return true;
    }
}
