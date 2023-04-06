package com.example.mastermind.game;

import android.view.MotionEvent;
import android.view.View;

public class TouchListener implements View.OnTouchListener{
    private GameView view;
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
                } else if(2*v.getWidth()*2/13+v.getWidth()/8-v.getWidth()/15<x && x<2*v.getWidth()*2/13+v.getWidth()/8+v.getWidth()/15) {
                    this.view.addChoix(2);
                } else if(3*v.getWidth()*2/13+v.getWidth()/8-v.getWidth()/15<x && x<3*v.getWidth()*2/13+v.getWidth()/8+v.getWidth()/15){
                    this.view.addChoix(3);
                } else if(4*v.getWidth()*2/13+v.getWidth()/8-v.getWidth()/15<x && x<4*v.getWidth()*2/13+v.getWidth()/8+v.getWidth()/15) {
                    this.view.addChoix(4);
                } else if(5*v.getWidth()*2/13+v.getWidth()/8-v.getWidth()/15<x && x<5*v.getWidth()*2/13+v.getWidth()/8+v.getWidth()/15){
                    this.view.addChoix(5);
                }
            // surveille si un bouton de controlle est cliqué (ici seule la soumission est gérée)
            } else if (v.getHeight()-v.getWidth()/9-v.getWidth()/10<y && y<v.getHeight()-v.getWidth()/9+v.getWidth()/10){
                this.view.changeState();
            }
        }
        return true;
    }
}
