package com.example.guessthecountry;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class Levels extends AppCompatActivity {
    CountryArrays countryArrays = new CountryArrays();
    GlobalVariable variable = new GlobalVariable();
    ProgressBar firstProgressBar, secondProgressBar, threeProgressBar, euroProgressBar;
    int counterFirstProgressBar, counterSecondProgressBar, counterThreeProgressBar, counterEuroProgressBar,
            countAllFlags = countryArrays.countries.length,
            countAsianFlags = countryArrays.asian_gerb_images.length,
            countEuroFlags = variable.countEuroFlags;
    SharedPreferences sPrefLevel, sPref;
    Button startFlagGame, asian_game, gerb_game, euro_game, setting;
    TextView countFlags, secondText, threeText, euro_learn;
    final String COUNTRY_SAVED = "country_saved", ASIA_SAVED = "asia_saved", COUNTRY_GERB_SAVED = "asian_gerb_saved";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.levels_activity);
        init();
        startFlagGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Levels.this, GuessTheFlag.class));
            }
        });

        asian_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Levels.this, GuessAsiaCountries.class));
            }
        });

        gerb_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Levels.this, GuessAsianGerb.class));
            }
        });

        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Levels.this, Settings.class));
            }
        });

        euro_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Levels.this, GuessEuroFlags.class));
            }
        });
    }

    public void init(){
        firstProgressBar = findViewById(R.id.game_first);
        secondProgressBar = findViewById(R.id.game_second);
        threeProgressBar = findViewById(R.id.game_asian_second);
        euroProgressBar = findViewById(R.id.game_three);

        euro_learn = findViewById(R.id.third_euro);

        startFlagGame = findViewById(R.id.start_flag_game);
        countFlags = findViewById(R.id.first_element);
        asian_game = findViewById(R.id.second_flag_game);
        euro_game = findViewById(R.id.three_flag_game);

        secondText = findViewById(R.id.sec_element);
        threeText = findViewById(R.id.sec_gerb_element);

        gerb_game = findViewById(R.id.second_asian_gerb_game);
        setting = findViewById(R.id.setting);
        loadInt();
        loadIntAsian();
        loadGerbAsian();
        loadEuroFlag();
        progressFirst();
    }

    public void progressFirst(){
        firstProgressBar.setProgress((int)((double)counterFirstProgressBar / countAllFlags * 100));
        secondProgressBar.setProgress((int)((double)counterSecondProgressBar / countAsianFlags * 100));
        threeProgressBar.setProgress((int)((double)counterThreeProgressBar / countAsianFlags * 100));
        euroProgressBar.setProgress((int)((double)counterEuroProgressBar / countEuroFlags * 100));
    }

    public void loadInt(){
        sPrefLevel =  getSharedPreferences(COUNTRY_SAVED, MODE_PRIVATE);
        counterFirstProgressBar = sPrefLevel.getInt(COUNTRY_SAVED, 0);
        if(counterFirstProgressBar != 0)
            countFlags.setText(String.valueOf(counterFirstProgressBar));
    }

    public void loadIntAsian(){
        sPrefLevel =  getSharedPreferences(ASIA_SAVED, MODE_PRIVATE);
        counterSecondProgressBar = sPrefLevel.getInt(ASIA_SAVED, 0);
        if(counterSecondProgressBar != 0)
            secondText.setText(String.valueOf(counterSecondProgressBar));
    }

    public void loadGerbAsian() {
        sPrefLevel =  getSharedPreferences(COUNTRY_GERB_SAVED, MODE_PRIVATE);
        counterThreeProgressBar = sPrefLevel.getInt(COUNTRY_GERB_SAVED, 0);
        if(counterThreeProgressBar != 0)
            threeText.setText(String.valueOf(counterThreeProgressBar));
    }

    public void loadEuroFlag() {
        sPrefLevel =  getSharedPreferences(variable.COUNTRY_EURO_SAVED, MODE_PRIVATE);
        counterEuroProgressBar = sPrefLevel.getInt(variable.COUNTRY_EURO_SAVED, 0);
        if(counterEuroProgressBar != 0)
            euro_learn.setText(String.valueOf(counterEuroProgressBar));
    }
}