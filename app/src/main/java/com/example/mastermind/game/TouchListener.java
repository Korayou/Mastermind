package com.example.mastermind.game;

import android.view.MotionEvent;
import android.view.View;

public class TouchListener implements View.OnTouchListener{

    public TouchListener(){}

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int action = event.getActionMasked();
        Float x = event.getX();
        Float y = event.getY();

        if (action==MotionEvent.ACTION_UP)
        {

        }
        return true;
    }
}
