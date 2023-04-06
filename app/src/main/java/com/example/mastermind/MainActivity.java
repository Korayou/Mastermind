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
        mHotSeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent HotSeat = new Intent(MainActivity.this, HotSeatActivity.class);
                HotSeat.putExtra("bot", false);
                startActivity(HotSeat);
            }
        });
        mORDI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ordi = new Intent(MainActivity.this, HotSeatActivity.class);
                ordi.putExtra("bot", true);
                startActivity(ordi);
            }
        });
        mSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent settings = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(settings);
            }
        });
        /*mTP4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent rules = new Intent(MainActivity.this, TP4.class);
                startActivity(rules);
            }
        });*/
    }
}