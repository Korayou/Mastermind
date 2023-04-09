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
    private Button mRules;
    private boolean pionV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mHotSeat = findViewById(R.id.HotSeat);
        mORDI = findViewById(R.id.ORDI);
        mSettings = findViewById(R.id.Settings);
        mRules = findViewById(R.id.Rules);
        mHotSeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent HotSeat = new Intent(MainActivity.this, GameActivity.class);
                HotSeat.putExtra("bot", false);
                HotSeat.putExtra("pionVide", pionV);
                startActivity(HotSeat);
            }
        });
        mORDI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ordi = new Intent(MainActivity.this, GameActivity.class);
                ordi.putExtra("bot", true);
                ordi.putExtra("pionVide", pionV);
                startActivity(ordi);
            }
        });
        mSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent settings = new Intent(MainActivity.this, SettingsActivity.class);
                startActivityForResult(settings, 2);
            }
        });
        mRules.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent rules = new Intent(MainActivity.this, RulesActivity.class);
                startActivity(rules);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 2) {
            pionV = data.getBooleanExtra("pionV", false);
        }
    }
}