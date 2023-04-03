package com.example.mastermind;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;

public class MainActivity extends Activity {
    private Button mHotSeat;
    private Button mORDI;
    private Button mSettings;
    //private Button mRules;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mHotSeat = findViewById(R.id.HotSeat);
        mORDI = findViewById(R.id.ORDI);
        mSettings = findViewById(R.id.Settings);
        //mRules = findViewById(R.id.Rules);
        /*mHotSeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent tp1Intent = new Intent(MainActivity.this, HotSeat.class);
                startActivity(tp1Intent);
            }
        });
        mORDI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent tp2Intent = new Intent(MainActivity.this, ORDI.class);
                startActivity(tp2Intent);
            }
        });*/
        mSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent tp3Intent = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(tp3Intent);
            }
        });
        /*mTP4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent tp4Intent = new Intent(MainActivity.this, TP4.class);
                startActivity(tp4Intent);
            }
        });*/
    }
}