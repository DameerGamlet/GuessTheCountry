package com.example.guessthecountry;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class GuessTheFlag extends AppCompatActivity implements VariableInterface{
    public int numberIgame, indexCorrectResult, countFlags = variable.countFlags;
    ImageView countryImage;
    ImageButton soundImageButton;
    Button first, second, third, fourth, fifth, next;
    SharedPreferences sPref, sPrefArr, sPrefOne, sPrefTwo, sPrefSound;
    ImageButton back;
    String name_country;
    TextView messageForResult;
    final String mesTrue = "Верно. На картинке изображён флаг страны \"",
            mesFalse1 = "Неверно, это не \"",
            mesFalse2 = "\". \nПравильный ответ: ";

    MediaPlayer trueAnswerSound, falseAnswerSound;

    private void init(){
        countryImage = findViewById(R.id.countryImage);
        first = findViewById(R.id.firstButton);
        second = findViewById(R.id.secondButton);
        third = findViewById(R.id.thirdButton);
        fourth = findViewById(R.id.fourthButton);
        fifth = findViewById(R.id.fifthButton);
        next = findViewById(R.id.next);
        messageForResult = findViewById(R.id.message);
        back = findViewById(R.id.back);
        soundImageButton = findViewById(R.id.soundImage);

        first.setId(0);
        second.setId((int)1);
        third.setId((int)2);
        fourth.setId((int)3);
        fifth.setId((int)4);

        trueAnswerSound = MediaPlayer.create(this, R.raw.true_answer);
        falseAnswerSound = MediaPlayer.create(this, R.raw.false_answer);
    }

    public void imageIn(int i){
        if(i == 1){
            soundImageButton.setImageResource(R.drawable.onsound);
        }
        else if(i == 0){
            soundImageButton.setImageResource(R.drawable.offsound);
        }
    }

    private void soundPlay(MediaPlayer sound){
        sound.start();
    }

    public void buttonOperation(Button button){
        System.out.println(loadSound());
        if(button.getId() == indexCorrectResult){
            savedCountry();
            savedResultTwo();
            button.setBackgroundResource(R.drawable.truebutton);
            messageForResult.setText(mesTrue + first.getText() + "\".");
            if(loadSound() == 1)
                soundPlay(trueAnswerSound);
        }
        else{
            button.setBackgroundResource(R.drawable.falsebutton);
            messageForResult.setText(mesFalse1 + button.getText() + mesFalse2 + name_country);
            if(loadSound() == 1)
                soundPlay(falseAnswerSound);
        }
        savedResultOne();
        onClickableButton(false);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gues_the_flag);
        init();
        generated();
        imageIn(loadSound());

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        soundImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(loadSound() == 1){
                    saveSound(0);
                }
                else if(loadSound() == 0){
                    saveSound(1);
                }
                imageIn(loadSound());
                System.out.println("sound: " + loadSound());
            }
        });

        first.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonOperation(first);
            }
        });

        second.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonOperation(second);
            }
        });

        third.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonOperation(third);
            }
        });

        fourth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonOperation(fourth);
            }
        });

        fifth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonOperation(fifth);
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                messageForResult.setText("");
                onClickableButton(true);
                generated();
            }
        });
    }

    public void onClickableButton(boolean option) {
        first.setClickable(option);
        second.setClickable(option);
        third.setClickable(option);
        fourth.setClickable(option);
        fifth.setClickable(option);
    }

    public void clearButton(){
        first.setBackgroundResource(R.drawable.button);
        second.setBackgroundResource(R.drawable.button);
        third.setBackgroundResource(R.drawable.button);
        fourth.setBackgroundResource(R.drawable.button);
        fifth.setBackgroundResource(R.drawable.button);
    }

    public void savedCountry(){
        sPref = getSharedPreferences(variable.COUNTRY_SAVED, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sPref.edit();
        editor.putInt(variable.COUNTRY_SAVED, loadCountry() + 1);
        editor.apply();
        savedCountryArray(numberIgame);
    }

    public int loadCountry(){
        sPref = getSharedPreferences(variable.COUNTRY_SAVED, Context.MODE_PRIVATE);
        return sPref.getInt(variable.COUNTRY_SAVED, 0);
    }

    public void savedCountryArray(int n){
        sPrefArr = getSharedPreferences(variable.COUNTRY_ARRAY_SAVED, Context.MODE_PRIVATE);
        SharedPreferences.Editor editorArr = sPrefArr.edit();
        editorArr.putString(variable.COUNTRY_ARRAY_SAVED, loadCountryArray() + " " + numberIgame);
        editorArr.apply();
    }

    public String loadCountryArray(){
        sPref = getSharedPreferences(variable.COUNTRY_ARRAY_SAVED, Context.MODE_PRIVATE);
        return sPref.getString(variable.COUNTRY_ARRAY_SAVED, null);
    }

    //-----------------------------------------------------------------------------------

    public void savedResultOne(){
        sPrefOne = getSharedPreferences(variable.SAVE_ONE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editorOne = sPrefOne.edit();
        editorOne.putInt(variable.SAVE_ONE, loadResultOne() + 1);
        editorOne.apply();
    }

    public int loadResultOne(){
        sPrefOne = getSharedPreferences(variable.SAVE_ONE, Context.MODE_PRIVATE);
        return sPrefOne.getInt(variable.SAVE_ONE, 0);
    }

    public void savedResultTwo(){
        sPrefTwo = getSharedPreferences(variable.SAVE_TWO, Context.MODE_PRIVATE);
        SharedPreferences.Editor editorTwo = sPrefTwo.edit();
        editorTwo.putInt(variable.SAVE_TWO, loadResultTwo() + 1);
        editorTwo.apply();
    }

    public int loadResultTwo(){
        sPrefTwo = getSharedPreferences(variable.SAVE_TWO, Context.MODE_PRIVATE);
        return sPrefTwo.getInt(variable.SAVE_TWO, 0);
    }

    //-----------------
    public void saveSound(int sound){
        sPrefSound = getSharedPreferences(variable.SOUND, Context.MODE_PRIVATE);
        SharedPreferences.Editor editoro = sPrefSound.edit();
        editoro.putInt(variable.SOUND, sound);
        editoro.apply();
    }

    public int loadSound(){
        sPrefSound = getSharedPreferences(variable.SOUND, Context.MODE_PRIVATE);
        return sPrefSound.getInt(variable.SOUND, 0);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void generated() {
        clearButton();
        System.out.println(loadCountryArray());
        numberIgame = random.nextInt(countFlags);
        countryImage.setImageResource(arrays.countries[numberIgame]);
        name_country = arrays.name_country[numberIgame];
        Set<Integer> randset = new HashSet<>();
        while (randset.size() != 5){
            int temp = random.nextInt(countFlags);
            String[] tempArr;
            List<Integer> listAnswer = new ArrayList<>();
            if(loadCountryArray() != null){
                tempArr = loadCountryArray().split(" ");
                for (String s : tempArr) {
                    if(!s.equals("null"))
                        listAnswer.add(Integer.parseInt(s.trim()));
                }
            }

            if(listAnswer.size() != 0){
                if (temp != numberIgame)
                    randset.add(temp);
            }
            else if (temp != numberIgame && !listAnswer.contains(temp))
                randset.add(temp);
        }

        int[] rand_o_5 = new int[5];
        for (int i = 0; i < 5; i++)
            rand_o_5[i] = 0;

        Iterator<Integer> i = randset.iterator();
        int k = 0;
        while (i.hasNext()) {
            rand_o_5[k] = (i.next());
            k++;
        }

        int[] newArray = new int[rand_o_5.length];
        List<Integer> indexes = new ArrayList<>(rand_o_5.length);
        int count = 0;
        Random random = new Random();
        do {
            int var = random.nextInt(rand_o_5.length);
            if (!indexes.contains(var)) {
                indexes.add(var);
                newArray[var] = rand_o_5[count++];
            }
        } while (count != rand_o_5.length);
        rand_o_5 = newArray.clone();

        indexCorrectResult = (int) (Math.random() * 4);
        switch (indexCorrectResult) {
            case 0:
                first.setText(arrays.correctCountriesNames[numberIgame]);
                second.setText(arrays.correctCountriesNames[rand_o_5[0]]);
                third.setText(arrays.correctCountriesNames[rand_o_5[1]]);
                fourth.setText(arrays.correctCountriesNames[rand_o_5[2]]);
                fifth.setText(arrays.correctCountriesNames[rand_o_5[3]]);
                break;
            case 1:
                first.setText(arrays.correctCountriesNames[rand_o_5[0]]);
                second.setText(arrays.correctCountriesNames[numberIgame]);
                third.setText(arrays.correctCountriesNames[rand_o_5[1]]);
                fourth.setText(arrays.correctCountriesNames[rand_o_5[2]]);
                fifth.setText(arrays.correctCountriesNames[rand_o_5[3]]);
                break;
            case 2:
                first.setText(arrays.correctCountriesNames[rand_o_5[0]]);
                second.setText(arrays.correctCountriesNames[rand_o_5[1]]);
                third.setText(arrays.correctCountriesNames[numberIgame]);
                fourth.setText(arrays.correctCountriesNames[rand_o_5[2]]);
                fifth.setText(arrays.correctCountriesNames[rand_o_5[3]]);
                break;
            case 3:
                first.setText(arrays.correctCountriesNames[rand_o_5[0]]);
                second.setText(arrays.correctCountriesNames[rand_o_5[1]]);
                third.setText(arrays.correctCountriesNames[rand_o_5[2]]);
                fourth.setText(arrays.correctCountriesNames[numberIgame]);
                fifth.setText(arrays.correctCountriesNames[rand_o_5[3]]);
                break;
            case 4:
                first.setText(arrays.correctCountriesNames[rand_o_5[0]]);
                second.setText(arrays.correctCountriesNames[rand_o_5[1]]);
                third.setText(arrays.correctCountriesNames[rand_o_5[2]]);
                fourth.setText(arrays.correctCountriesNames[rand_o_5[3]]);
                fifth.setText(arrays.correctCountriesNames[numberIgame]);
                break;
        }
    }
}