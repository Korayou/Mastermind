package com.example.mastermind.game;

import android.app.Activity;
import android.view.MotionEvent;
import android.view.View;

import com.example.mastermind.GameActivity;
import com.example.mastermind.SaisieActivity;

public class TapListener implements View.OnTouchListener{
    private Activity context;
    public TapListener(Activity context) {
        this.context=context;
    }
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int action = event.getActionMasked();

        if (action == MotionEvent.ACTION_UP) {
            this.context.finish();
        }
        return true;
    }
}
