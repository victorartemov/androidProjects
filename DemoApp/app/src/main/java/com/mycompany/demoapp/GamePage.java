package com.mycompany.demoapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


import java.util.logging.LogRecord;
import android.os.Message;
import android.os.Handler;

public class GamePage extends AppCompatActivity implements View.OnClickListener{

    TextView timeTextView;
    TextView scoreTextView;

    TextView questionTextView;
    TextView ans1;
    TextView ans2;
    TextView ans3;
    TextView ans4;

    int score = 0;
    int currentQuestionNumber = 0;
    int countOfQuestions = 3;
    Game game = new Game();

    // Пока задания для теста/игры будут лежать тут в строках, потом скорее всего
    // сделаю маленькую бд

    String[] questions = {
            "25 * 5 = ?#115#110#100#125#125",
            "174 - 28 = ?#144#156#148#146#146",
            "153 + 27 = ?" +
                    "#180#170#167#177#180"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_page);

        timeTextView = (TextView) findViewById(R.id.timeTextView);
        scoreTextView = (TextView) findViewById(R.id.scoreTextView);
        questionTextView = (TextView) findViewById(R.id.questionTextView);

        ans1 = (TextView)findViewById(R.id.ans1);
        ans2 = (TextView)findViewById(R.id.ans2);
        ans3 = (TextView)findViewById(R.id.ans3);
        ans4 = (TextView)findViewById(R.id.ans4);

        ans1.setOnClickListener(this);
        ans2.setOnClickListener(this);
        ans3.setOnClickListener(this);
        ans4.setOnClickListener(this);
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
        if(currentQuestionNumber < countOfQuestions)
        {
            switch (v.getId())
            {
                case R.id.ans1:
                    if(ans1.getText().equals(game.getRightAnswer()))
                        score+=100;
                    game.getNewTask(questions[currentQuestionNumber++]);
                    updateScreen();
                    break;

                case R.id.ans2:
                    if(ans2.getText().equals(game.getRightAnswer()))
                        score+=100;
                    game.getNewTask(questions[currentQuestionNumber++]);
                    updateScreen();
                    break;

                case R.id.ans3:
                    if(ans3.getText().equals(game.getRightAnswer()))
                        score+=100;
                    game.getNewTask(questions[currentQuestionNumber++]);
                    updateScreen();
                    break;

                case R.id.ans4:
                    if(ans4.getText().equals(game.getRightAnswer()))
                        score+=100;
                    game.getNewTask(questions[currentQuestionNumber++]);
                    updateScreen();
                    break;
            }
        }
        else {
            //i.putExtra("score",score);
            startActivity(i);
        }
    }

    public void updateScreen()
    {
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
