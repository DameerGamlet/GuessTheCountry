package com.example.guessthecountry;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class GuessAsianGerb extends AppCompatActivity implements VariableInterface {
    public int numberIgame, indexCorrectResult;
    ImageView countryImage;
    Button first, second, third, fourth, fifth, next;
    SharedPreferences sPref, sPrefOne, sPrefTwo, sPrefSound;
    ImageButton back, soundImageButton;
    String name_country;
    TextView messageForResult;
    final String mesTrue = "Верно. На картинке изображён герб страны \"", mesFalse1 = "Неверно, это не герб страны \"", mesFalse2 = "\". \nПравильный ответ: ";
    MediaPlayer trueAnswerSound, falseAnswerSound;

    private void init() {
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
        second.setId((int) 1);
        third.setId((int) 2);
        fourth.setId((int) 3);
        fifth.setId((int) 4);

        trueAnswerSound = MediaPlayer.create(this, R.raw.true_answer);
        falseAnswerSound = MediaPlayer.create(this, R.raw.false_answer);
    }

    public void imageIn(int i) {
        if (i == 1) {
            soundImageButton.setImageResource(R.drawable.onsound);
        } else if (i == 0) {
            soundImageButton.setImageResource(R.drawable.offsound);
        }
    }

    private void soundPlay(MediaPlayer sound) {sound.start();}

    public void buttonOperation(Button button){
        savedResultOne();
        if (button.getId() == indexCorrectResult) {
            savedCountry();
            savedResultOne();
            button.setBackgroundResource(R.drawable.truebutton);
            messageForResult.setText(mesTrue + button.getText() + "\".");
            if (loadSound() == 1)
                soundPlay(trueAnswerSound);
        } else {
            button.setBackgroundResource(R.drawable.falsebutton);
            messageForResult.setText(mesFalse1 + button.getText() + mesFalse2 + name_country);
            if (loadSound() == 1)
                soundPlay(falseAnswerSound);
        }
        onClickableButton(false);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guess_asian_gerb);
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
                if (loadSound() == 1) {
                    saveSound(0);
                } else if (loadSound() == 0) {
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

    public void clearButton() {
        first.setBackgroundResource(R.drawable.button);
        second.setBackgroundResource(R.drawable.button);
        third.setBackgroundResource(R.drawable.button);
        fourth.setBackgroundResource(R.drawable.button);
        fifth.setBackgroundResource(R.drawable.button);
    }

    public void savedCountry() {
        sPref = getSharedPreferences(variable.COUNTRY_GERB_SAVED, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sPref.edit();
        editor.putInt(variable.COUNTRY_GERB_SAVED, loadCountry() + 1);
        editor.apply();
    }

    public int loadCountry() {
        sPref = getSharedPreferences(variable.COUNTRY_GERB_SAVED, Context.MODE_PRIVATE);
        return sPref.getInt(variable.COUNTRY_GERB_SAVED, 0);
    }


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

    public void saveSound(int sound) {
        sPrefSound = getSharedPreferences(variable.SOUND, Context.MODE_PRIVATE);
        SharedPreferences.Editor editoro = sPrefSound.edit();
        editoro.putInt(variable.SOUND, sound);
        editoro.apply();
    }

    public int loadSound() {
        sPrefSound = getSharedPreferences(variable.SOUND, Context.MODE_PRIVATE);
        return sPrefSound.getInt(variable.SOUND, 0);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void generated() {
        clearButton();
        numberIgame = random.nextInt(variable.asian_gerb - 1);
        countryImage.setImageResource(arrays.asian_gerb_images[numberIgame]);
        name_country = arrays.asian_name[numberIgame];
        Set<Integer> randomSet = new HashSet<>();
        while (randomSet.size() != 5) {
            int temp = random.nextInt(variable.asian_gerb - 1);
            if (temp != numberIgame)
                randomSet.add(temp);
        }

        int[] rand_o_5 = new int[5];
        for (int i = 0; i < 5; i++)
            rand_o_5[i] = 0;

        Iterator<Integer> i = randomSet.iterator();
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

        System.out.println("name: " + arrays.asian[numberIgame]);

        indexCorrectResult = (int) (Math.random() * 4);
        switch (indexCorrectResult) {
            case 0:
                first.setText(arrays.asian[numberIgame]);
                second.setText(arrays.asian[rand_o_5[0]]);
                third.setText(arrays.asian[rand_o_5[1]]);
                fourth.setText(arrays.asian[rand_o_5[2]]);
                fifth.setText(arrays.asian[rand_o_5[3]]);
                break;
            case 1:
                first.setText(arrays.asian[rand_o_5[0]]);
                second.setText(arrays.asian[numberIgame]);
                third.setText(arrays.asian[rand_o_5[1]]);
                fourth.setText(arrays.asian[rand_o_5[2]]);
                fifth.setText(arrays.asian[rand_o_5[3]]);
                break;
            case 2:
                first.setText(arrays.asian[rand_o_5[0]]);
                second.setText(arrays.asian[rand_o_5[1]]);
                third.setText(arrays.asian[numberIgame]);
                fourth.setText(arrays.asian[rand_o_5[2]]);
                fifth.setText(arrays.asian[rand_o_5[3]]);
                break;
            case 3:
                first.setText(arrays.asian[rand_o_5[0]]);
                second.setText(arrays.asian[rand_o_5[1]]);
                third.setText(arrays.asian[rand_o_5[2]]);
                fourth.setText(arrays.asian[numberIgame]);
                fifth.setText(arrays.asian[rand_o_5[3]]);
                break;
            case 4:
                first.setText(arrays.asian[rand_o_5[0]]);
                second.setText(arrays.asian[rand_o_5[1]]);
                third.setText(arrays.asian[rand_o_5[2]]);
                fourth.setText(arrays.asian[rand_o_5[3]]);
                fifth.setText(arrays.asian[numberIgame]);
                break;
        }
    }
}