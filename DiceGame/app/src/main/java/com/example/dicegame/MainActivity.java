package com.example.dicegame;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private int[] Dice = {0,0,0,0,0};
    private int[] DiceCounted = {0,0,0,0,0};
    private String[] DiceString = {"?", "?", "?", "?", "?"};
    private int numberCount = 1;
    private int Score = 0;
    private int gameScore = 0;
    private int rollCount = 0;

    private TextView textViewDice1;
    private TextView textViewDice2;
    private TextView textViewDice3;
    private TextView textViewDice4;
    private TextView textViewDice5;
    private TextView textViewScore;
    private TextView textViewGameScore;
    private TextView textViewRollCount;
    private Button buttonRollDice;
    private Button buttonResetScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewDice1 = findViewById(R.id.textViewDice1);
        textViewDice2 = findViewById(R.id.textViewDice2);
        textViewDice3 = findViewById(R.id.textViewDice3);
        textViewDice4 = findViewById(R.id.textViewDice4);
        textViewDice5 = findViewById(R.id.textViewDice5);
        textViewScore = findViewById(R.id.textViewScore);
        textViewGameScore = findViewById(R.id.textViewGameScore);
        textViewRollCount = findViewById(R.id.textViewRollCount);
        buttonRollDice = findViewById(R.id.buttonRollDice);
        buttonResetScore = findViewById(R.id.buttonResetScore);

        buttonRollDice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RollDice();
                Score = 0;
            };
        });

        buttonResetScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ResetGame();
            };
        });
    };
    private void RollDice(){
        for(int i=0; i<5; i++){
            Dice[i] = (int)(Math.random()* 6 + 1);
            DiceString[i] = Integer.toString(Dice[i]);
            DiceCounted[i] = 0;
        };

        for(int i=0; i<5; i++){
            numberCount = 1;
            for(int j=i+1; j<5; j++){
                if(Dice[i] == Dice[j] && DiceCounted[j] == 0){
                    if(numberCount == 1){
                        Score += Dice[i] + Dice[j];
                    } else {
                        Score += Dice[j];
                    };

                    DiceCounted[j] = Dice[j];
                    numberCount++;
                };
            };
        };

        gameScore += Score;
        rollCount++;

        SetText();
    };

    private void ResetGame(){
        Score = 0;
        gameScore = 0;
        rollCount = 0;

        SetText();
    };

    private void SetText(){
        textViewDice1.setText(DiceString[0]);
        textViewDice2.setText(DiceString[1]);
        textViewDice3.setText(DiceString[2]);
        textViewDice4.setText(DiceString[3]);
        textViewDice5.setText(DiceString[4]);

        textViewGameScore.setText("Wynik gry: " + gameScore);
        textViewScore.setText("Wynik tego losowania: " + Score);
        textViewRollCount.setText("Liczba rzutów: " + rollCount);
    }
};