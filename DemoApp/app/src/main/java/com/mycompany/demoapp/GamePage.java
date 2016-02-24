package com.mycompany.demoapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


import java.util.logging.LogRecord;

import android.os.Message;
import android.os.Handler;

public class GamePage extends AppCompatActivity implements View.OnClickListener {

    TextView timeTextView;
    TextView scoreTextView;

    TextView questionTextView;
    TextView ans1;
    TextView ans2;
    TextView ans3;
    TextView ans4;

    int score;
    int currentQuestionNumber;
    int countOfQuestions;
    Game game = new Game();

    // Пока задания для теста/игры будут лежать тут в строках, потом скорее всего
    // сделаю маленькую бд

    String[] questions = {
            "25 * 5 = ?#115#110#100#125#125",
            "174 - 28 = ?#144#156#148#146#146",
            "153 + 27 = ?#180#170#167#177#180",
            "80% от 100 = ?#74#80#88#100#80",
            "18 * 3 = ?#54#46#56#58#54",
            "100 - 23 + 33 = ?#103#105#100#110#110",
            "15 * 2 * 3 = ?#65#75#80#90#90",
            "2 * 2 + 2 = ?#4#8#6#10#6",
            "2 * (2 + 2) = ?#6#8#10#4#8",
            "20 % от 80 = ?#8#18#16#20#16"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_page);

        timeTextView = (TextView) findViewById(R.id.timeTextView);
        scoreTextView = (TextView) findViewById(R.id.scoreTextView);
        questionTextView = (TextView) findViewById(R.id.questionTextView);

        ans1 = (TextView) findViewById(R.id.ans1);
        ans2 = (TextView) findViewById(R.id.ans2);
        ans3 = (TextView) findViewById(R.id.ans3);
        ans4 = (TextView) findViewById(R.id.ans4);

        ans1.setOnClickListener(this);
        ans2.setOnClickListener(this);
        ans3.setOnClickListener(this);
        ans4.setOnClickListener(this);

        score = 0;
        currentQuestionNumber = 0;
        countOfQuestions = 10;
    }

    @Override
    protected void onResume() {
        super.onResume();
        game.getNewTask(questions[currentQuestionNumber++]);
        updateScreen();
    };


    @Override
    public void onClick(View v) {
        Intent i = new Intent(GamePage.this, ResultActivity.class);
        switch (v.getId()) {
            case R.id.ans1:
                if (ans1.getText().equals(game.getRightAnswer()))
                    score += 100;
                break;

            case R.id.ans2:
                if (ans2.getText().equals(game.getRightAnswer()))
                    score += 100;
                break;

            case R.id.ans3:
                if (ans3.getText().equals(game.getRightAnswer()))
                    score += 100;
                break;

            case R.id.ans4:
                if (ans4.getText().equals(game.getRightAnswer()))
                    score += 100;
                break;
        }
        if (currentQuestionNumber < countOfQuestions) {
            game.getNewTask(questions[currentQuestionNumber++]);
            updateScreen();
        } else {
            i.putExtra("score", String.valueOf(score));
            startActivity(i);
        }

    }

    public void updateScreen() {
        questionTextView.setText(game.getTask());
        ans1.setText(game.getAnswer1());
        ans2.setText(game.getAnswer2());
        ans3.setText(game.getAnswer3());
        ans4.setText(game.getAnswer4());
        scoreTextView.setText("Счет: " + String.valueOf(score));
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(this, FirstPage.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
    }
}
