package com.example.guessthecountry;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Settings extends AppCompatActivity {
    GlobalVariable variable = new GlobalVariable();
    Button clearDate;
    SharedPreferences sPref, sPref2, sPrefArr, sPref3, sPrefOne1, sPrefTwo2, sPref4;
    final String COUNTRY_SAVED = "country_saved",
            ASIA_SAVED = "asia_saved",
            COUNTRY_ARRAY_SAVED = "array_saved",
            COUNTRY_GERB_SAVED = "asian_gerb_saved";
    TextView one, two, three;
    int oneInt, twoInt, threeInt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        init();
        clearDate = findViewById(R.id.clearDate);
        clearDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteDate();
            }
        });
    }
    public void deleteDate(){
        sPref = getSharedPreferences(COUNTRY_SAVED, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sPref.edit();
        editor.putInt(COUNTRY_SAVED, 0);
        editor.apply();

        sPref2 = getSharedPreferences(ASIA_SAVED, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor2 = sPref2.edit();
        editor2.putInt(ASIA_SAVED, 0);
        editor2.apply();

        sPref3 = getSharedPreferences(COUNTRY_GERB_SAVED, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor3 = sPref3.edit();
        editor3.putInt(COUNTRY_GERB_SAVED, 0);
        editor3.apply();

        sPref4 = getSharedPreferences(variable.COUNTRY_EURO_SAVED, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor4 = sPref4.edit();
        editor4.putInt(variable.COUNTRY_EURO_SAVED, 0);
        editor4.apply();

        sPrefArr = getSharedPreferences(COUNTRY_ARRAY_SAVED, Context.MODE_PRIVATE);
        SharedPreferences.Editor editorArr = sPrefArr.edit();
        editorArr.putString(COUNTRY_ARRAY_SAVED, null);
        editorArr.apply();

        //-------------------

        sPrefOne1 = getSharedPreferences(variable.SAVE_ONE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editorOne = sPrefOne1.edit();
        editorOne.putInt(variable.SAVE_ONE, 0);
        editorOne.apply();

        sPrefTwo2 = getSharedPreferences(variable.SAVE_TWO, Context.MODE_PRIVATE);
        SharedPreferences.Editor editorTwo = sPrefTwo2.edit();
        editorTwo.putInt(variable.SAVE_TWO, 0);
        editorTwo.apply();
    }

    public void init(){
        one = findViewById(R.id.result_one);
        two = findViewById(R.id.result_two);
        three = findViewById(R.id.result_three);
        loadResultOne();
        loadResultTwo();
        loadResultThree();
    }

    public void loadResultOne() {
        sPrefOne1 =  getSharedPreferences(variable.SAVE_ONE, MODE_PRIVATE);
        oneInt = sPrefOne1.getInt(variable.SAVE_ONE, 0);
        if(oneInt != 0)
            one.setText(String.valueOf(oneInt));
    }

    public void loadResultTwo() {
        sPrefTwo2 =  getSharedPreferences(variable.SAVE_TWO, MODE_PRIVATE);
        twoInt = sPrefTwo2.getInt(variable.SAVE_TWO, 0);
        if(twoInt != 0)
            two.setText(String.valueOf(twoInt));
    }

    public void loadResultThree() {
        threeInt = oneInt - twoInt;
        if(threeInt != 0)
            three.setText(String.valueOf(threeInt));
    }
}