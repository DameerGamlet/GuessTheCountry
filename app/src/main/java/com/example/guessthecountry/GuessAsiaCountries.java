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
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class GuessAsiaCountries extends AppCompatActivity {
    GlobalVariable variable = new GlobalVariable();
    CountryArrays arrays = new CountryArrays();
    Random random = new Random();
    int numberIgame;
    ImageView countryImage;
    ImageButton soundImageButton;
    Button button1, button2, button3, button4, button5, button7, button8, button9, button10,
            button11, button13, button14, button15, button16, button17, button, next;
    TextView name_c, message;
    SharedPreferences sPref, sPrefOne, sPrefTwo, sPrefSound;
    String name_country;
    ImageButton back;
    MediaPlayer trueAnswerSound, falseAnswerSound;

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

    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_guess_asia_countries);
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

            next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clearn();
                    generated();
                    name_c.setText("");
                    message.setText("");
                }
            });

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    savedResultOne();
                    if(name_c.getText().equals(name_country.toUpperCase())){
                        message.setText("Верно, это флаг страны \"" + name_c.getText() + "\".");
                        savedCountry(numberIgame);
                        savedResultTwo();
                        if(loadSound() == 1)
                            soundPlay(trueAnswerSound);
                    }
                    else if(name_c.getText().equals("")){
                        message.setText("Пустое поле!");
                        if(loadSound() == 1)
                            soundPlay(falseAnswerSound);
                    }
                    else{
                        message.setText("Это не \"" + name_c.getText() + "\". Попробуйте ещё.");
                        clearn();
                        name_c.setText("");
                        if(loadSound() == 1)
                            soundPlay(falseAnswerSound);
                    }
                }
            });

            button1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    button1.setClickable(false);
                    button1.setBackground(null);
                    String do_name = name_c.getText().toString();
                    name_c.setText(do_name + button1.getText());
                }
            });

            button2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    button2.setClickable(false);
                    button2.setBackground(null);
                    String do_name = name_c.getText().toString();
                    name_c.setText(do_name + button2.getText());
                }
            });

            button3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    button3.setClickable(false);
                    button3.setBackground(null);
                    String do_name = name_c.getText().toString();
                    name_c.setText((do_name +button3.getText()).toUpperCase());
                }
            });

            button4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    button4.setClickable(false);
                    button4.setBackground(null);
                    String do_name = name_c.getText().toString();
                    name_c.setText(do_name + button4.getText());
                }
            });

            button5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    button5.setClickable(false);
                    button5.setBackground(null);
                    String do_name = name_c.getText().toString();
                    name_c.setText(do_name +button5.getText());
                }
            });

            button7.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    button7.setClickable(false);
                    button7.setBackground(null);
                    String do_name = name_c.getText().toString();
                    name_c.setText(do_name +button7.getText());
                }
            });

            button8.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    button8.setClickable(false);
                    button8.setBackground(null);
                    String do_name = name_c.getText().toString();
                    name_c.setText(do_name + button8.getText());
                }
            });

            button9.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    button9.setClickable(false);
                    button9.setBackground(null);
                    String do_name = name_c.getText().toString();
                    name_c.setText(do_name + button9.getText());
                }
            });

            button10.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    button10.setClickable(false);
                    button10.setBackground(null);
                    String do_name = name_c.getText().toString();
                    name_c.setText(do_name + button10.getText());
                }
            });

            button11.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    button11.setClickable(false);
                    button11.setBackground(null);
                    String do_name = name_c.getText().toString();
                    name_c.setText(do_name + button11.getText());
                }
            });

            button13.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    button13.setClickable(false);
                    button13.setBackground(null);
                    String do_name = name_c.getText().toString();
                    name_c.setText(do_name + button13.getText());
                }
            });

            button14.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    button14.setClickable(false);
                    button14.setBackground(null);
                    String do_name = name_c.getText().toString();
                    name_c.setText(do_name + button14.getText());
                }
            });

            button15.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    button15.setClickable(false);
                    button15.setBackground(null);
                    String do_name = name_c.getText().toString();
                    name_c.setText(do_name + button15.getText());
                }
            });

            button16.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    button16.setClickable(false);
                    button16.setBackground(null);
                    String do_name = name_c.getText().toString();
                    name_c.setText(do_name + button16.getText());
                }
            });

            button17.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    button17.setClickable(false);
                    button17.setBackground(null);
                    String do_name = name_c.getText().toString();
                    name_c.setText(do_name + button17.getText());
                }
            });
        }

        public void clearn(){
            button1.setClickable(true);
            button2.setClickable(true);
            button3.setClickable(true);
            button4.setClickable(true);
            button5.setClickable(true);
            button7.setClickable(true);
            button8.setClickable(true);
            button9.setClickable(true);
            button10.setClickable(true);
            button11.setClickable(true);
            button13.setClickable(true);
            button14.setClickable(true);
            button15.setClickable(true);
            button16.setClickable(true);
            button17.setClickable(true);
            button1.setBackgroundResource(R.drawable.but);
            button2.setBackgroundResource(R.drawable.but);
            button3.setBackgroundResource(R.drawable.but);
            button4.setBackgroundResource(R.drawable.but);
            button5.setBackgroundResource(R.drawable.but);
            button7.setBackgroundResource(R.drawable.but);
            button8.setBackgroundResource(R.drawable.but);
            button9.setBackgroundResource(R.drawable.but);
            button10.setBackgroundResource(R.drawable.but);
            button11.setBackgroundResource(R.drawable.but);
            button13.setBackgroundResource(R.drawable.but);
            button14.setBackgroundResource(R.drawable.but);
            button15.setBackgroundResource(R.drawable.but);
            button16.setBackgroundResource(R.drawable.but);
            button17.setBackgroundResource(R.drawable.but);
        }

        public void init(){
            next = findViewById(R.id.next);
            countryImage = findViewById(R.id.countryImage);
            button1 =  findViewById(R.id.abutton);
            button2 =  findViewById(R.id.abutton2 );
            button3 =  findViewById(R.id.abutton3 );
            button4 =  findViewById(R.id.abutton4 );
            button5 =  findViewById(R.id.abutton5 );
            button7 =  findViewById(R.id.abutton7 );
            button8 =  findViewById(R.id.button8 );
            button9 =  findViewById(R.id.button9 );
            button10 = findViewById(R.id.button10);
            button11 = findViewById(R.id.button11);
            button13 = findViewById(R.id.button13);
            button14 = findViewById(R.id.button14);
            button15 = findViewById(R.id.button15);
            button16 = findViewById(R.id.button16);
            button17 = findViewById(R.id.button17);
            name_c = findViewById(R.id.atextView5);
            button = findViewById(R.id.button19);
            back = findViewById(R.id.back);
            message = findViewById(R.id.message);
            soundImageButton = findViewById(R.id.soundImage);

            trueAnswerSound = MediaPlayer.create(this, R.raw.true_answer);
            falseAnswerSound = MediaPlayer.create(this, R.raw.false_answer);

        }

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

        public void savedCountry(int n){
            sPref = getSharedPreferences(variable.ASIA_SAVED, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sPref.edit();
            editor.putInt(variable.ASIA_SAVED, loadCountry() + 1);
            editor.apply();
        }

        public int loadCountry(){
            sPref = getSharedPreferences(variable.ASIA_SAVED, Context.MODE_PRIVATE);
            return sPref.getInt(variable.ASIA_SAVED, 0);
        }

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

        @RequiresApi(api = Build.VERSION_CODES.N)
        public void generated() {
            numberIgame = random.nextInt(variable.asian_flag);
            countryImage.setImageResource(arrays.asian_countries_images[numberIgame]);
            name_country = arrays.asian_name[numberIgame];

            String[] name_simbol = name_country.split("");
            char[] abc = {
                    'А', 'Б', 'В', 'Г', 'Д', 'Е', 'Ё', 'Ж', 'З', 'И', 'Й', 'К', 'Л', 'М', 'Н', 'О', 'П', 'Р', 'С', 'Т', 'У', 'Ф', 'Х', 'Ц', 'Ч', 'Ш', 'Щ', 'Ы', 'Ь', 'Э', 'Ю', 'Я'
            };

            List<String> randList = new ArrayList<>();
            while (randList.size() != 15 - name_simbol.length) {
                randList.add((abc[random.nextInt(abc.length-1)] + "").toUpperCase());
            }
            Collections.addAll(randList, name_simbol);
            Collections.shuffle(randList);

            randList.forEach(s -> System.out.print(s + " "));
            System.out.println(name_country);

            button1.setText(randList.get(0).toUpperCase());
            button2.setText(randList.get(1).toUpperCase());
            button3.setText(randList.get(2).toUpperCase());
            button4.setText(randList.get(3).toUpperCase());
            button5.setText(randList.get(4).toUpperCase());
            button7.setText(randList.get(5).toUpperCase());
            button8.setText(randList.get(6).toUpperCase());
            button9.setText(randList.get(7).toUpperCase());
            button10.setText(randList.get(8).toUpperCase());
            button11.setText(randList.get(9).toUpperCase());
            button13.setText(randList.get(10).toUpperCase());
            button14.setText(randList.get(11).toUpperCase());
            button15.setText(randList.get(12).toUpperCase());
            button16.setText(randList.get(13).toUpperCase());
            button17.setText(randList.get(14).toUpperCase());
            clearn();
        }
    }