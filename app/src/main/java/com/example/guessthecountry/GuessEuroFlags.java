package com.example.guessthecountry;

import android.content.Context;
import android.content.SharedPreferences;
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

public class GuessEuroFlags extends AppCompatActivity {
    GlobalVariable variable = new GlobalVariable();
    CountryArrays arrays = new CountryArrays();
    Random random = new Random();
    int numberIgame, indexCorrectResult,
            countEuroFlags = variable.countEuroFlags;
    ImageView countryImage;
    Button first, second, third, fourth, fifth, next;
    SharedPreferences sPref, sPrefArr, sPrefOne, sPrefTwo;
    ImageButton back;
    String name_country, cap;
    TextView messageForResult, countryInfo;
    final String mesTrue = "Верно. Столиция выбрана верно.",
            mesFalse1 = "Неверно, это не \"",
            mesFalse2 = "\". \nПравильный ответ: ";

    private void init() {
        countryImage = findViewById(R.id.countryImage);
        first = findViewById(R.id.firstButton);
        second = findViewById(R.id.secondButton);
        third = findViewById(R.id.thirdButton);
        fourth = findViewById(R.id.fourthButton);
        fifth = findViewById(R.id.fifthButton);

        countryInfo = findViewById(R.id.country_info);

        next = findViewById(R.id.next);
        messageForResult = findViewById(R.id.message);
        back = findViewById(R.id.back);

        first.setId(0);
        second.setId((int) 1);
        third.setId((int) 2);
        fourth.setId((int) 3);
        fifth.setId((int) 4);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guess_euro_flags);
        init();
        generated();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        first.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (first.getId() == indexCorrectResult) {
                    savedCountry();
                    savedResultTwo();
                    first.setBackgroundResource(R.drawable.truebutton);
                    messageForResult.setText(mesTrue);
                } else {
                    first.setBackgroundResource(R.drawable.falsebutton);
                    messageForResult.setText(mesFalse1 + first.getText() + mesFalse2 + cap);
                }
                savedResultOne();
                offClickableButton();
            }
        });
        second.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (second.getId() == indexCorrectResult) {
                    savedCountry();
                    savedResultTwo();
                    second.setBackgroundResource(R.drawable.truebutton);
                    messageForResult.setText(mesTrue);
                } else {
                    second.setBackgroundResource(R.drawable.falsebutton);
                    messageForResult.setText(mesFalse1 + second.getText() + mesFalse2 + cap);
                }
                offClickableButton();
                savedResultOne();
            }
        });
        third.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (third.getId() == indexCorrectResult) {
                    savedCountry();
                    savedResultTwo();
                    third.setBackgroundResource(R.drawable.truebutton);
                    messageForResult.setText(mesTrue);
                } else {
                    third.setBackgroundResource(R.drawable.falsebutton);
                    messageForResult.setText(mesFalse1 + third.getText() + mesFalse2 + cap);
                }
                offClickableButton();
                savedResultOne();
            }
        });
        fourth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fourth.getId() == indexCorrectResult) {
                    savedCountry();
                    savedResultTwo();
                    fourth.setBackgroundResource(R.drawable.truebutton);
                    messageForResult.setText(mesTrue);
                } else {
                    fourth.setBackgroundResource(R.drawable.falsebutton);
                    messageForResult.setText(mesFalse1 + fourth.getText() + mesFalse2 + cap);
                }
                offClickableButton();
                savedResultOne();
            }
        });
        fifth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fifth.getId() == indexCorrectResult) {
                    savedCountry();
                    savedResultTwo();
                    fifth.setBackgroundResource(R.drawable.truebutton);
                    messageForResult.setText(mesTrue);
                } else {
                    fifth.setBackgroundResource(R.drawable.falsebutton);
                    messageForResult.setText(mesFalse1 + first.getText() + mesFalse2 + cap);
                }
                offClickableButton();
                savedResultOne();
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                messageForResult.setText("");
                onClickableButton();
                generated();
            }
        });
    }

    public void onClickableButton() {
        first.setClickable(true);
        second.setClickable(true);
        third.setClickable(true);
        fourth.setClickable(true);
        fifth.setClickable(true);
    }

    public void offClickableButton() {
        first.setClickable(false);
        second.setClickable(false);
        third.setClickable(false);
        fourth.setClickable(false);
        fifth.setClickable(false);
    }

    public void clearButton() {
        first.setBackgroundResource(R.drawable.button);
        second.setBackgroundResource(R.drawable.button);
        third.setBackgroundResource(R.drawable.button);
        fourth.setBackgroundResource(R.drawable.button);
        fifth.setBackgroundResource(R.drawable.button);
    }

    public void savedCountry() {
        sPref = getSharedPreferences(variable.COUNTRY_EURO_SAVED, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sPref.edit();
        editor.putInt(variable.COUNTRY_EURO_SAVED, loadCountry() + 1);
        editor.apply();
        savedCountryArray(numberIgame);
    }

    public int loadCountry() {
        sPref = getSharedPreferences(variable.COUNTRY_EURO_SAVED, Context.MODE_PRIVATE);
        return sPref.getInt(variable.COUNTRY_EURO_SAVED, 0);
    }

    public void savedCountryArray(int n){
        sPrefArr = getSharedPreferences(variable.COUNTRY_EURO_ARRAY_SAVED, Context.MODE_PRIVATE);
        SharedPreferences.Editor editorArr = sPrefArr.edit();
        editorArr.putString(variable.COUNTRY_EURO_ARRAY_SAVED, loadCountryArray() + " " + numberIgame);
        editorArr.apply();
    }

    public String loadCountryArray(){
        sPrefArr = getSharedPreferences(variable.COUNTRY_EURO_ARRAY_SAVED, Context.MODE_PRIVATE);
        return sPrefArr.getString(variable.COUNTRY_EURO_ARRAY_SAVED, null);
    }

    //-----------------------------------------------------------------------------------

    public void savedResultOne() {
        sPrefOne = getSharedPreferences(variable.SAVE_ONE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editorOne = sPrefOne.edit();
        editorOne.putInt(variable.SAVE_ONE, loadResultOne() + 1);
        editorOne.apply();
    }

    public int loadResultOne() {
        sPrefOne = getSharedPreferences(variable.SAVE_ONE, Context.MODE_PRIVATE);
        return sPrefOne.getInt(variable.SAVE_ONE, 0);
    }

    public void savedResultTwo() {
        sPrefTwo = getSharedPreferences(variable.SAVE_TWO, Context.MODE_PRIVATE);
        SharedPreferences.Editor editorTwo = sPrefTwo.edit();
        editorTwo.putInt(variable.SAVE_TWO, loadResultTwo() + 1);
        editorTwo.apply();
    }

    public int loadResultTwo() {
        sPrefTwo = getSharedPreferences(variable.SAVE_TWO, Context.MODE_PRIVATE);
        return sPrefTwo.getInt(variable.SAVE_TWO, 0);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void generated() {
        clearButton();
        numberIgame = random.nextInt(countEuroFlags);
        countryImage.setImageResource(arrays.euroCountry[numberIgame]);
        name_country = arrays.euroNameCountry[numberIgame];
        cap = arrays.euroCapitalization[numberIgame];
        countryInfo.setText("На рисунке флаг страны: " + name_country);

        Set<Integer> randset = new HashSet<>();
        while (randset.size() != 5){
            int temp = random.nextInt(countEuroFlags);
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
                first.setText(arrays.correctEuroStringCountry[numberIgame]);
                second.setText(arrays.correctEuroStringCountry[rand_o_5[0]]);
                third.setText(arrays.correctEuroStringCountry[rand_o_5[1]]);
                fourth.setText(arrays.correctEuroStringCountry[rand_o_5[2]]);
                fifth.setText(arrays.correctEuroStringCountry[rand_o_5[3]]);
                break;
            case 1:
                first.setText(arrays.correctEuroStringCountry[rand_o_5[0]]);
                second.setText(arrays.correctEuroStringCountry[numberIgame]);
                third.setText(arrays.correctEuroStringCountry[rand_o_5[1]]);
                fourth.setText(arrays.correctEuroStringCountry[rand_o_5[2]]);
                fifth.setText(arrays.correctEuroStringCountry[rand_o_5[3]]);
                break;
            case 2:
                first.setText(arrays.correctEuroStringCountry[rand_o_5[0]]);
                second.setText(arrays.correctEuroStringCountry[rand_o_5[1]]);
                third.setText(arrays.correctEuroStringCountry[numberIgame]);
                fourth.setText(arrays.correctEuroStringCountry[rand_o_5[2]]);
                fifth.setText(arrays.correctEuroStringCountry[rand_o_5[3]]);
                break;
            case 3:
                first.setText(arrays.correctEuroStringCountry[rand_o_5[0]]);
                second.setText(arrays.correctEuroStringCountry[rand_o_5[1]]);
                third.setText(arrays.correctEuroStringCountry[rand_o_5[2]]);
                fourth.setText(arrays.correctEuroStringCountry[numberIgame]);
                fifth.setText(arrays.correctEuroStringCountry[rand_o_5[3]]);
                break;
            case 4:
                first.setText(arrays.correctEuroStringCountry[rand_o_5[0]]);
                second.setText(arrays.correctEuroStringCountry[rand_o_5[1]]);
                third.setText(arrays.correctEuroStringCountry[rand_o_5[2]]);
                fourth.setText(arrays.correctEuroStringCountry[rand_o_5[3]]);
                fifth.setText(arrays.correctEuroStringCountry[numberIgame]);
                break;
        }
    }
}
